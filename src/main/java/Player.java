import java.io.InputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

import static java.lang.Integer.compare;
import static java.lang.Math.max;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.concat;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    interface AI {

        Link nextLinkToCut(Graph graph, int skynetAgentIndice);

        class NoLinkFoundException extends RuntimeException { }
        class NoPathFoundException extends RuntimeException { }
    }

    static class ShortestPathFinderAI implements AI {

        @Override
        public Link nextLinkToCut(Graph g, int skyNetAgentIndice) {

            long start = currentTimeMillis();

            System.err.println("****************************************************");

            System.err.println("--- Search Paths -----------------------------------------------");
            System.err.println(" Skynet Agent Indice: " + skyNetAgentIndice);
            System.err.println("----------------------------------------------------------------");

            List<Path> foundPaths = ShortestPathFinder.findShortestPaths(g, skyNetAgentIndice);

            if (foundPaths.isEmpty()) {
                throw new NoPathFoundException();
            }

            System.err.println(foundPaths.size() + " path(s) found with a size of " + foundPaths.get(0).getLinks().size());

            Link link = foundPaths.stream()
                    .map(Path::getLinks)
                    .flatMap(List::stream)
                    .distinct()
                    .sorted(lessWeightedLinkComparator())
                    .findFirst().orElseThrow(NoLinkFoundException::new);

            System.err.println("Duration: " + max(0, currentTimeMillis() - start) + " ms");
            System.err.println("Link to sever: " + link);
            System.err.println("--------------------------------------------------");

            return link;
        }

        private Comparator<Link> lessWeightedLinkComparator() {
            return (l1, l2) -> compare(l1.getWeight(), l2.getWeight());
        }

    }


    static class ShortestPathFinder {

        private ShortestPathFinder() {
            super();
        }

        public static List<Path> findShortestPaths(Graph graph, int orig) {

            List<Path> initialPaths = findSiblingPaths(graph, new Path(), new Node(orig));

            Queue<Path> queue = new LinkedBlockingQueue<>(initialPaths);

            List<Path> foundPaths = new ArrayList<>();
            Integer shortestPathSize = null;

            while (!queue.isEmpty()) {

                Path path = queue.poll();

                if (shortestPathSize != null && path.size() > shortestPathSize) {
                    break;
                }

                if (path.last().get().getN2() instanceof ExitNode) {
                    shortestPathSize = path.size();
                    foundPaths.add(path);
                    continue;
                }

                List<Path> siblingPaths = findSiblingPaths(graph, path, path.lastNode().get());

                queue.addAll(siblingPaths);
            }

            return foundPaths;
        }

        static List<Path> findSiblingPaths(Graph graph, Path path, Node node) {
            return concat(graph.getLinks().stream(), graph.reverseLinks().stream())
                    .filter(link -> link.isConnectedTo(node) && !path.contains(link.getN2()))
                    .map(path::push)
                    .collect(toList());
        }

    }

    static class GameEngine {

        public static Graph play(AI ai, InputStream in) {

            Scanner scanner = new Scanner(in);

            Graph graph = GraphBuilder.buildGraph(scanner);

            while (scanner.hasNextInt()) {
                int skynetAgentIndice = scanner.nextInt();
                Link linkToCut = ai.nextLinkToCut(graph, skynetAgentIndice);

                graph.cutLink(linkToCut);
                System.out.println(format("%1$s %2$s", linkToCut.getN1().getIndex(), linkToCut.getN2().getIndex()));
            }

            return graph;
        }

    }

    static class GraphBuilder {

        private GraphBuilder() {
            super();
        }

        public static Graph buildGraph(Scanner in) {

        /*int nodeCount = */in.nextInt(); // the total number of nodes in the level, including the exitNodes
            int linkCount = in.nextInt(); // the number of links
            int exitCount = in.nextInt(); // the number of exit exitNodes

            List<Link> links = buildLinks(in, linkCount);
            List<ExitNode> exitNodes = buildExitNodes(in, exitCount);

            return new Graph(links)
                    .replaceNodes(exitNodes)
                    .computeWeights(weightNode(links));
        }

        private static List<Link> buildLinks(Scanner in, int linkCount) {
            return range(0, linkCount).boxed().map(i -> {
                Node node1 = new Node(in.nextInt()); // node1 and node2 defines a link between these nodes
                Node node2 = new Node(in.nextInt());

                return new Link(node1, node2);
            }).collect(toList());
        }

        private static List<ExitNode> buildExitNodes(Scanner in, int exitCount) {
            return range(0, exitCount).boxed().map(i -> {
                return new ExitNode(in.nextInt()); // the index of a gateway node
            }).collect(toList());
        }

        private static Function<Node, Integer> weightNode(List<Link> links) {
            return node ->
                    links.stream()
                            .mapToInt(l -> l.contains(node) ? 1 : 0)
                            .reduce(0, (acc, weight) -> acc + weight);
        }

    }

    static class Graph {

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

    static class Path {

        private List<Link> links = new ArrayList<>();

        public Path() {
            super();
        }


        public Path(Link link) {
            this(singletonList(link));
        }

        public Path(List<Link> links) {
            super();
            this.links = new ArrayList<>(links);
        }

        public List<Link> getLinks() {
            return unmodifiableList(links);
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

        public Optional<Node> lastNode() {
            return last().isPresent() ? Optional.of(last().get().n2) : Optional.empty();
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

    static class Link {

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
            return isConnectedTo(link.getN2());
        }

        public boolean isConnectedTo(Node node) {
            return n1.equals(node);
        }

        public int getWeight() {
            return n1.getWeight() + n2.getWeight();
        }

        public boolean contains(Node n) {
            return n1.equals(n) || n2.equals(n);
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
            return "{n1:" + n1.getIndex() + "(" + n1.getWeight() + ") n2:" + n2.getIndex() + "(" + n2.getWeight() + ") - w:" + getWeight() + "}";
        }

    }
    static class Node {

        private int index;
        private int weight = 1;

        public Node(int index) {
            this.index = index;
        }
        
        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        public Node weighted(int weight) {
            return new Node(index, weight);
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

    static class ExitNode extends Node {

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


    public static void main(String args[]) {
        GameEngine.play(new ShortestPathFinderAI(), System.in);
    }

}