package org.openCage.lang.clazz;


import org.openCage.lang.protocol.tuple.T2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Mar 30, 2010
 * Time: 10:50:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Maps {

    public static <A,B> Map<A,B> toMap( List<T2<A,B>> keyvals  ) {
        HashMap<A,B> map =  new HashMap<A,B>();

        for ( T2<A,B> pair : keyvals ) {
            map.put( pair.i0, pair.i1 );
        }

        return map;
        
    }
}
