package org.openCage.util.www.wikidot;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class WikiDotGen {

    public static String ancor( String name ) {
        return "[[# " + normalize( name ) + "]]";
    }

    public static String ancor( int num ) {
        return ancor( "" + num );
    }

    public static String link( String name, String text ) {
        return "[#" + normalize( name ) + " " + text + "]";
    }

    public static String link( String page, String name, String text ) {
        return "["+ page +"#" + normalize( name ) + " " + text + "]";
    }

    public static String link( int num, String text ) {
        return link( "" + num, text);
    }


    public static String normalize( String ref ) {
        return ref.replace( '.', '-');
    }

    public static String externalLink(String ext, String text) {
        return "[" +  ext + " " + text + "]";
    }
}
