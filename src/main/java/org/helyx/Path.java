package org.helyx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Path {

    private List<Link> links = new ArrayList<>();

    public List<Link> getLinks() {
        return Collections.unmodifiableList(links);
    }

    public Path() {
        super();
    }

    private Path(List<Link> links) {
        super();
        this.links = links;
    }

    public Integer size() {
        return links.size();
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

    @Override
    public String toString() {
        return "Path{ links=" + links + '}';
    }
}
