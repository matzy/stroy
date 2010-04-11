package org.openCage.lang.clazz;

import org.openCage.lang.protocol.FE1;

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
public class Lazy1<T,A> {
    private T              obj;
    private boolean        evaluated = false;
    private final FE1<T,A> func;

    public Lazy1( FE1<T,A> func ) {
        this.func = func;
    }

    // TODO exceptions
    public T get( A a) {
        if ( !evaluated ) {
            try {
                obj = func.call( a );
            } catch (Exception ex) {
                Logger.getLogger(Lazy.class.getName()).log(Level.SEVERE, null, ex);
            }
            evaluated = true;
        }

        return obj;
    }
}
