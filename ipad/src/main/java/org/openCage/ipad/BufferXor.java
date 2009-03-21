package org.openCage.ipad;

import org.openCage.utils.Math;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/


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

    private int nextMask( byte[] mask, int maskIdx, int sizeMask, byte last ) {

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
