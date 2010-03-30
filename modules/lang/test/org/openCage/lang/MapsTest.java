package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.clazz.Maps;
import org.openCage.lang.protocol.tuple.T2;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Mar 30, 2010
 * Time: 10:55:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class MapsTest {

    @Test
    public void testMaps() {


//        Map<String,Integer> map = Maps.toMap( arr("a"), new Integer[]{1});
        Map<String,Integer> map = Maps.toMap( Arrays.asList( T2.c( "a", 1) ));
    }

    public static <T> T[] arr( T [] ts ) {
        return null;
    }
}
