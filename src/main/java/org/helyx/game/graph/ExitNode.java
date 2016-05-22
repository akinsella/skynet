package org.helyx.game.graph;

import org.helyx.graph.Node;

public class ExitNode extends Node {

    public ExitNode(int index) {
        super(index);
    }

    public ExitNode(int index, int weight) {
        super(index, weight);
    }

    public Node weighted(int weight) {
        return new ExitNode(getIndex(), weight);
    }

    @Override
    public String toString() {
        return "ExitNode{" + getIndex() + "}";
    }
}
