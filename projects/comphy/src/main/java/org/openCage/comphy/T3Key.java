package org.openCage.comphy;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/30/12
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class T3Key implements Comparable<T3Key>{
    private final String str;

    public T3Key(String key) {
        this.str = key;
    }

    public static T3Key valueOf(String key) {
        return new T3Key( key );
    }

    @Override
    public int compareTo(T3Key t3Key) {
        return str.compareTo( t3Key.str );
    }

    @Override
    public String toString() {
        return str;
    }
}
