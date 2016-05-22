package org.helyx.game.ai.shortpathfinder;

import org.helyx.game.ai.AI;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.helyx.graph.Path;

import java.util.List;

import static java.lang.Integer.compare;
import static java.lang.System.currentTimeMillis;
import static java.util.stream.Collectors.toList;
import static org.helyx.game.ai.shortpathfinder.ShortestPathFinder.findShortestPath;

public class ShortestPathFinderAI implements AI {

    @Override
    public Link nextLinkToCut(Graph g, int skyNetAgentIndice) {

        long start = currentTimeMillis();

        System.err.println("****************************************************");

        System.err.println("--- Search Paths -----------------------------------------------");
        System.err.println(" Skynet Agent Indice: " + skyNetAgentIndice);
        System.err.println("----------------------------------------------------------------");

        List<Path> foundPaths = findShortestPath(g, skyNetAgentIndice);

        if (foundPaths.isEmpty()) {
            throw new NoPathFoundException();
        }

        List<Link> links = foundPaths.stream()
                .map(Path::getLinks)
                .flatMap(List::stream)
                .distinct()
                .sorted((l1, l2) -> -compare(l1.getWeight(), l2.getWeight()))
                .collect(toList());

        Link link = links.get(0);

        System.err.println("Duration: " + Math.max(0, currentTimeMillis() - start) + " ms");
        System.err.println("First Link of shortest path found:" + link);
        System.err.println("--------------------------------------------------");

        return link;
    }

}
