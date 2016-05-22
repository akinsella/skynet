package org.helyx.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

public class Path {

    private List<Link> links = new ArrayList<>();

    public List<Link> getLinks() {
        return unmodifiableList(links);
    }

    public Path(Link link) {
        this(singletonList(link));
    }

    public Path(List<Link> links) {
        super();
        this.links = new ArrayList<>(links);
    }

    public Path push(Link link) {
        ArrayList<Link> links = new ArrayList<>(getLinks());
        links.add(link);

        return new Path(links);
    }

    public Optional<Link> first() {
        return links.isEmpty() ? Optional.empty() : Optional.of(links.get(0));
    }

    public Optional<Link> last() {
        return links.isEmpty() ? Optional.empty() : Optional.of(links.get(links.size() - 1));
    }

    public boolean contains(Node node) {
        return links.stream().anyMatch(link -> node.equals(link.n1) || node.equals(link.n2));
    }

    public boolean isEmpty() {
        return links.isEmpty();
    }


    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public int size() {
        return links.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;

        Path path = (Path) o;

        return links != null ? links.equals(path.links) : path.links == null;

    }

    @Override
    public int hashCode() {
        return links != null ? links.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Path{ links=" + links + '}';
    }

}
