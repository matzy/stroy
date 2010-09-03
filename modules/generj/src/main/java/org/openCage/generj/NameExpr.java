package org.openCage.generj;

import org.openCage.lang.Strings;

import java.util.regex.Pattern;


/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
public class NameExpr implements Callble {

    private static Pattern valid = Pattern.compile( "([a-z]*[A-Z]*)*"); // TODO expand

    private final String str;

    public NameExpr( String str ) {

        if ( !valid.matcher( str ).matches() ) {
            throw new IllegalArgumentException( "not a valid java name: <" + str + ">" );
        }

        this.str = str;
    }

    public String toString() {
        return str;
    }

    public static NameExpr n( String str ) {
        return new NameExpr( str );
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static NameExpr NAME( String name ) {
        return new NameExpr( name );
    }

    public static NameExpr SETTER( String name ) {
        return new NameExpr( "set" + Strings.toFirstUpper( name ));
    }

    public static NameExpr GETTER( String name ) {
        return new NameExpr( "get" + Strings.toFirstUpper( name ));
    }

    public static NameExpr NULL = new NameExpr( "null" );
    public static NameExpr TRUE = new NameExpr( "true" );
    public static NameExpr FALSE = new NameExpr( "false" );

}
