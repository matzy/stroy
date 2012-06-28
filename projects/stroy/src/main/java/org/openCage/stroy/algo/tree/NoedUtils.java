package org.openCage.stroy.algo.tree;

import org.openCage.stroy.algo.tree.Noed;


/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
