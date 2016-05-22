package org.helyx.game.graph;

import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.helyx.graph.Node;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class GraphBuilder {

    private GraphBuilder() {
        super();
    }

    public static Graph buildGraph(Scanner in) {

        /*int nodeCount = */in.nextInt(); // the total number of nodes in the level, including the exitNodes
        int linkCount = in.nextInt(); // the number of links
        int exitCount = in.nextInt(); // the number of exit exitNodes

        List<Link> links = buildLinks(in, linkCount);
        List<ExitNode> exitNodes = buildExitNodes(in, exitCount);

        return new Graph(links)
                .replaceNodes(exitNodes)
                .computeWeights(weightNodeByLinkCount(links));
    }

    private static List<Link> buildLinks(Scanner in, int linkCount) {
        return range(0, linkCount).boxed().map(i -> {
            Node node1 = new Node(in.nextInt()); // node1 and node2 defines a link between these nodes
            Node node2 = new Node(in.nextInt());

            return new Link(node1, node2);
        }).collect(toList());
    }

    private static List<ExitNode> buildExitNodes(Scanner in, int exitCount) {
        return range(0, exitCount).boxed().map(i -> {
            return new ExitNode(in.nextInt()); // the index of a gateway node
        }).collect(toList());
    }

    public static Function<Node, Integer> weightNodeByLinkCount(List<Link> links) {
        return node ->
                links.stream()
                        .mapToInt(l -> l.contains(node) ? 1 : 0)
                        .reduce(0, (acc, weight) -> acc + weight);
    }

}