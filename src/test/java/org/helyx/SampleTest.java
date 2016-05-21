package org.helyx;

import org.helyx.game.ai.basic.BasicAI;
import org.helyx.game.GameEngine;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.helyx.IOUtils.toInputStream;
import static org.helyx.TestUtils.readLinks;
import static org.helyx.game.GameEngine.play;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SampleTest {

    @Test
    public void should_validate_sample_test() throws Exception {

        // Given
        String content =
            "4 4 1\n" +
            "0 1\n" +
            "0 2\n" +
            "1 3\n" +
            "2 3\n" +
            "3\n" +
            "0\n" +
            "2\n";

        // When
        Graph g = play(new BasicAI(), toInputStream(content));

        // Then
        readLinks("1 3 2 3").stream()
                .forEach(link -> assertFalse(g.contains(link)));
    }

}