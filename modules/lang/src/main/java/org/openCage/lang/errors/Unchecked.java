package org.openCage.lang.errors;

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

    public String toString() {
        return "Unchecked Exception, Caused by: " + source;
    }

    public Exception getSource() {
        return source;
    }

    public static Unchecked wrap( Exception cause ) {
        return new Unchecked(cause);
    }
}
