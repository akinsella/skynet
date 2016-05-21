package org.helyx.graph;

import org.helyx.game.graph.ExitNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return links.stream().map(link -> link.reverse()).collect(toList());
    }

    @Override
    public String toString() {
        return "Graph{links=" + links + '}';
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

}