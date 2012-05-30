package org.openCage.comphy;

import org.junit.Test;
import org.openCage.lang.structure.Ref;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/23/12
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringPropertyTest {

    @Test
    public void testChangeEvent() {
        StringProperty prop = new StringProperty( "one" );

        final Ref<Integer> count = new Ref<Integer>(1);

        prop.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        prop.set("two");

        assertEquals( 2, count.get().intValue() );
    }
}
