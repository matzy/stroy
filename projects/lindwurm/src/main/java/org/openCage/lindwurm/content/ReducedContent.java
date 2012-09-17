package org.openCage.lindwurm.content;

import org.openCage.kleinod.io.FileUtils;

import java.io.File;
import java.io.InputStream;

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


/**
 * The idea here is to replace a real file with ReducedContent, i.e. with just its fingerprint and fuzzyHash
 * Also good to build tests
 */
public class ReducedContent implements Content {
    private final String    name;
    private final String    typ;


    public ReducedContent( final String    name ) {
        this.name     = name;
        this.typ      = FileUtils.getExtension(name);
    }

    public ReducedContent( final String    name,
                           final String    typ ) {
        this.name     = name;
        this.typ       = typ;
    }

    public ReducedContent( final Content orig ) {
        this.name      = orig.getName();
        this.typ       = orig.getType();
    }


    public String getName() {
        return name;
    }


    public String getType() {
        return typ;
    }

    // lets refactor
    public String getLocation() {
        // TODO
        return "here";
    }

    @Override
    public InputStream getStream() {
        throw new Error("impl me");
    }

    public File getFile() {
        return null;
    }


    public String toString() {
        return "Reduced: " + name;
    }
}
