package org.helyx;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShortestPathFinderAI implements AI {

    @Override
    public Link nextLinkToCut(GameState gs) {

        System.err.println("****************************************************");

        int skynetAgentIndice = gs.getSkynetAgentIndice();

        List<Path> foundPaths = gs.getGateways().stream()
        .map(g -> {
            int exitIndice = g.getExitIndice();

            System.err.println("--- Gateway: " + g.getExitIndice() + ", skynetAgentIndice: " + skynetAgentIndice);

            List<Path> gwFoundPaths = searchShorterPath(skynetAgentIndice, exitIndice, gs.getUncutList());

            System.err.println("Shorter Path: " + gwFoundPaths);

            return gwFoundPaths;
        })
        .flatMap(List::stream)
        .sorted((p1, p2) -> p1.size().compareTo(p2.size()))
        .collect(Collectors.toList());

        Optional<Path> maybeShortestPath = foundPaths.isEmpty() ? Optional.empty() : Optional.of(foundPaths.get(0));

        Path path = maybeShortestPath.orElseThrow(NoActionAvailable::new);

        Optional<Link> firstLink = path
                .first().orElseThrow(NoActionAvailable::new).getN1() == skynetAgentIndice ?
                path.first() :
                path.last();

        System.err.println("First Link of shortest path found:" + firstLink);
        System.err.println("--------------------------------------------------");

        return firstLink.orElseThrow(() -> new AI.NoActionAvailable());
    }

    List<Path> searchShorterPath(int skynetAgentIndice, int exitIndice, List<Link> pLinks) {

        long start = System.currentTimeMillis();

        System.err.println("--- Search Paths -----------------------------------------------");

        System.err.println("--- skynetAgentIndice: " + skynetAgentIndice + ", exitIndice: " + exitIndice);
//        System.err.println("--- links: " + pLinks);
        System.err.println("--------------------------------------------------");

        List<Link> links = pLinks.stream()
                .map(link -> Arrays.asList(link, new Link(link.getN2(), link.getN1())))
                .flatMap(List::stream)
                .collect(Collectors.toList());

//        System.err.println("Links sorted:" + links);

        List<Path> foundPaths = GraphWalker.visit(skynetAgentIndice, exitIndice, links);

//        System.err.println("Visit:" + foundPaths);

        System.err.println("Duration: " + Math.max(0, System.currentTimeMillis() - start) + " ms");

        return foundPaths;
    }

}
