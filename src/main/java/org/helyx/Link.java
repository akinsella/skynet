package org.helyx;

public class Link {

    final int n1, n2;

    public Link(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (n1 != link.n1) return false;
        return n2 == link.n2;

    }

    @Override
    public int hashCode() {
        int result = n1;
        result = 31 * result + n2;
        return result;
    }

    @Override
    public String toString() {
        return "{" + n1 + " " + n2 + '}';
    }

}