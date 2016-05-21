package org.helyx.graph;

public class Node {

    private int index;

    public Node(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Accepts sub type equality
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return index == node.index;

    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public String toString() {
        return "Node{" + index + '}';
    }

}
