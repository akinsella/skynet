package org.helyx;

import static org.helyx.GameEngine.play;

class App {

    public static void main(String args[]) {
        play(new BasicAI(), System.in);
    }

}