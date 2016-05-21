package org.helyx;

import org.helyx.game.ai.basic.BasicAI;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.helyx.game.GameEngine.play;
import static org.helyx.IOUtils.toInputStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SimpleTest {

    @Test
    public void should_validate_simple_test() throws Exception {

        // Given
        String content =
            "3 2 1\n" +
            "0 1\n" +
            "1 2\n" +
            "2\n" +
            "1\n";

        // When
        Graph g = play(new BasicAI(), toInputStream(content));

        // Then
        assertFalse(g.contains(new Link(1, 2)));
    }

}