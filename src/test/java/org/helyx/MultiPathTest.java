package org.helyx;

import org.helyx.game.ai.basic.BasicAI;
import org.helyx.graph.Graph;
import org.junit.Test;

import static org.helyx.utils.IOUtils.toInputStream;
import static org.helyx.utils.TestUtils.readLinks;
import static org.helyx.game.GameEngine.play;
import static org.junit.Assert.assertFalse;

public class MultiPathTest {

    @Test
    public void should_validate_multi_path_test() throws Exception {

        // Given
        String content =
            "4 4 1\n" +
            "1 3\n" +
            "2 3\n" +
            "0 1\n" +
            "0 2\n" +
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