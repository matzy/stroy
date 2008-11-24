package org.openCage.stroy.algo.tree;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.Fiel;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
