package org.helyx.game.ai.shortestpathfinder;

import org.helyx.game.graph.ExitNode;
import org.helyx.graph.Graph;
import org.helyx.graph.Node;
import org.helyx.graph.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;


/**
 *
 * Assumes there is a solution
 *
 */
public class ShortestPathFinder {

    private ShortestPathFinder() {
        super();
    }

    public static List<Path> findShortestPaths(Graph graph, int orig) {

        List<Path> initialPaths = findSiblingPaths(graph, new Path(), new Node(orig));

        Queue<Path> queue = new LinkedBlockingQueue<>(initialPaths);

        List<Path> foundPaths = new ArrayList<>();
        Integer shortestPathSize = null;

        while (!queue.isEmpty()) {

            Path path = queue.poll();

            if (shortestPathSize != null && path.size() > shortestPathSize) {
                break;
            }

            if (path.last().get().getN2() instanceof ExitNode) {
                shortestPathSize = path.size();
                foundPaths.add(path);
                continue;
            }

            List<Path> siblingPaths = findSiblingPaths(graph, path, path.lastNode().get());

            queue.addAll(siblingPaths);
        }

        return foundPaths;
    }

    static List<Path> findSiblingPaths(Graph graph, Path path, Node node) {
        return concat(graph.getLinks().stream(), graph.reverseLinks().stream())
                .filter(link -> link.isConnectedTo(node) && !path.contains(link.getN2()))
                .map(path::push)
                .collect(toList());
    }

}
