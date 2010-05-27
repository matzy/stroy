package org.openCage.lang;

import com.sun.tools.javac.util.Pair;
import org.openCage.lang.Lazy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 26, 2010
 * Time: 10:28:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThePodi {

    private static Map<Class, Lazy<Object>> map = new HashMap<Class, Lazy<Object>>();
    private static Map<Pair<Class,String>, Lazy<Object>> map2 = new HashMap<Pair<Class, String>, Lazy<Object>>();

    public static <T> T get(Class<T> c) {
        return (T)map.get(c).get();
    }

    public static <T> T get(Class<T> c, String name ) {
        return (T)map2.get( new Pair<Class,String>(c,name) ).get();
    }

    public static <T> void set(Class<T> c, Lazy<T> ll ) {
        map.put( c, (Lazy)ll );
    }

    public static <T> void set(Class<T> c, String name, Lazy<T> ll ) {
        map2.put( new Pair<Class,String>(c,name), (Lazy)ll );
    }
}
