package org.openCage.stroy.filter;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.comphy.ListProperty;
import org.openCage.comphy.StringProperty;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.VF0;

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

public class IgnoreCentral5 implements Ignore, VF0 {

    private final ListProperty<StringProperty> patterns;
    private Pattern pattern;

    @Inject
    public IgnoreCentral5( @Named( value = "ignores" ) ListProperty<StringProperty> patterns) {
        this.patterns = patterns;
        patterns.getListenerControl().add( this );
        call();
    }

    public static ListProperty<StringProperty> getInitial() {

        ListProperty<StringProperty> pat = new ListProperty<StringProperty>();

        pat.add( StringProperty.valueOf(".*\\.svn" ));
        pat.add( StringProperty.valueOf(".*\\.DS_Store" ));
        pat.add( StringProperty.valueOf(".*\\.class" ));
        pat.add( StringProperty.valueOf(".*\\.o" ));
        pat.add( StringProperty.valueOf(".*\\.obj" ));

        pat.add( StringProperty.valueOf( ".*/\\.get_date\\.dat" ));
        pat.add( StringProperty.valueOf(".*/copyarea\\.db" ));
        pat.add( StringProperty.valueOf(".*/copyarea\\.dat" ));
        pat.add( StringProperty.valueOf(".*/vssver\\.scc" ));
        pat.add( StringProperty.valueOf(".*/vssver2\\.scc" ));
        pat.add( StringProperty.valueOf(".*/CVS" ));
        pat.add( StringProperty.valueOf(".*/SCCS" ));
        pat.add( StringProperty.valueOf(".*/RCS" ));
        pat.add( StringProperty.valueOf(".*/rcs" ));

        return pat;
    }

    public Ignore getIgnore() {
        return this;
    }

    public List<StringProperty> getPatterns() {
        return patterns;
    }

    public void addExtension(String extension) {
        patterns.add( StringProperty.valueOf(".*\\." + extension ));
    }

    public void addPattern(String name) {
        patterns.add( StringProperty.valueOf(name));
    }

    public void addPath(String path) {
        patterns.add( StringProperty.valueOf(path));
    }

    @Override
    public boolean match(String path) {

        return pattern.matcher( path ).matches();
    }

    @Override
    public void call() {
       pattern = Pattern.compile( Strings.join( patterns ).
                                       trans( new F1<String, StringProperty>() {
                                           @Override public String call(StringProperty stringProperty) {
                                               return "(" + stringProperty.get() + ")";
                                           }}).
                                       separator("|").toString());
    }
}
