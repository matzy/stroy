package org.openCage.stroy.matching;

import org.openCage.stroy.tree.Noed;

public class TreeTaskImpl extends TaskNeutral<Noed> implements TreeTask  {

    private final Noed leftRoot;
    private final Noed rightRoot;

    public TreeTaskImpl( Noed left, Noed right ) {
        leftRoot  = left;
        rightRoot = right;

        addLeftRec( left );
        addRightRec( right );
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
