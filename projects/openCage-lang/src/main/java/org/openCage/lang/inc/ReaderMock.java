package org.openCage.lang.inc;

import java.io.IOException;
import java.io.Reader;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***** END LICENSE BLOCK *****/

public class ReaderMock extends Reader {

    public static char[] txt = ("'But who is to give the prizes?' quite a chorus of voices asked.\n" +
            "'Why, SHE, of course,' said the Dodo, pointing to Alice with one finger; and the whole party at once crowded round her, calling out in a confused way, 'Prizes! Prizes!'\n" +
            "Alice had no idea what to do, and in despair she put her hand in her pocket, and pulled out a box of comfits, (luckily the salt water had not got into it), and handed them round as prizes. There was exactly one a-piece all round.\n" +
            "'But she must have a prize herself, you know,' said the Mouse.\n" +
            "'Of course,' the Dodo replied very gravely. 'What else have you got in your pocket?' he went on, turning to Alice.\n" +
            "'Only a thimble,' said Alice sadly.\n" +
            "'Hand it over here,' said the Dodo.").toCharArray();


    private int ptr = 0;
    private boolean open = true;
    private int closeAt;

    public ReaderMock( int closedAt ) {
        this.closeAt = closedAt;
        if ( closedAt < 0 ) {
            this.closeAt = 1000000;
        }
    }

    /**
     * Reads characters into a portion of an array.  This method will block
     * until some input is available, an I/O error occurs, or the end of the
     * stream is reached.
     *
     * @param      cbuf  Destination buffer
     * @param      off   Offset at which to start storing characters
     * @param      len   Maximum number of characters to read
     *
     * @return     The number of characters read, or -1 if the end of the
     *             stream has been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if ( ptr + len >= closeAt ) {
            throw new IOException( "random io exception" );
        }

        if ( !open ) {
            throw new IOException( "reader already closed" );
        }

        int i = 0;
        for ( ; ptr < txt.length && len > 0; ptr++, len--, i++) {
            cbuf[off+i] = txt[ptr];
        }

        return i == 0 ? -1 : i;
    }

    /**
     * Closes the stream and releases any system resources associated with
     * it.  Once the stream has been closed, further read(), ready(),
     * mark(), reset(), or skip() invocations will throw an IOException.
     * Closing a previously closed stream has no effect.
     *
     * @exception  IOException  If an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        open = false;
    }

    public boolean isClosed() {
        return !open;
    }
}
