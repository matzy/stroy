package org.openCage.stroy.algo.tree;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.Fiel;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
public class NoedImpl implements Noed{

    private String     name;
    private boolean    isLeaf;
    private Noed       parent;
    private List<Noed> children = new ArrayList<Noed>();
    private Fiel       fiel;


    private NoedImpl( String name, boolean isLeaf, Fiel fiel ) {
        this.name = name;
        this.isLeaf = isLeaf;
        this.fiel = fiel;

    }

    public static Noed makeDirNoed( String name ) {
        return new NoedImpl( name, false, null );
    }

    public static Noed makeLeafNoed( String name, Fiel fiel ) {
        return new NoedImpl( name, true, fiel );
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public boolean isReadOnly() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setParent( Noed parent ) {
        if ( parent.isLeaf() ) {
            throw new IllegalArgumentException( "parent arg not a dir " + parent);
        }

        this.parent = parent;
    }

    public Noed getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public List<Noed> getChildren() {
        return Collections.unmodifiableList( children );
    }

    public void addChild( Noed noed ) {
        children.add( noed );
        noed.setParent( this );
    }

    public Fiel getFiel() {
        return fiel;
    }


    public String toString() {
        return "Noed " + name + (isLeaf ? "" : "/");
    }
}
