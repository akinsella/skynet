package org.helyx;

import java.io.InputStream;
import java.util.Scanner;

import static java.lang.String.format;

public class GameEngine {

    public static GameState play(AI ai, InputStream in) {

        Scanner scanner = new Scanner(in);

        GameState gameState = GameState.buildGameState(scanner);

        while (scanner.hasNextInt()) {
            int skynetAgentIndice = scanner.nextInt();
            gameState.consumeNextSkynetAgentAction(skynetAgentIndice);

            Link link = ai.nextLinkToCut(gameState);

            gameState.cutLink(link);
            System.out.println(format("%1$s %2$s", link.getN1(), link.getN2()));
        }

        return gameState;
    }

}
