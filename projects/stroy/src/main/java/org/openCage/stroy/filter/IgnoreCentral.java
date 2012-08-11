package org.openCage.stroy.filter;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.VF0;
import org.openCage.ruleofthree.property.ArrayListProperty;
import org.openCage.ruleofthree.property.ListProperty;

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

public class IgnoreCentral implements Ignore, VF0 {

    private final ListProperty<String> patterns;
    private Pattern pattern;

    @Inject
    public IgnoreCentral(@Named("ignores") ListProperty<String> patterns) {
        this.patterns = patterns;
        patterns.getListenerControl().add( this );
        call();
    }

    public static ArrayListProperty<String> getInitial() {

        ArrayListProperty<String> pat = new ArrayListProperty<String>();

        pat.add( String.valueOf(".*\\.svn" ));
        pat.add( String.valueOf(".*\\.DS_Store" ));
        pat.add( String.valueOf(".*\\.class" ));
        pat.add( String.valueOf(".*\\.o" ));
        pat.add( String.valueOf(".*\\.obj" ));
        pat.add( String.valueOf(".*\\.git" ));

        pat.add( String.valueOf(".*/__MACOSX" ));
        pat.add( String.valueOf( ".*/\\.get_date\\.dat" ));
        pat.add( String.valueOf(".*/copyarea\\.db" ));
        pat.add( String.valueOf(".*/copyarea\\.dat" ));
        pat.add( String.valueOf(".*/vssver\\.scc" ));
        pat.add( String.valueOf(".*/vssver2\\.scc" ));
        pat.add( String.valueOf(".*/CVS" ));
        pat.add( String.valueOf(".*/SCCS" ));
        pat.add( String.valueOf(".*/RCS" ));
        pat.add( String.valueOf(".*/rcs" ));

        return pat;
    }

    public Ignore getIgnore() {
        return this;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public void addExtension(String extension) {
        patterns.add( String.valueOf(".*\\." + extension ));
    }

    public void addPattern(String name) {
        patterns.add( String.valueOf(name));
    }

    public void addPath(String path) {
        patterns.add( String.valueOf(path));
    }

    @Override
    public boolean match(String path) {

        return pattern.matcher( path ).matches();
    }

    @Override
    public void call() {
       pattern = Pattern.compile( Strings.join( patterns ).
                                       trans( new F1<String, String>() {
                                           @Override public String call(String str) {
                                               return "(" + str + ")";
                                           }}).
                                       separator("|").toString());
    }
}
