package org.openCage.ruleofthree;


import java.util.regex.Pattern;

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

public class XMLUtils {

    /** Wandelt in XML-Fliesstext nicht erlaubte Zeichen in
     * XML-Entities um
     * @param body umzuwandelnder XML-String
     * @return umgewandelter XML-String
     */
    public static String escapeXmlEntities(String body) {
        if (body == null) body = "";

        StringBuffer buf = new StringBuffer(body);

        for (int i = 0; i < buf.length(); i++) {
            switch (buf.charAt(i)) {
                case '<':
                    buf.deleteCharAt(i);
                    buf.insert(i, "&lt;");
                    i += 3;

                    break;

                case '>':
                    buf.deleteCharAt(i);
                    buf.insert(i, "&gt;");
                    i += 3;

                    break;

                case '&':
                    buf.deleteCharAt(i);
                    buf.insert(i, "&amp;");
                    i += 4;

                    break;

                default:
                    break;
            }
        }

        return buf.toString();
    }


    //    private static Pattern xmlNamePattern = Pattern.compile( "^[:A-Z_a-z\\u00C0\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02ff\\u0370-\\u037d"
//            + "\\u037f-\\u1fff\\u200c\\u200d\\u2070-\\u218f\\u2c00-\\u2fef\\u3001-\\ud7ff"
//            + "\\uf900-\\ufdcf\\ufdf0-\\ufffd\\x10000-\\xEFFFF]"
//            + "[:A-Z_a-z\\u00C0\\u00D6\\u00D8-\\u00F6"
//            + "\\u00F8-\\u02ff\\u0370-\\u037d\\u037f-\\u1fff\\u200c\\u200d\\u2070-\\u218f"
//            + "\\u2c00-\\u2fef\\u3001-\\udfff\\uf900-\\ufdcf\\ufdf0-\\ufffd\\-\\.0-9"
//            + "\\u00b7\\u0300-\\u036f\\u203f-\\u2040]*\\Z");
    private static Pattern xmlNamePattern = Pattern.compile( "^[:A-Z_a-z\u00C0\u00D6\u00D8-\u00F6\u00F8-\u02ff\u0370-\u037d"
            + "\u037f-\u1fff\u200c\u200d\u2070-\u218f\u2c00-\u2fef\u3001-\ud7ff"
            + "\uf900-\ufdcf\ufdf0-\ufffd\\x10000-\\xEFFFF]"
            + "[:A-Z_a-z\u00C0\u00D6\u00D8-\u00F6"
            + "\u00F8-\u02ff\u0370-\u037d\u037f-\u1fff\u200c\u200d\u2070-\u218f"
            + "\u2c00-\u2fef\u3001-\udfff\uf900-\ufdcf\ufdf0-\ufffd\\-\\.0-9"
            + "\u00b7\u0300-\u036f\u203f-\u2040]*\\Z");

    private static Pattern xmlNameReserved = Pattern.compile( "(x|X)(m|M)(l|l).*" );



    //  Thanks, Does this means that rule number 3 is not right "3. Names cannot start with the letters xml (or XML, or Xml, etc)" – ekeren Mar 22 '11 at 22:23
//    The answer is yes and no. "Names beginning with the string "xml", or with any string which would match (('X'|'x') ('M'|'m') ('L'|'l')), are reserved for standardization in this or future versions of this specification." So it is a valid name, but it is reserved. – Mike Samuel Mar 22 '11 at 23:51

    //    Names can contain letters, numbers, and other characters
//    Names cannot start with a number or punctuation character
//    Names cannot start with the letters xml (or XML, or Xml, etc)
//    Names cannot contain spaces
    public static boolean isNameOk(String name ) {
        return !name.contains(" ");
        //return xmlNamePattern.matcher(name.get()).matches() && !xmlNamePattern.matcher(name.get()).matches();

    }
}
