package org.openCage.kleinod.hash;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/30/12
 * Time: 8:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class StdHash {

    public static int hash( int sofar, int next ) {
        return 31 * sofar + next;
    }

    public static int hash( int sofar, Object obj ) {
        return hash( sofar, obj == null ? 0 : obj.hashCode() );
    }

    public static int hash( Object obj ) {
        return hash( 0, obj == null ? 0 : obj.hashCode() );
    }

    public static int hash( Object a, Object b ) {
        return hash( hash(a), hash(b));
    }

}
