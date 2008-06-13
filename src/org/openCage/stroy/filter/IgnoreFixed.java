package org.openCage.stroy.filter;

import java.util.List;
import java.util.regex.Pattern;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class IgnoreFixed implements Ignore {

    private final Pattern pattern;

    public IgnoreFixed( IgnoreStore store ) {
        String str = "";

         if ( store.extensions != null ) {
             for ( String ext : store.extensions ) {
                 if ( str.length() > 0 ) {
                     str += "|";
                 }

                 str += "(.*\\." + ext + ")";
             }
         }

//        pattern = Pattern.compile( str );

         if ( store.paths != null ) {
             for ( String path : store.paths ) {
                 if ( str.length() > 0 ) {
                     str += "|";
                 }

                 str += "(" + path + ")";
             }
         }

         if ( store.patterns != null ) {
             for ( String pat : store.patterns) {
                 if ( str.length() > 0 ) {
                     str += "|";
                 }

                 str += "(" + pat + ")";
             }
         }

          pattern = Pattern.compile( str );
    }

    public boolean match(String path) {

        return pattern.matcher( path ).matches();
    }

    public void setExtensions(List<String> extensions) {}

    public void setPatterns(List<String> patterns) {}

    public void setPaths(List<String> paths) {}
}
