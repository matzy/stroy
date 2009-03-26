package org.openCage.lang;

import org.jetbrains.annotations.NotNull;

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

public class L1<R, A> implements E1<R,E0<A>>{

    private R              obj;
    private E1<R,A>        func;

    public L1( @NotNull E1<R,A> func ) {
        this.func = func;
    }

    public R c( E0<A> f) {
        if ( func != null ) {
            try {
                obj = func.c( f.c() );
            } catch (Exception e) {
                // TODO
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            func = null;
        }

        return obj;
    }

}
