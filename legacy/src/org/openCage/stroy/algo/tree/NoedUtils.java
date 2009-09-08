package org.openCage.stroy.algo.tree;

import org.openCage.stroy.algo.tree.Noed;


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

public class NoedUtils {
    public static Noed getNoed( Noed root, String ... path ) {

        for ( String name : path ) {

            if ( root.isLeaf() ) {
                throw new IllegalArgumentException( "not a dir" );
            }
            Noed dir = root;

            boolean found = false;
            for ( Noed child : dir.getChildren() ) {
                if ( child.getName().equals( name )) {
                    root = child;
                    found = true;
                    break;
                }
            }

            if ( !found ) {
                throw new IllegalArgumentException( "path not found: " + name );
            }
        }

        return root;
    }

    public static void print( Noed root ) {
        print( root, "" );
    }

    private static void print( Noed root, String prefix  ) {
        System.out.println( prefix + root );
        for ( Noed child : root.getChildren() ) {
            print( child, prefix + "  " );
        }
    }

}
