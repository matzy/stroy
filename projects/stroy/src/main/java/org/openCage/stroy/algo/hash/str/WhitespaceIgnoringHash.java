package org.openCage.stroy.algo.hash.str;

import org.openCage.stroy.algo.hash.Hash;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
public class WhitespaceIgnoringHash implements Hash<String> {
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
