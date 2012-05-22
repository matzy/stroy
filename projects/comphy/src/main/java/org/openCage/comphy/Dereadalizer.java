package org.openCage.comphy;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Dereadalizer<T> {

    T fromString( String str );
    T fromList( List<Readable> lst );
    T fromMap( Map<String,Readable> map );

}
