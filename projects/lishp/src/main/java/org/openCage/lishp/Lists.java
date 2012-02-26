package org.openCage.lishp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 1/18/12
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Lists {
    public static <T> List<List<Object>>  split( List<Object> list, T ... separators ) {
        List<List<Object>> ret = new ArrayList<List<Object>>();
        int from = 0;
        int to = 0;
        for ( ; to < list.size(); to++ ) {
            for ( T sep : separators ) {
                if ( list.get(to).equals( sep )) {
                    ret.add( list.subList(from,to));
                    from = to;
                    break;
                }
            }
        }
        ret.add( list.subList(from,list.size()));

        return ret;
    }

}
