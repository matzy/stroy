package org.openCage.utils.unchecked;

import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Mar 13, 2009
 * Time: 2:03:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class UncheckedTest {

    @Test
    public void testSelection() {
        Unchecked.unchecked( new FileNotFoundException());

        try {
            throw new Error("woo");
        } catch( Error err ) {
            System.out.println(err.getMessage());            
        }


        try {
            throw new Throwable("foo");
        } catch ( Throwable tt ) {
            System.out.println(tt.getMessage());            
        }


    }
}
