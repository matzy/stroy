package org.openCage.io.fspath;

import org.openCage.lang.Lists;
import org.openCage.lang.Strings;
import org.openCage.lang.errors.Unchecked;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
public class FSPathUNC extends FSPathBase {

    public static final String UNC_PREFIX = "\\\\";

    private final List<String> elements = new ArrayList<String>();

    private FSPathUNC() {}

    public FSPathUNC(String absolutePath ) {
        if ( Strings.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }

        if ( !absolutePath.startsWith(UNC_PREFIX)) {
            throw new IllegalArgumentException("no UNC path");
        }

        String[] els = absolutePath.substring(2).split(UNC_PREFIX);
        ElementRules.check( els );

        for ( String element : els ) {
            if ( !element.trim().isEmpty() && !element.equals(".")) {
                if ( element.equals("..")) {
                    if ( elements.size() < 2 ) {
                        throw new IllegalArgumentException("illegal .. (no parent)");
                    }
                    Lists.pop(elements);
                } else {
                    elements.add( element );
                }
            }
        }

        if ( elements.size() == 0 ) {
            throw new IllegalArgumentException( "UNC paths have no root" );
        }
    }

    public String toString() {
        return UNC_PREFIX + Strings.join( elements ).separator("\\").toString();
    }


    @Override
    public FSPath add(List<String> elements) {
        ElementRules.check( elements );

        FSPathUNC ret = new FSPathUNC( toString() );
        ret.elements.addAll( elements );
        return ret;
    }

    @Override
    public Iterator<FSPath> iterator() {
        List<FSPath> ret = new ArrayList<FSPath>();
        FSPath path =  null;
        for ( String el : elements ) {
            if ( path == null ) {
                path = new FSPathUNC( "\\\\" + el );
            } else {
                path = path.add( el );
            }
            ret.add( path );
        }
        return ret.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public URI toURI() {
        try {
            return new URI( "file://" + toString().replace( '\\', '/') );
        } catch (URISyntaxException e) {
            throw Unchecked.wrap(e);
        }
    }

    @Override
    public FSPath parent(int i) {

        if ( i > elements.size() ) {
            throw new IllegalArgumentException( "to manny .." );
        }

        FSPathUNC ret = new FSPathUNC();
        ret.elements.addAll( elements.subList( 0, elements.size() - i ));

        return ret;
    }

    @Override
    public String getFileName() {
        return elements.get( elements.size() - 1 );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FSPathUNC)) { return false; }

        FSPathUNC fsPathUNC = (FSPathUNC) o;

        if (elements != null ? !elements.equals(fsPathUNC.elements) : fsPathUNC.elements != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return elements != null ? elements.hashCode() : 0;
    }
}
