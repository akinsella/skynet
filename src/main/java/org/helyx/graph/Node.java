package org.helyx.graph;

public class Node {

    private int index;
    private int weight = 1;

    public Node(int index) {
        this.index = index;
    }
    public Node(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
        return "Node{ i:" + index + ", w:" + weight + "}";
    }

}
