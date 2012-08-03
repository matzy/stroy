package org.openCage.ruleofthree.jtothree;

import com.google.inject.TypeLiteral;
import org.junit.Test;
import org.openCage.rei.ReiHashMap;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeHashMap;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.ThreeMap;
import org.openCage.ruleofthree.property.ImmuProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.openCage.ruleofthree.Threes.THREE;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class JfromThreeTest {

    @Test
    public void basics() {
        assertEquals(new Integer(1), new JfromThree().get(Integer.class, THREE("1")));
    }

    @Test
    public void list() {
        List<Three> th = new ArrayList<Three>();
        th.add( THREE("foo"));
        th.add( THREE("bar"));

        List<String> ll = new ArrayList<String>();
        ll.add( "foo");
        ll.add( "bar");


        assertEquals(ll, new JfromThree().get(new TypeLiteral<ArrayList<String>>() {
        }, THREE(th)));
    }

    @Test
    public void treeMap() {
        Map<ThreeKey,String> map = new ThreeHashMap<String>();
        map.put( new ThreeKey("foo"), "bar");
        map.put( new ThreeKey("baz"), "duh");

        Map<ThreeKey,Three> thmap = new ThreeHashMap<Three>();
        thmap.put( new ThreeKey("foo"), THREE("bar"));
        thmap.put( new ThreeKey("baz"), THREE("duh"));

        assertEquals( map, new JfromThree().get( new TypeLiteral<ThreeHashMap<String>>() {}, THREE(thmap)));

    }

    @Test
    public void otherMap() {
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1,"one");
        map.put(2,"two");

        List<Three> ll = new ArrayList<Three>();
        List<Three> one = new ArrayList<Three>();
        List<Three> two = new ArrayList<Three>();

        one.add( THREE("1"));
        one.add( THREE("one"));
        ll.add(THREE( one));

        two.add( THREE("2"));
        two.add( THREE("two"));
        ll.add(THREE( two));



        assertEquals( map, new JfromThree().get( new TypeLiteral<HashMap<Integer, String>>() {}, THREE(ll)));
    }

    @Test
    public void enumTest() {
        assertEquals( AEnum.three, new JfromThree().get( AEnum.class, THREE("three")));
    }

    @Test
    public void immuProp() {
        assertEquals( "foo", new JfromThree().get( new TypeLiteral<ImmuProperty<String>>() {}, THREE("foo")).get());
    }

    @Test
    public void testSimpleClass() {
        ThreeMap<Three> map = new ThreeHashMap<Three>();
        map.put( new ThreeKey("long"), THREE("7") );
        map.put( new ThreeKey("foo"), THREE("bar") );

        assertEquals( new AClass(), new JfromThree().get(AClass.class, THREE(map)));

    }

    @Test
    public void immuProp2() {
        ThreeMap<Three> map = new ThreeHashMap<Three>();
        map.put( new ThreeKey("long"), THREE("7") );
        map.put( new ThreeKey("foo"), THREE("bar") );

        assertEquals( new AClass(), new JfromThree().get( new TypeLiteral<ImmuProperty<AClass>>() {}, THREE(map)).get());
    }


}
