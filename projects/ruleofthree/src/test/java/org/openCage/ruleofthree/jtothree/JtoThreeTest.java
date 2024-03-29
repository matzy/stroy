package org.openCage.ruleofthree.jtothree;

import com.google.inject.TypeLiteral;
import org.junit.Test;
import org.openCage.kleinod.collection.Ref;
import org.openCage.kleinod.collection.ReiHashMap;
import org.openCage.kleinod.type.TypeLit;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeHashMap;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.ThreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.openCage.ruleofthree.Threes.THREE;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class JToThreeTest {

    @Test
    public void testBasic() {
        assertEquals( THREE("1"), new JToThree().toThree( 1L ));
    }

    @Test
    public void list() {
        List<String>  ll = new ArrayList<String>();
        ll.add( "foo");
        ll.add( "bar");

        List<Three> th = new ArrayList<Three>();
        th.add( THREE("foo"));
        th.add( THREE("bar"));

        assertEquals(THREE(th), new JToThree().toThree(ll));
    }

    @Test
    public void treeMap() {
        Map<ThreeKey,String> map = new ThreeHashMap<String>();
        map.put( new ThreeKey("foo"), "bar");
        map.put( new ThreeKey("baz"), "duh");

        Map<ThreeKey,Three> thmap = new ThreeHashMap<Three>();
        thmap.put( new ThreeKey("foo"), THREE("bar"));
        thmap.put( new ThreeKey("baz"), THREE("duh"));

        assertEquals(THREE(thmap), new JToThree().toThree(map));
    }

    @Test
    public void otherMap() {
        Map<Integer,String> map = new ReiHashMap<Integer, String>( new TypeLit<Integer>() {});
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



        assertEquals( THREE(ll), new JToThree().toThree( map));
    }

    @Test
    public void ref() {
        assertEquals(THREE("foo"),new JToThree().toThree( new Ref<String>("foo")));
    }

    @Test
    public void enumTest() {
        assertEquals(THREE("two"), new JToThree().toThree(AEnum.two));
    }

    @Test
    public void testSimpleClass() {
        ThreeMap<Three> map = new ThreeHashMap<Three>();
        map.put( new ThreeKey("long"), THREE("7") );
        map.put( new ThreeKey("foo"), THREE("bar") );

        AClass ac = new AClass();
        assertEquals( THREE(map), new JToThree().toThree( ac ));

    }

}
