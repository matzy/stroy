package org.openCage.lang;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jul 23, 2010
 * Time: 7:03:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Duck {

    public static <T> T duck( Class<T> interf, Object obj ) {

        return DuckType.coerce( obj ).to( interf );
    }
}
