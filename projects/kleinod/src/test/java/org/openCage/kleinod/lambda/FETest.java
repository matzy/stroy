package org.openCage.kleinod.lambda;

import org.junit.Test;
import org.openCage.kleinod.errors.Unchecked;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/29/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class FETest {

    @Test( expected = Unchecked.class)
    public void tunnel() {
        new F0E<Void>() {
            @Override
            public Void callWithException() throws Exception {
                throw new IllegalArgumentException();
            }
        }.call();
    }
}
