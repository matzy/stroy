package org.openCage.lispaffair;

import org.openCage.lishp.BuildinFunction;
import org.openCage.lishp.Function;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 12/4/11
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildinString {

    public static Function split = new BuildinFunction( ":split" ){
        public Object apply( List<Object> lst, Environment env ) {

            Object str = lst.get(1);
            Object regexp = lst.get(2);

            if (!( str instanceof String )) {
                System.err.println("split needs a string not " + str );
                return null;
            }

            if (!( regexp instanceof String )) {
                System.err.println("split needs a string not " + regexp );
                return null;
            }

            List ret = new LinkedList();
            
            for ( String elem : ((String)str).split( (String)regexp )) {
                ret.add( elem );
            }
            
            return ret;
        }

        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "split" );
            return toAppendTo;
        }
    };
}
