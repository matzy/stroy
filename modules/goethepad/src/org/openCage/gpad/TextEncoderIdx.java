package org.openCage.gpad;

import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 23, 2009
 * Time: 6:08:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TextEncoderIdx<T> {

    String encode( T item, int idx );
    T      decode( String line, int idx );

    void   setPad( URI path );
}
