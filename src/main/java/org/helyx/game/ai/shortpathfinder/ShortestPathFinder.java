package org.helyx.game.ai.shortpathfinder;

import org.helyx.game.graph.ExitNode;
import org.helyx.graph.Graph;
import org.helyx.graph.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.singletonList;
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

    public static List<Path> findShortestPath(Graph graph, int orig) {

        List<Path> initialPaths = concat(graph.getLinks().stream(), graph.reverseLinks().stream())
                .filter(link -> link.getN1().getIndex() == orig)
                .map(Path::new)
                .collect(toList());

        Queue<Path> queue = new LinkedBlockingQueue<>(initialPaths);

        return new ShortestPathFinder().visitPaths(graph, queue);
    }

    private List<Path> visitPaths(Graph graph, Queue<Path> paths) {

        List<Path> foundPaths = new ArrayList<>();

        while (!paths.isEmpty()) {

            Path path = paths.poll();

            if (path.last().get().getN2() instanceof ExitNode) {
                return singletonList(path);
            }

            List<Path> candidatePaths = concat(graph.getLinks().stream(), graph.reverseLinks().stream())
                    .filter(link ->
                            link.isConnectedTo(path.last().get()) &&
                                    !path.contains(link.getN2())
                    )
                    .map(path::push)
                    .collect(toList());

            paths.addAll(candidatePaths);
        }

        return foundPaths;
    }

}
