package org.helyx.game.ai;

import org.helyx.graph.Graph;
import org.helyx.graph.Link;

public interface AI {

    Link nextLinkToCut(Graph graph, int skynetAgentIndice);

    class NoPathFoundException extends RuntimeException { }
    class NoLinkFoundException extends RuntimeException { }

}
