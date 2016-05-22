package org.helyx.graph;

import org.helyx.game.graph.ExitNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Graph {

    private List<Link> links;

    public Graph(List<Link> links) {
        super();
        this.links = new ArrayList<>(links);
    }

    public void cutLink(Link link) {
        links.remove(link);
        links.remove(link.reverse());
    }

    public List<Link> getLinks() {
        return new ArrayList<>(links);
    }

    public List<Link> reverseLinks() {
        return links.stream().map(Link::reverse).collect(toList());
    }

    public boolean contains(Link link) {
        return links.contains(link) || links.contains(link.reverse());
    }

    public Link first() {
        return links.get(0);
    }

    public Graph replaceNodes(List<ExitNode> nodes) {

        Map<Integer, Node> nodeMap = nodes.stream().collect(toMap(Node::getIndex, node -> node));

        List<Link> links = this.links.stream().map(link -> new Link(
                nodeMap.getOrDefault(link.getN1().getIndex(), link.getN1()),
                nodeMap.getOrDefault(link.getN2().getIndex(), link.getN2())
        )).collect(toList());

        return new Graph(links);
    }

    public Graph computeWeights(Function<Node, Integer> weight) {
        Map<Node, Integer> weights = this.links.stream()
                .map(l -> asList(l.getN1(), l.getN2()))
                .flatMap(List::stream)
                .distinct()
                .collect(toMap(identity(), weight));

        List<Link> links = this.links.stream()
                .map(l -> new Link(
                        l.getN1().weighted(weights.get(l.getN1())),
                        l.getN2().weighted(weights.get(l.getN2()))
                )).collect(toList());

        return new Graph(links);
    }

    @Override
    public String toString() {
        return "Graph{links=" + links + '}';
    }
}