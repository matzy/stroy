package org.openCage.lang;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class StringsTest {

    @Test
    public void testJoinNonStrings() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "1, 2, 3", Strings.join( ints ).toString());
    }
}
