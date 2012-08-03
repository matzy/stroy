package org.openCage.lang.errors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

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
 * Wrapper for checked exceptions to be used in methods without throws
 * good for closures and slim code
 */
public class Unchecked extends Error {

    private static final long serialVersionUID = 1310525450890886497L;

    private final Exception source;
    private static final Logger LOG = Logger.getLogger( Unchecked.class.getName() );

    public Unchecked( Exception source) {
        LOG.info( "Unchecked: " + source );
        this.source = source;
    }

    @Override public String toString() {
        return "Unchecked Exception, Caused by: " + source;
    }

    public Exception getSource() {
        return source;
    }

    public static Unchecked wrap( Exception cause ) {
        return new Unchecked(cause);
    }

    public static Unchecked wrap( IOException cause ) {
        return new IOUnchecked(cause);
    }

    public static Unchecked wrap( FileNotFoundException cause ) {
        return new FileNotFoundUnchecked(cause);
    }

}
