package org.openCage.util.io;

import org.openCage.util.logging.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

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


public class LineReaderIterator  implements Iterator<String> {
    private BufferedReader reader;
    private String         line;


    public LineReaderIterator( BufferedReader reader ) throws IOException {
        this.reader = reader;
        this.line   = reader.readLine();
    }

    public boolean hasNext() {
        return line != null;
    }

    public String next() {
        String oldLine = line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException("read not readble anymore" );
        }
        return oldLine;
    }

    public void remove() {
        throw new UnsupportedOperationException( "this iterator has no remove" );
    }

    public void close() {
        if ( reader != null ) {
            try {
                reader.close();
            } catch ( IOException e ) {
                Log.warning( "close threw error " + e  );
            }
            reader = null;
        }
    }

    public static void close( LineReaderIterator it ) {
        if ( it != null ) {
            it.close();
        }
    }

}
