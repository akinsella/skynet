package org.helyx.game.ai.shortpathfinder;

import org.helyx.game.ai.AI;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.helyx.graph.Path;

import static java.lang.System.currentTimeMillis;
import static org.helyx.game.ai.shortpathfinder.ShortestPathFinder.findShortestPath;

public class ShortestPathFinderAI implements AI {

    @Override
    public Link nextLinkToCut(Graph g, int skyNetAgentIndice) {

        long start = currentTimeMillis();

        System.err.println("****************************************************");

        System.err.println("--- Search Paths -----------------------------------------------");
        System.err.println(" Skynet Agent Indice: " + skyNetAgentIndice);
        System.err.println("----------------------------------------------------------------");

        Path foundPath = findShortestPath(g, skyNetAgentIndice).orElseThrow(NoPathFoundException::new);

        Link firstLink = foundPath
                .first().orElseThrow(EmptyPathException::new).getN1().getIndex() == skyNetAgentIndice ?
                foundPath.first().get() :
                foundPath.last().get();

        System.err.println("Duration: " + Math.max(0, currentTimeMillis() - start) + " ms");
        System.err.println("First Link of shortest path found:" + firstLink);
        System.err.println("--------------------------------------------------");

        return firstLink;
    }

    class EmptyPathException extends RuntimeException { }

}
