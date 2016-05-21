package org.helyx.game.ai.shortpathfinder;

import org.helyx.game.graph.ExitNode;
import org.helyx.graph.Graph;
import org.helyx.graph.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static Optional<Path> findShortestPath(Graph graph, int orig) {

        List<Path> initialPaths = concat(graph.getLinks().stream(), graph.reverseLinks().stream())
                .filter(link -> link.getN1().getIndex() == orig)
                .map(Path::new)
                .collect(toList());

        return new ShortestPathFinder().visitPaths(graph, initialPaths);
    }

    private Optional<Path> visitPaths(Graph graph, List<Path> paths) {

        if (paths.isEmpty()) {
            return Optional.empty();
        }

        List<Path> pathsToAdd = new ArrayList<>();

        for (Path path : paths) {

            if (path.last().get().getN2() instanceof ExitNode) {
                return Optional.of(path);
            }

            List<Path> candidatePaths = concat(graph.getLinks().stream(), graph.reverseLinks().stream())
                    .filter(link ->
                        link.isConnectedTo(path.last().get()) &&
                        !path.contains(link.getN2())
                    )
                    .map(path::push)
                    .collect(toList());


            pathsToAdd.addAll(candidatePaths);
        }

        return visitPaths(graph, pathsToAdd);
    }

}
