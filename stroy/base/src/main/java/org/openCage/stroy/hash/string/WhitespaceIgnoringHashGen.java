package org.openCage.stroy.hash.string;

import org.openCage.stroy.hash.HashGen;


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

/**
 * An whitespace independent hash function for strings
 * intended for programming languages
 * indentation/formating won't change the hash
 * it will create identical hashs for different java files if the concatination of
 * 2 strings is an other valid one
 * this is considered rare and because the hash is used for heuristics it is
 * acceptable. Note that there are not many spaces without extra special chars e.g.','
 * used for separating
 * also note that shifting space alla "String a =" to "Stri ga"
 */
public class WhitespaceIgnoringHashGen implements HashGen<String> {

    public int getHash(final String obj) {

        int hash = 0;

        for ( int i = 0; i < obj.length(); i++ ) {
            final char ch = obj.charAt( i );

            switch( ch ) {
                case ' '  :
                case '\t' :
                case '\r' :
                case '\n' :
                    break;
                default:
                    // default java hash calc see: http://mindprod.com/jgloss/hashcode.html
                    hash = hash * 31 + ch;
            }
        }

        return hash;
    }
}
