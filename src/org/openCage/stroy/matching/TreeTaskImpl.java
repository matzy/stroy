package org.openCage.stroy.matching;

import org.openCage.stroy.tree.Noed;

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

public class TreeTaskImpl extends TaskNeutral<Noed> implements TreeTask  {

    private final Noed leftRoot;
    private final Noed rightRoot;

    public TreeTaskImpl( Noed left, Noed right ) {
        leftRoot  = left;
        rightRoot = right;

        addLeftRec( left );
        addRightRec( right );

        match( left, right );
    }

    private void addRightRec( Noed right ) {
        addRight( right );

        for ( Noed child : right.getChildren() ) {
            addRightRec( child );
        }
    }

    private void addLeftRec( Noed left ) {
        addLeft( left );

        for ( Noed child : left.getChildren() ) {
            addLeftRec( child );
        }
    }

    public Noed getLeftRoot() {
        return leftRoot;
    }

    public Noed getRightRoot() {
        return rightRoot;
    }
}
