package org.openCage.artig;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 17, 2010
 * Time: 9:41:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class LicenceChecker {

    public static void canUse( String using, String src ) {

        if ( src.equals( "GPL2" )) {
            if ( !using.equals( "GPL2" )) {
                throw new IllegalArgumentException("only GPL2 can use GPL2");
            }
        }

        return;
    }
}
