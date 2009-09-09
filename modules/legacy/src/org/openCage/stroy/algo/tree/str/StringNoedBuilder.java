package org.openCage.stroy.algo.tree.str;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedImpl;

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

public class StringNoedBuilder {


    public Noed d( String name, Noed ... childs ) {
        Noed ret = NoedImpl.makeDirNoed( name );
        for ( Noed child : childs ) {
            ret.addChild( child );
        }

        return ret;
    }

    public Noed l( String name, String typ, String content) {
        return NoedImpl.makeLeafNoed( name, new StringFiel( typ, content ));
    }

}
