package org.helyx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GraphWalker {

    private GraphWalker() {
        super();
    }

    static List<Path> visit(int orig, int dest, List<Link> links) {
        GraphWalker graphWalker = new GraphWalker();

        List<Path> initialPaths = links.stream()
                .filter(link -> link.getN1() == orig)
                .map(link -> new Path().push(link))
                .collect(Collectors.toList());

        List<Path> paths = graphWalker.visitPaths(dest, links, initialPaths, 0);

//        System.err.println("* paths: " + toString(paths));

        return paths;
    }

    private List<Path> visitPaths(int dest, List<Link> links, List<Path> paths, int depth) {

        List<Path> pathsToAdd = new ArrayList<>();

        for (Path path : paths) {

            if (path.last().get().getN2() == dest) {
                return Collections.singletonList(path);
            }

            List<Path> candidatePaths = links.stream()
                    .filter(link ->
                        link.getN1() == path.last().get().getN2() &&
                        !path.getLinks().stream().anyMatch(hLink -> link.getN2() == hLink.getN1() )
                    )
                    .map(path::push)
                    .collect(Collectors.toList());


            pathsToAdd.addAll(candidatePaths);
        }

        return visitPaths(dest, links, pathsToAdd, depth + 1);
    }

}
