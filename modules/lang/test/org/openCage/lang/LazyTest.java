/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.lang;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.FE0;
import org.openCage.lang.protocol.Lazy;

/**
 *
 * @author stephan
 */
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
