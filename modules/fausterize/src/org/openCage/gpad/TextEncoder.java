package org.openCage.gpad;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 17, 2009
 * Time: 9:30:59 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TextEncoder<T> {
    String encode( T ch );

    T decode( String line );
}
