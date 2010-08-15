package org.openCage.stjx;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 14, 2010
 * Time: 12:07:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Keywords {

    public static boolean isAllowed( String str ) {
        return !str.equals( "list" ) && !str.equals( "element" );
    }
}
