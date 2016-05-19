package org.helyx;

public class BasicAI implements AI {

    public Link nextLinkToCut(GameState gs) {

        for (Gateway g : gs.getGateways()) {
            int exitIndice = g.getExitIndice();

            for (Link link : gs.getUncutList()) {
                if (link.getN1() == exitIndice || link.getN2() == exitIndice) {
                    return link;
                }
            }
        }

        throw new AI.NoActionAvailable();
    }

}
