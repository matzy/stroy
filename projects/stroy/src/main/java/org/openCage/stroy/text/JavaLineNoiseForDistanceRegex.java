package org.openCage.stroy.text;

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

public class JavaLineNoiseForDistanceRegex implements LineNoise{

    private final Pattern pattern;

    /**
      * A linenoise implementation declaring all lines as noise that
      * do not carry diff relevant information
      * 'package' is changed by moves not by the content
      * 'import' is style dependend
      * 'bracket' hold littel content
      * anything that looks like a comment
      * This is not for diff but for distance because it filters content,
      * just not terrible important content.
      */
    public JavaLineNoiseForDistanceRegex() {

        StringBuffer patternString = new StringBuffer( "( *\t*//.*)" );
        patternString.append( "|( *\t*/\\*.*)");
        patternString.append( "|( *\t*\\{ *\t*)");
        patternString.append( "|( *\t*\\} *\t*)");
        patternString.append( "|( *\t*\\*.*)");
        patternString.append( "|( *\t*\\@.*)");
        patternString.append( "|( *\t*)");
        patternString.append( "|( *\t*package.*)");
        patternString.append( "|( *\t*public class.*)");
        patternString.append( "|( *\t*public interface.*)");
        patternString.append( "|( *\t*import.*)");

        pattern = Pattern.compile( patternString.toString() );
    }

    public boolean isGrayNoise(String text) {
        return pattern.matcher( text ).matches();
    }
}
