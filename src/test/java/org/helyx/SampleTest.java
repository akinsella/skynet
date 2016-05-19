package org.helyx;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

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
        GameState gs = GameEngine.play(new BasicAI(), IOUtils.toInputStream(content));

        // Then
        assertEquals(asList(new Link(1, 3), new Link(2, 3)), gs.getCutLinks());
    }

}