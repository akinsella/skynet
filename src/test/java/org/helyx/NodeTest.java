package org.helyx;

import org.helyx.game.graph.ExitNode;
import org.helyx.graph.Link;
import org.helyx.graph.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTest {

    @Test
    public void should_have_both_instances_equal() {
        assertEquals(new Node(35), new Node(35));
    }

    @Test
    public void should_have_both_instances_equal_even_with_different_sub_types() {
        assertEquals(new Node(28), new ExitNode(28));
    }

}
