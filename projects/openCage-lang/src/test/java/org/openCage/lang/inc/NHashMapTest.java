package org.openCage.lang.inc;

import org.junit.Test;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 7:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class NHashMapTest {

    @Test( expected = IllegalArgumentException.class )
    public void hah() {

        Map<String,Integer> map = new NHashMap<String, Integer>(String.class, Integer.class );

        map.get( new Integer(7));
    }

    @Test
    public void good() {
        Map<String,Integer> map = new NHashMap<String, Integer>(String.class, Integer.class );

        map.get( "duh");

    }
}
