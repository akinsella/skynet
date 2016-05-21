package org.helyx.graph;

public class Link {

    final Node n1, n2;

    public Link(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public Link(int i1, int i2) {
        this.n1 = new Node(i1);
        this.n2 = new Node(i2);
    }

    public Node getN1() {
        return n1;
    }

    public Node getN2() {
        return n2;
    }

    public Link reverse() {
        return new Link(n2, n1);
    }

    public boolean isConnectedTo(Link link) {
        return n1.equals(link.getN2());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link = (Link) o;

        if (n1 != null ? !n1.equals(link.n1) : link.n1 != null) return false;
        return n2 != null ? n2.equals(link.n2) : link.n2 == null;

    }

    @Override
    public int hashCode() {
        int result = n1 != null ? n1.hashCode() : 0;
        result = 31 * result + (n2 != null ? n2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + n1.getIndex() + " " + n2.getIndex() + '}';
    }

}