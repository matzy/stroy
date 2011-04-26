package org.openCage.stjx;

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
public class Names {

    private static Pattern invalidClassNames = Pattern.compile( "String|List");
    private static Pattern validTagNames = Pattern.compile( "([a-z]|[A-Z])*");
//    private static Pattern invalidTagNames = Pattern.compile( "");

    public static String getClassName( String str ) {
        String ret = Strings.toFirstUpper( str );

        while( invalidClassNames.matcher( ret ).matches() ) {
            ret = "C" + ret;
        }

        return ret;
    }

    public static void validateTageName( String str ) {

        if ( !validTagNames.matcher( str ).matches() ) {
            throw new IllegalArgumentException( "not a valid tagname: " + str );
        }
    }
}
