package org.openCage.comphy;

import org.junit.Test;
import org.openCage.lang.structure.Ref;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/24/12
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ComplexPropTest {

    @Test
    public void testComplex() {

        MapProperty<ListProperty<StringProperty>> map = new MapProperty<ListProperty<StringProperty>>();

        StringProperty str = new StringProperty("foo");
        ListProperty<StringProperty> list = new ListProperty<StringProperty>( Key.valueOf("elem"), new StringPropertyDereader());
        list.add( str );
        map.put(Key.valueOf("onw"), list);

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        str.set("bar");

        assertEquals(1, count.get().intValue());


    }
}
