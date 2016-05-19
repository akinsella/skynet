package org.helyx;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.helyx.GameEngine.play;
import static org.helyx.IOUtils.toInputStream;
import static org.junit.Assert.assertEquals;

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
        GameState gs = play(new BasicAI(), toInputStream(content));

        // Then
        assertEquals(asList(new Link(1, 2)), gs.getCutLinks());
    }

}