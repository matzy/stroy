package org.openCage.util.lang;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;
import org.openCage.lang.protocol.FE1;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class Lazy1<S,T> {
    private S              obj;
    private FE1<S,T> func;

    public Lazy1( @NotNull FE1<S,T> func ) {
//        if ( func == null ) {
//            throw new NullPointerException( "Lazy1 needs a function" );
//        }
        this.func = func;
    }

    public S get( T t) {
        if ( func != null ) {
            try {
                obj = func.call(t);
            } catch (Exception ex) {
                // TODO
                Logger.getLogger(Lazy1.class.getName()).log(Level.SEVERE, null, ex);
            }
            func = null;
        }

        return obj;
    }
}
