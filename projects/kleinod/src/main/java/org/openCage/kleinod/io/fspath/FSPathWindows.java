package org.openCage.kleinod.io.fspath;

import org.openCage.kleinod.Strings;
import org.openCage.kleinod.collection.Lists;
import org.openCage.kleinod.errors.Unchecked;
import org.openCage.kleinod.hash.StdHash;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
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
public class FSPathWindows extends FSPathBase {

    private final String drive;
    private final List<String> elements = new ArrayList<String>();
    private static final Pattern driveLetter = Pattern.compile( "[a-zA-Z]\\:" );

    public FSPathWindows( String absolutePath ) {
        if ( Strings.isEmpty(absolutePath)) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }


        if ( absolutePath.length() < 2 || !driveLetter.matcher( absolutePath.substring(0,2)).matches() ) { 
            throw new IllegalArgumentException("no drive letter: " + absolutePath);
        }

        drive = absolutePath.substring( 0, 1 ).toUpperCase();

        String[] els = absolutePath.substring(2).split("\\\\");
        ElementRules.check( els );

        for ( String element : els ) {
            if ( !element.trim().isEmpty() && !element.equals(".") ) {
                if ( element.equals("..")) {
                    if ( elements.size() == 0 ) {
                        throw new IllegalArgumentException("illegal .. (no parent)");
                    }
                    Lists.pop(elements);
                } else {
                    elements.add( element );
                }
            }
        }
    }

    private FSPathWindows() {
        drive = "Z";
        throw new IllegalStateException( "need a path" );
    }

    public String toString() {
        return drive + ":\\" + Strings.join( elements ).separator("\\").toString();
    }


    @Override
    public FSPath add( List<String> elements) {
        ElementRules.check( elements );        
        // clone
        FSPathWindows ret = new FSPathWindows( toString() );
        ret.elements.addAll( elements );
        return ret;
    }

    @Override
    public Iterator<FSPath> iterator() {
        List<FSPath> ret = new ArrayList<FSPath>();
        FSPath path = new FSPathWindows( drive + ":");
        ret.add( path );
        for ( String el : elements ) {
            path = path.add( el );
            ret.add( path );
        }
        return ret.iterator();
    }

    @Override
    public int size() {
        return elements.size() + 1;
    }

    @Override
    public URI toURI() {
        try {
            return new URI( "file:///" + toString().replace( '\\', '/') );
        } catch (URISyntaxException e) {
            throw Unchecked.wrap(e);
        }
    }

    @Override
    public FSPath parent(int i) {
        if ( i > elements.size() ) {
            throw new IllegalArgumentException( "to manny .." );
        }

        FSPathWindows ret = new FSPathWindows( drive + ":");
        ret.elements.addAll( elements.subList( 0, elements.size() - i ));

        return ret;
    }


    @Override
    public String getFileName() {
        return elements.get( elements.size() - 1 );
    }

    // TODO
    @Override
    public boolean isRoot() {
        return elements.size() == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FSPathWindows)) { return false; }

        FSPathWindows that = (FSPathWindows) o;

        if (drive != null ? !drive.equals(that.drive) : that.drive != null) { return false; }
        if (elements != null ? !elements.equals(that.elements) : that.elements != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return StdHash.hash( drive, elements );
    }

    
}
