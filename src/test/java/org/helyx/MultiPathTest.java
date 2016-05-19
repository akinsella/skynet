package org.helyx;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.helyx.GameEngine.play;
import static org.helyx.IOUtils.toInputStream;
import static org.junit.Assert.assertEquals;

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
        GameState gs = play(new BasicAI(), toInputStream(content));

        // Then
        assertEquals(asList(new Link(1, 3), new Link(2, 3)), gs.getCutLinks());
    }

}