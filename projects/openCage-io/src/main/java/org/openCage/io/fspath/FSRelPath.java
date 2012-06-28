package org.openCage.io.fspath;

import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
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

public class FSRelPath {

    private final List<String> elems = new ArrayList<String>();

    public FSRelPath( String ... elems ) {
        // add all to keep the arraylist which allows adding
        // eve if it is only done to add to the newly created, see below
        this.elems.addAll(Arrays.asList(elems));
    }

    public FSRelPath( List<String> elems ) {
        this.elems.addAll( elems );
    }

    public FSRelPath add( String ... more ) {
        FSRelPath ret = new FSRelPath( elems );

        ret.elems.addAll( Arrays.asList(more));

        return ret;
    }

    @Override
    public String toString() {
        return Strings.join( elems ).separator( "/" ).toString();
    }

    public List<String> getAsList() {
        return elems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FSRelPath fsRelPath = (FSRelPath) o;

        if (elems != null ? !elems.equals(fsRelPath.elems) : fsRelPath.elems != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return elems != null ? elems.hashCode() : 0;
    }
}

