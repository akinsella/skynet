package org.helyx.game;

import org.helyx.game.ai.AI;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;

import java.io.InputStream;
import java.util.Scanner;

import static java.lang.String.format;
import static org.helyx.game.graph.GraphBuilder.buildGraph;

public class GameEngine {

    public static Graph play(AI ai, InputStream in) {

        Scanner scanner = new Scanner(in);

        Graph graph = buildGraph(scanner);

        while (scanner.hasNextInt()) {
            int skynetAgentIndice = scanner.nextInt();
            Link linkToCut = ai.nextLinkToCut(graph, skynetAgentIndice);

            graph.cutLink(linkToCut);
            System.out.println(format("%1$s %2$s", linkToCut.getN1().getIndex(), linkToCut.getN2().getIndex()));
        }

        return graph;
    }

}
