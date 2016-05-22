package org.helyx.scenarios;

import org.helyx.game.ai.basic.BasicAI;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.junit.Test;

import static org.helyx.utils.IOUtils.toInputStream;
import static org.helyx.game.GameEngine.play;
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