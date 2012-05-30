package org.openCage.comphy;

import org.junit.Test;
import org.openCage.lang.structure.Ref;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/24/12
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MapPropertyTest {

    @Test
    public void testPut() {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        map.put( Key.valueOf("duh"), new StringProperty("foo"));

        assertEquals( 1, count.get().intValue() );

    }

    @Test
    public void testElemModify () {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty str = new StringProperty("foo");
        map.put( Key.valueOf("duh"), str );

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        str.set("bar");


        assertEquals( 1, count.get().intValue() );

    }

    @Test
    public void testPutOverride () {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty str = new StringProperty("foo");
        map.put( Key.valueOf("duh"), str );
        map.put( Key.valueOf("duh"), new StringProperty("new") );

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });


        str.set("bar");


        assertEquals(0, count.get().intValue());

    }

    @Test
    public void testPutAll() {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty stra = new StringProperty("aa");
        map.put( Key.valueOf("a"), stra );
        StringProperty strb = new StringProperty("b");
        map.put( Key.valueOf("b"), strb );

        Map<Key,StringProperty> map2 = new HashMap<Key, StringProperty>();
        StringProperty strb2 = new StringProperty("b2");
        map2.put( Key.valueOf("b"), strb2 );
        StringProperty strc = new StringProperty("c");
        map2.put( Key.valueOf("c"), strc );

        map.putAll(map2);

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });



        stra.set("22"); // event
        assertEquals(1, count.get().intValue());

        strb.set("22"); // no event
        assertEquals(1, count.get().intValue());

        strb2.set("22"); // event
        assertEquals(2, count.get().intValue());

        strc.set("22"); // event
        assertEquals(3, count.get().intValue());

    }

    @Test
    public void testClear() {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty str = new StringProperty("foo");
        map.put( Key.valueOf("duh"), str );
        map.clear();

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });


        str.set("bar");

        assertEquals(0, count.get().intValue());
    }
}
