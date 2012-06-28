package org.openCage.stroy.matching;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.matching.TreeTask;

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

public class TreeTaskImpl extends TaskNeutral<Noed> implements TreeTask {

    private final Noed leftRoot;
    private final Noed rightRoot;

    public TreeTaskImpl( Noed left, Noed right ) {
        leftRoot  = left;
        rightRoot = right;

        addLeftRecursive( left );
        addRightRecursive( right );

        match( left, right );
    }


    public void addLeftRecursive( Noed left ) {
        addLeft( left );

        for ( Noed child : left.getChildren() ) {
            addLeftRecursive( child );
        }
    }

    public Noed getLeftRoot() {
        return leftRoot;
    }

    public Noed getRightRoot() {
        return rightRoot;
    }

    public void addRightRecursive( Noed right ) {
        addRight( right );

        for ( Noed child : right.getChildren() ) {
            addRightRecursive( child );
        }
    }
}
