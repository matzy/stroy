package org.openCage.lang;

import org.openCage.lang.functions.F1;

import java.util.List;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
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
        private F1<Boolean,T> filter;
        private String postfix = "";

        public StringJoiner(List<T> list) {
            this.list = list;
        }

        public String toString() {
            if ( list == null || list.isEmpty() ) {
                return "";
            }

            StringBuffer buf = new StringBuffer( prefix );

            boolean one = false;

            for ( T str : list ) {

                if ( filter != null && filter.call( str )) {
                    continue;
                }

                one = true;

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

            if ( !one ) {
                return "";
            }

            return buf.toString() + postfix;
        }

        public StringJoiner prefix( String prefix ) {
            this.prefix = prefix;
            return this;
        }

        public StringJoiner postfix( String postfix ) {
            this.postfix = postfix;
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

        public StringJoiner skip( F1<Boolean, T> filter ) {
            this.filter = filter;
            return this;
        }
    }
}
