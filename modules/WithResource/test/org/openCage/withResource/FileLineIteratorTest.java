/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.withResource;

import java.io.File;
import org.junit.Test;
import org.openCage.withResource.impl.IterableFile;

import org.openCage.withResource.protocol.FileLineIterable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author stephan
 */
public class FileLineIteratorTest {

    @Test
    public void testStd() {
        String res = "woo";

        // pattern for how to use Iterable file
        FileLineIterable it = new IterableFile( new File(getClass().getResource( "lines.txt").getPath()));
        try { for ( String str : it ) {
                res = str;
            }
        } finally { it.close();}

        assertEquals( "dd", res);
    }

    @Test
    public void testLeaveEarly() {

        int i = 0;
        IterableFile it = new IterableFile( new File(getClass().getResource( "lines.txt").getPath()));

        try {
            for ( String str : it ) {
                i++;
                if ( i == 2) {
                    break;
                }
            }
        } finally {
            it.close();
        }

        assertEquals( 2, i);
        assertTrue( it.isClosed());

    }

    @Test
    public void testLeaveEarlyThrow() {

        int i = 0;
        IterableFile it = new IterableFile( new File(getClass().getResource( "lines.txt").getPath()));

        try {
        try {
            for ( String str : it ) {
                i++;
                if ( i == 2) {
                    throw new Error( "early" );
                }
            }
        } finally {
            it.close();
        }
        } catch ( Error e ) {}

        assertEquals( 2, i);
        assertTrue( it.isClosed());

    }
}
