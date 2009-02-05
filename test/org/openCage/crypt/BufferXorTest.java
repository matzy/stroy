package org.openCage.crypt;

import org.junit.Test;
import org.junit.Assert;


public class BufferXorTest {

    @Test
    public void testNext() {
        BufferXor bxor = new BufferXor();

        byte[] mask = new byte[3];
        mask[0] = 0;
        mask[1] = 0;
        mask[2] = 0;

        try {
            bxor.nextMask( mask, 0, 3, (byte)0 );
            bxor.nextMask( mask, 1, 3, (byte)0 );
            bxor.nextMask( mask, 2, 3, (byte)0 );
            bxor.nextMask( mask, 1001, 3, (byte)0 );
        } catch ( Error e ) {
            // expected
        }

        Assert.assertEquals( 1, bxor.nextMask( mask, 0, 3, (byte)1 ));
        Assert.assertEquals( 2, bxor.nextMask( mask, 1, 3, (byte)1 ));
        Assert.assertEquals( 0, bxor.nextMask( mask, 1001, 3, (byte)1 ));

        mask[2] = 5;
        Assert.assertEquals( 2, bxor.nextMask( mask, 0, 3, (byte)0 ));
        Assert.assertEquals( 2, bxor.nextMask( mask, 1, 3, (byte)0 ));
        Assert.assertEquals( 2, bxor.nextMask( mask, 1001, 3, (byte)0 ));

    }

    @Test
    public void testBufferXor() {
        byte[] mask = new byte[4];
        mask[0] = 0;
        mask[1] = 1;
        mask[2] = 1;
        mask[3] = 2;

        byte[] src = new byte[3];
        src[0] = 'A';
        src[1] = 'B';
        src[2] = 'C';

        byte[] tgt1 = new byte[3];
        byte[] tgt2 = new byte[3];

        BufferXor bxor = new BufferXor();

        bxor.xor( src, tgt1, 3, mask, 4, 1001 );
        bxor.xor( tgt1, tgt2, 3, mask, 4, 1001 );

        Assert.assertArrayEquals( src, tgt2 );


    }
}
