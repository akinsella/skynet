package org.helyx;

import org.helyx.game.ai.shortestpathfinder.ShortestPathFinderAI;

import static org.helyx.game.GameEngine.play;

class App {

    public static void main(String args[]) {
        play(new ShortestPathFinderAI(), System.in);
    }

}