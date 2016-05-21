package org.helyx.game.graph;

import org.helyx.graph.Node;

public class ExitNode extends Node {

    public ExitNode(int index) {
        super(index);
    }

    @Override
    public String toString() {
        return "ExitNode{" + getIndex() + "}";
    }
}
