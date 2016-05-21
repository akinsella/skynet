package org.helyx;

import org.helyx.game.ai.shortpathfinder.ShortestPathFinderAI;
import org.helyx.graph.Graph;
import org.helyx.graph.Link;
import org.junit.Test;

import java.util.List;

import static org.helyx.game.GameEngine.play;
import static org.helyx.IOUtils.toInputStream;
import static org.helyx.TestUtils.readLinks;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TripleStarsTest {

    @Test
    public void should_validate_star_test() throws Exception {

        // Given
        String content =
            "38 79 3\n" +
            "28 36\n" +
            "0 2\n" +
            "3 34\n" +
            "29 21\n" +
            "37 35\n" +
            "28 32\n" +
            "0 10\n" +
            "37 2\n" +
            "4 5\n" +
            "13 14\n" +
            "34 35\n" +
            "27 19\n" +
            "28 34\n" +
            "30 31\n" +
            "18 26\n" +
            "0 9\n" +
            "7 8\n" +
            "18 24\n" +
            "18 23\n" +
            "0 5\n" +
            "16 17\n" +
            "29 30\n" +
            "10 11\n" +
            "0 12\n" +
            "15 16\n" +
            "0 11\n" +
            "0 17\n" +
            "18 22\n" +
            "23 24\n" +
            "0 7\n" +
            "35 23\n" +
            "22 23\n" +
            "1 2\n" +
            "0 13\n" +
            "18 27\n" +
            "25 26\n" +
            "32 33\n" +
            "28 31\n" +
            "24 25\n" +
            "28 35\n" +
            "21 22\n" +
            "4 33\n" +
            "28 29\n" +
            "36 22\n" +
            "18 25\n" +
            "37 23\n" +
            "18 21\n" +
            "5 6\n" +
            "19 20\n" +
            "0 14\n" +
            "35 36\n" +
            "9 10\n" +
            "0 6\n" +
            "20 21\n" +
            "0 3\n" +
            "33 34\n" +
            "14 15\n" +
            "28 33\n" +
            "11 12\n" +
            "12 13\n" +
            "17 1\n" +
            "18 19\n" +
            "36 29\n" +
            "0 4\n" +
            "0 15\n" +
            "0 1\n" +
            "18 20\n" +
            "2 3\n" +
            "0 16\n" +
            "8 9\n" +
            "0 8\n" +
            "26 27\n" +
            "28 30\n" +
            "3 4\n" +
            "31 32\n" +
            "6 7\n" +
            "37 1\n" +
            "37 24\n" +
            "35 2\n" +
            "0\n" +
            "18\n" +
            "28\n" +
            "37\n" +
            "35\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "33\n" +
            "34\n" +
            "35\n" +
            "36\n" +
            "22\n" +
            "21\n" +
            "29\n" +
            "30\n" +
            "31\n" +
            "32\n"
                ;

        // When
        Graph g = play(new ShortestPathFinderAI(), toInputStream(content));

        // Then
        readLinks(
                "37 35 35 28 2 0 3 0 4 0 33 28 34 28 35 23 36 28 22 18 21 18 29 28 30 28 31 28 32 28"
        ).stream()
                .forEach(link -> assertFalse(g.contains(link)));

    }

}