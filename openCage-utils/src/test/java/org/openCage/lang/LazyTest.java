package org.openCage.lang;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Mar 25, 2009
 * Time: 9:53:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class LazyTest {

    interface Fct extends E1<String, String> {
    }

    class C {
        public void m( Fct f ) {
            new L1<String, String>( f ).get( "a" );
        }
    }


    @Test
    public void testL1Derive() {


    }
}
