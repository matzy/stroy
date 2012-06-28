package org.openCage.stroy.filter;

import java.util.List;
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

//        pattern = Pattern.compile( str );

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
