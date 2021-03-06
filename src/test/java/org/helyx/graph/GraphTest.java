package org.helyx.graph;

import org.helyx.game.graph.GraphBuilder;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.helyx.graph.Node;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.helyx.game.graph.GraphBuilder.buildGraph;
import static org.helyx.game.graph.GraphBuilder.weightNodeByLinkCount;
import static org.helyx.utils.TestUtils.createScanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphTest {

    String input =  "28 36 0 2 3 34 29 21 37 35 28 32 0 10 37 2 4 5 13 14 34 35 27 19 28 34 30 31 18 26 0 9 7 8 18 " +
            "24 18 23 0 5 16 17 29 30 10 11 0 12 15 16 0 11 0 17 18 22 23 24 0 7 35 23 22 23 1 2 0 13 18 27 25 26 32 " +
            "33 28 31 24 25 28 35 21 22 4 33 28 29 36 22 18 25 37 23 18 21 5 6 19 20 0 14 35 36 9 10 0 6 20 21 0 3 33 " +
            "34 14 15 28 33 11 12 12 13 17 1 18 19 36 29 0 4 0 15 0 1 18 20 2 3 0 16 8 9 0 8 26 27 28 30 3 4 31 32 6 7 " +
            "37 1 37 24 35 2";

    @Test
    public void should_remove_cut_link_from_uncut_links() {

        Graph graph = buildGraph(createScanner(input));

        Link linkToCut = new Link(35, 28);

        assertTrue(graph.contains(linkToCut));

        graph.cutLink(linkToCut);

        assertTrue(!graph.contains(linkToCut));
    }

    @Test
    public void should_remove_cut_link_from_uncut_links_even_when_direction_is_reversed() {

        Graph graph = buildGraph(createScanner(input));

        Link linkToCut = new Link(35, 28);

        assertTrue(graph.contains(linkToCut));

        graph.cutLink(linkToCut);

        assertTrue(!graph.contains(linkToCut));
    }

    @Test
    public void should_build_weighted_graph() throws Exception {

        // Given
        String content =
                "3 2 1\n" +
                "0 1\n" +
                "1 2\n" +
                "2\n" +
                "1\n";

        // When
        Graph g = GraphBuilder.buildGraph(createScanner(content));

        g.computeWeights(weightNodeByLinkCount(g.getLinks()));

        // Then
        Link l0 = g.getLinks().get(0);
        assertEquals(1, l0.getN1().getWeight());
        assertEquals(2, l0.getN2().getWeight());

        Link l1 = g.getLinks().get(1);
        assertEquals(2, l1.getN1().getWeight());
        assertEquals(1, l1.getN2().getWeight());
    }

}

