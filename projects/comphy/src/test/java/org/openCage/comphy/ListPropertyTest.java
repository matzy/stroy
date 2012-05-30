package org.openCage.comphy;

import org.junit.Test;
import org.openCage.lang.structure.Ref;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/23/12
 * Time: 6:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListPropertyTest {

    @Test
    public void testAdd() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        list.add( new StringProperty("foo"));

        assertEquals( 1, count.get().intValue() );

    }

    @Test
    public void testModifyElement() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        list.add( elem );

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        elem.set("bar");


        assertEquals(1, count.get().intValue());
    }

    @Test
    public void testAddAll() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        ListProperty<StringProperty> sub = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        sub.add( elem );
        StringProperty elem2 = new StringProperty("bar");
        sub.add(elem2);

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        list.addAll(sub);
        assertEquals(1, count.get().intValue());

        elem.set("duh");
        elem2.set("woo");


        assertEquals(3, count.get().intValue());
    }

    @Test
    public void testRemovedElementShouldNotUpdate() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        list.add( elem );
        list.remove( elem );

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        elem.set("bar");


        assertEquals(0, count.get().intValue());
    }

    @Test
    public void testAddRemoveGame1() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        list.add( elem );
        list.add( elem );
        list.remove( elem );

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        elem.set("bar");


        assertEquals(1, count.get().intValue());
    }

    @Test
    public void testAddRemoveGame2() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        list.add( elem );
        list.add( elem );
        list.removeAll( Collections.singleton( elem ));

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        elem.set("bar");


        assertEquals(0, count.get().intValue());
    }

    @Test
    public void testRemovedStrangeElem() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        list.add( elem );
        list.remove( new Integer(7) );
    }

    @Test
    public void testListRemove() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("a");

        list.removeAll(Collections.singleton("a"));

        assertEquals(1,list.size());

    }

    @Test
    public void testClear() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        list.add( elem );
        list.add( elem );
        list.clear();

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        elem.set("bar");

        assertEquals(0, count.get().intValue());
    }

    @Test
    public void testRetainAll() {
        ListProperty<StringProperty> list = new ListProperty<StringProperty>(Key.valueOf("e"), new StringPropertyDereader());
        StringProperty elem = new StringProperty("foo");
        list.add( elem );
        StringProperty elem2 = new StringProperty("bar");
        list.add( elem2 );

        list.retainAll(Collections.singleton(elem));

        final Ref<Integer> count = new Ref<Integer>(0);

        list.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        elem.set("22");
        assertEquals(1, count.get().intValue()); // observed

        elem2.set("22");
        assertEquals(1, count.get().intValue()); // not observed
    }

}