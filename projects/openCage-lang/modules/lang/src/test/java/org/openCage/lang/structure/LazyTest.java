package org.openCage.lang.structure;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.openCage.lang.structure.Lazy;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F0;

/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class LazyTest {

    @Test( expected=Unchecked.class)
    public void testExecption() {
        Lazy<String> lazy = new Lazy<String>( new F0<String>() {
            public String call()  {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        } );

        lazy.get();
    }

    @Test( expected=Unchecked.class)
    public void testGetTwiceExecption() {
        Lazy<String> lazy = new Lazy<String>( new F0<String>() {
            public String call() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        } );

        try {
            lazy.get();
        } catch ( Error ex ) {}

        lazy.get();

    }

    @Test
    public void testMultithreading() {
        final Lazy<String> lazy = new Lazy<String>( new F0<String>() {
            public String call() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw Unchecked.wrap(e);
                }
                System.out.println("evaluating");
                return "a big a slow result";
            }
        } );

        for ( int  i = 0; i < 100; ++i ) {
            new Thread(new Runnable() {
                public void run() {
                    lazy.get();
                }
            } ).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LazyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
