package org.helyx;

public interface AI {

    Link nextLinkToCut(GameState gameState);

    class NoActionAvailable extends RuntimeException {
        public NoActionAvailable() {
            super("No action available");
        }
    }

}
