package org.helyx;

import org.helyx.game.graph.ExitNode;
import org.helyx.graph.Link;
import org.helyx.graph.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkTest {

    @Test
    public void should_have_both_instances_equal() {
        assertEquals(new Link(35, 28), new Link(35, 28));
    }

}
