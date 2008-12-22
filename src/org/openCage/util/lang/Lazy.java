package org.openCage.util.lang;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * lazy evaluate a function with no arguments
 * i.e. a memoization method
 * no protection against trows
 */
public class Lazy<T> {
    private T              obj;
    private boolean        evaluated = false;
    private final   F0<T>  func;

    public Lazy( F0<T> func ) {
        this.func = func;
    }

    public T get() {
        if ( !evaluated ) {
            obj       = func.call();
            evaluated = true;
        }

        return obj;
    }
}
