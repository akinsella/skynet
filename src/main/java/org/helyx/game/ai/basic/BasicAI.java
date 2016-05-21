package org.helyx.game.ai.basic;

import org.helyx.game.ai.AI;
import org.helyx.game.graph.ExitNode;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;

public class BasicAI implements AI {

    public Link nextLinkToCut(Graph g, int skynetAgentIndice) {

        for (Link link : g.getLinks()) {
            if (link.getN1() instanceof ExitNode || link.getN2() instanceof ExitNode) {
                return link;
            }
        }

        throw new NoPathFoundException();
    }

}
