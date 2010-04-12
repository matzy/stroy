package org.openCage.lang;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.openCage.lang.Lazy;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.FE0;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class LazyTest {

    @Test( expected=Unchecked.class)
    public void testExecption() {
        Lazy<String> lazy = new Lazy<String>( new FE0<String>() {
            public String call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        } );

        lazy.get();
    }

    @Test( expected=Unchecked.class)
    public void testGetTwiceExecption() {
        Lazy<String> lazy = new Lazy<String>( new FE0<String>() {
            public String call() throws Exception {
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
        final Lazy<String> lazy = new Lazy<String>( new FE0<String>() {
            public String call() throws Exception {
                Thread.sleep(1000);
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
