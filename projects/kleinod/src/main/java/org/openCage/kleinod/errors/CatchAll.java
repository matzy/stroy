package org.openCage.kleinod.errors;

import org.openCage.kleinod.lambda.F0;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.F2;
import org.openCage.kleinod.lambda.VF0;

import java.util.logging.Level;
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



public final class CatchAll {
    private static final Logger LOG = Logger.getLogger( CatchAll.class.getName() );

    private CatchAll() {};

    public static void call( VF0 f ) {
        try {
            f.call();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }
    }

    public static <R> R call( F0<R> f ) {
        try {
            return f.call();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }

        return null;
    }

    public static <R,A> R call( F1<R,A> f, A a ) {
        try {
            return f.call(a);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }

        return null;
    }

    public static <R,A,B> R call( F2<R,A,B> f, A a, B b ) {
        try {
            return f.call( a, b);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch ( Error err ) {
            LOG.log(Level.SEVERE, null, err);
        }

        return null;
    }
}
