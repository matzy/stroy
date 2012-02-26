package org.openCage.lang.functions;

import org.junit.Test;
import org.openCage.lang.errors.Unchecked;

import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 2/12/12
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionHandlingTest {

    @Test( expected = Unchecked.class )
    public void testTunnel() {
        F0<Void> fct = new F0E<Void>(){
            @Override
            public Void callWithException() throws Exception {
                throw new FileNotFoundException("foo");
            }
        };

        fct.call();
    }
}
