package org.openCage.lang;

import org.openCage.lang.functions.F1;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 9, 2010
 * Time: 9:16:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Strings {

    public static String toFirstLower( String name ) {
        return "" + name.toLowerCase().charAt(0) + name.substring(1);
    }

    public static String toFirstUpper(String name) {
        return "" + name.toUpperCase().charAt(0) + name.substring(1);
    }


    public static <T> StringJoiner<T> join( List<T> list ) {
        return new StringJoiner<T>( list );
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static class StringJoiner<T> {

        private final List<T> list;
        private String sep = ", ";
        private String prefix = "";
        private F1<String,T> trans;
        boolean comma = false;

        public StringJoiner(List<T> list) {
            this.list = list;
        }

        public String toString() {
            if ( list.isEmpty() ) {
                return "";
            }

            StringBuffer buf = new StringBuffer( prefix );

            for ( T str : list ) {
                if ( comma ) {
                    buf.append( sep );
                }
                comma = true;                

                if ( trans == null ) {
                    buf.append( str );
                } else {
                    buf.append( trans.call( str ));
                }

            }

            return buf.toString();
        }

        public StringJoiner prefix( String prefix ) {
            this.prefix = prefix;
            return this;
        }

        public StringJoiner separator( String sep ) {
            this.sep = sep;
            return this;
        }

        public StringJoiner trans( F1<String,T> trans ) {
            this.trans = trans;
            return this;
        }

        public StringJoiner startWithSeparator( boolean start ) {
            comma = start;
            return this;
        }
    }
}
