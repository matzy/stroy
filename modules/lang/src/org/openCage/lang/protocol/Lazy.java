package org.openCage.lang.protocol;

import org.openCage.lang.errors.Unchecked;


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

/**
 * lazy evaluate a function with no arguments
 * i.e. a memoization method
 * no protection against trows
 * this is similar to FutureTask
 */
public class Lazy<T> {
    private T              obj;
    private Boolean        evaluated = false;
    private Error          exp       = null;
    private final FE0<T>   func;

    public Lazy( FE0<T> func ) {
        this.func = func;
    }

    public T get() {

        eval();

        if ( exp != null ) {
            throw exp;            
        }
        
        return obj;
    }

    private synchronized void eval() {
        if ( !evaluated ) {
            try {
                obj   = func.call();
            } catch (Exception ex) {
                exp = new Unchecked(ex);
            } catch ( Error ex ) {
                exp = ex;
            }
            evaluated = true;
        }
    }
}
