package org.openCage.stroy.tree.zip;

import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.Fiel;

import java.util.List;
import java.util.ArrayList;

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
        return children;
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
