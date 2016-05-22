package org.helyx.scenarios;

import org.helyx.game.ai.shortestpathfinder.ShortestPathFinderAI;
import org.helyx.graph.Graph;
import org.junit.Test;

import static org.helyx.utils.IOUtils.toInputStream;
import static org.helyx.utils.TestUtils.readLinks;
import static org.helyx.game.GameEngine.play;
import static org.junit.Assert.assertFalse;

public class StarTest {

    @Test
    public void should_validate_star_test() throws Exception {

        // Given
        String content =
                "12 23 1\n" +
                "11 6\n" +
                "0 9\n" +
                "1 2\n" +
                "0 1\n" +
                "10 1\n" +
                "11 5\n" +
                "2 3\n" +
                "4 5\n" +
                "8 9\n" +
                "6 7\n" +
                "7 8\n" +
                "0 6\n" +
                "3 4\n" +
                "0 2\n" +
                "11 7\n" +
                "0 8\n" +
                "0 4\n" +
                "9 10\n" +
                "0 5\n" +
                "0 7\n" +
                "0 3\n" +
                "0 10\n" +
                "5 6\n" +
                "0\n" +
                "11\n" +
                "5\n" +
                "4\n" +
                "3\n" +
                "2\n" +
                "1\n" +
                "10\n" +
                "9\n" +
                "8\n" +
                "7\n" +
                "6\n";

        // When
        Graph g = play(new ShortestPathFinderAI(), toInputStream(content));

        // Then
        readLinks("11 6 5 0 4 0 3 0 2 0 1 0 10 0 9 0 8 0 7 0 6 0").stream()
                .forEach(link -> {
                    assertFalse(g.contains(link));
                });
    }

}