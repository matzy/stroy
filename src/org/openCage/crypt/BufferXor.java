package org.openCage.crypt;

import org.openCage.utils.lang.Math;

public class BufferXor {

    public void xor( byte[] src, byte[] tgt, int sizeSrc, byte[] mask, int sizeMask, int start ) {

        int pdx       = start;
        byte lastByte = 0;

        for( int idx = 0; idx < sizeSrc; ++idx ) {

            pdx      = nextMask( mask, pdx, sizeMask, lastByte );
            lastByte = mask[ pdx ];

            tgt[idx] = Math.xor( src[idx], mask[pdx ]);
        }
    }

    public int nextMask( byte[] mask, int maskIdx, int sizeMask, byte last ) {

        int original = maskIdx;        
        maskIdx++;

        while ( original != maskIdx ) {
            
            if ( maskIdx >= sizeMask ) {
                maskIdx = 0;
            } else if ( mask[ maskIdx] != last ) {
                return maskIdx;
            } else {
                ++maskIdx;
            }
        }

        throw new Error( "bad mask file" );
    }
}
