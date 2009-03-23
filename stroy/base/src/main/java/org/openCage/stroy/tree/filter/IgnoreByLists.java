package org.openCage.stroy.tree.filter;

import org.openCage.stroy.tree.filter.Ignore;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class IgnoreByLists implements Ignore {

    private List<String> extensions;
    private List<String> patternStrings;
    private List<String> files;
    private Pattern pattern;


    public boolean match(String path) {
        if ( pattern == null ) {
            return false;
        }

        return pattern.matcher( path ).matches();
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
        calculate();
    }

    public void setPatterns(List<String> patterns) {
        this.patternStrings = patterns;
        calculate();
    }

    public void setPaths(List<String> paths) {
        this.files = paths;
        calculate();
    }


    public void calculate() {
        String str = "";

        if ( extensions != null ) {
            for ( String ext : extensions ) {
                if ( str.length() > 0 ) {
                    str += "|";
                }

                str += "(.*\\." + ext + ")";
            }
        }

        if ( files != null ) {
            for ( String path : files ) {
                if ( str.length() > 0 ) {
                    str += "|";
                }

                str += "(" + path + ")";
            }
        }

        if ( patternStrings != null ) {
            for ( String pat : patternStrings) {
                if ( str.length() > 0 ) {
                    str += "|";
                }

                str += "(" + pat + ")";
            }
        }

        pattern = Pattern.compile( str );

    }
}
