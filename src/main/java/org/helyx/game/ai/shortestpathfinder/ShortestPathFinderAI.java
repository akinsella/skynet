package org.helyx.game.ai.shortestpathfinder;

import org.helyx.game.ai.AI;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.helyx.graph.Path;

import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.compare;
import static java.lang.Math.max;
import static java.lang.System.currentTimeMillis;
import static org.helyx.game.ai.shortestpathfinder.ShortestPathFinder.findShortestPaths;

public class ShortestPathFinderAI implements AI {

    @Override
    public Link nextLinkToCut(Graph g, int skyNetAgentIndice) {

        long start = currentTimeMillis();

        System.err.println("****************************************************");

        System.err.println("--- Search Paths -----------------------------------------------");
        System.err.println(" Skynet Agent Indice: " + skyNetAgentIndice);
        System.err.println("----------------------------------------------------------------");

        List<Path> foundPaths = findShortestPaths(g, skyNetAgentIndice);

        if (foundPaths.isEmpty()) {
            throw new NoPathFoundException();
        }

        System.err.println(foundPaths.size() + " path(s) found with a size of " + foundPaths.get(0).getLinks().size());

        Link link = foundPaths.stream()
                .map(Path::getLinks)
                .flatMap(List::stream)
                .distinct()
                .sorted(lessWeightedLinkComparator())
                .findFirst().orElseThrow(NoLinkFoundException::new);

        System.err.println("Duration: " + max(0, currentTimeMillis() - start) + " ms");
        System.err.println("Link to sever: " + link);
        System.err.println("--------------------------------------------------");

        return link;
    }

    private Comparator<Link> lessWeightedLinkComparator() {
        return (l1, l2) -> compare(l1.getWeight(), l2.getWeight());
    }

}
