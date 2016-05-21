package org.helyx;

import org.junit.Test;

import static org.helyx.TestUtils.readPath;
import static org.junit.Assert.assertEquals;

public class PathTest {

    @Test
    public void should_have_both_instances_equal() {
        assertEquals(readPath("21 22 22 18"), readPath("21 22 22 18"));
    }

}
