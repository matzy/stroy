package org.openCage.utils.lang;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;



public class MathTest {

    @Test
    public void testXor() {
        assertTrue( (byte)101 != Math.xor( (byte)101, (byte)50 ) );
        assertEquals( (byte)101, Math.xor( Math.xor( (byte)101, (byte)50 ), (byte)50));
        assertEquals( (byte)0xFE, Math.xor( Math.xor( (byte)0xFE, (byte)50 ), (byte)50));

        for ( int i = -127; i < 128; ++i ) {
            for ( int j = -127; j < 128; ++j ) {
                if ( j != 0 ) {
                    assertTrue( "" + i + " xor " + j, (byte)i != Math.xor( (byte)i, (byte)j ) );
                    assertEquals( (byte)i, Math.xor( Math.xor( (byte)i, (byte)j ), (byte)j));
                }
            }
        }
    }
}
