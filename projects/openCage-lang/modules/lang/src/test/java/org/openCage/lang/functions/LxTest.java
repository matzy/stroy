package org.openCage.lang.functions;

import org.junit.Test;
import org.openCage.lang.structure.Ref;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: spf
 * Date: 12.01.11
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class LxTest {

    @Test
    public void testSimple() {
        F1<Integer,Integer> square = new Lx<Integer, Integer>(0) {{
                return_( x * x );
        }};

        assertEquals( 4, square.call( 2 ).intValue());
        assertEquals( 9, square.call( 3 ).intValue());
    }


    // Lx is not read yet, the constuctor is more complicated in the presence of final refs

//    @Test
//    public void testOnce() {
//        final Ref<Integer> ii = new Ref<Integer>(0);
//        F1<Integer,Integer> square = new LLx<Integer, Integer>(null, 0) {{
//            if (gard != null) {
//                ii.set( ii.get() + 1 );
//                return_( x * x );
//            };
//        }};
//
//        assertEquals( 4, square.call( 2 ).intValue());
//        assertEquals( 9, square.call( 3 ).intValue());
//
//        assertEquals( 2, ii.get().intValue() );
//
//    }
}
