package org.openCage.lang.iterators;

import org.junit.Test;
import org.openCage.lang.iterators.ArrayCount;
import org.openCage.lang.iterators.Count;
import org.openCage.lang.structure.T2;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class IteratorsTest {

    @Test
    public void testSimple() {
        int tested = 0;        
        for ( Count<Character> ch : Count.count( Iterators.chars( "01234"))) {
            assertEquals( new Integer( ch.idx()), Integer.valueOf( ""+ ch.obj() )  );
            tested++;
        }

        assertEquals(5, tested);
    }

    @Test
    public void testWords() {

        int tested = 0;
        for ( ArrayCount<String> ch : ArrayCount.count( Iterators.words( "0 1   2   3 4 5 6 7 8 9 10    11\t12"))) {
            assertEquals( new Integer( ch.idx()), Integer.valueOf( ""+ ch.obj() )  );
            tested++;
        }

        assertEquals(13, tested);
    }

    @Test
    public void testLines() {

        int tested = 0;

        for (T2<String,String> pair : Iterators.parallel(
                Iterators.lines( "111\n\r2 2\n\n\n\n\n   3\r4\t4"),
                Arrays.asList( "111", "2 2", "   3", "4\t4"))) {
            assertEquals( pair.i0, pair.i1 );
            tested++;
        }

        assertEquals( 4, tested);

    }

    @Test
    public void testParallel() {
        for ( T2<Integer,String> pair : Iterators.parallel( Arrays.asList( 1,2,3), Arrays.asList("1", "2"))) {
            assertEquals( pair.i0.toString(), pair.i1);
        }
    }
}
