package org.openCage.lang.functions;

import java.util.logging.Level;
import java.util.logging.Logger;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public final class CatchAll {
    private static final Logger LOG = Logger.getLogger( CatchAll.class.getName() );

    private CatchAll() {};

    public static void call( FV f ) {
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
