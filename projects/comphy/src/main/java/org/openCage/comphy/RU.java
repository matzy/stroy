package org.openCage.comphy;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 9:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class RU {
    public static Readable r( String str ) {
        return new RString( str );
    }

    public static String es( Readable rdbl ) {
        return ((RString)rdbl).get();
    }
}
