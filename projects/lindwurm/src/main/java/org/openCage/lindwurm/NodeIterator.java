package org.openCage.lindwurm;

import org.openCage.kleinod.type.Null;

import java.util.Iterator;
import java.util.Stack;

public class NodeIterator implements Iterator<LindenNode> {

    private Stack<Iterator<LindenNode>> stack = new Stack<Iterator<LindenNode>>();
    private LindenNode ptr;

    public NodeIterator(LindenNode root) {
        ptr = root;
    }

    public boolean hasNext() {
        return Null.isNot(ptr);
    }

    public LindenNode next() {
        LindenNode oldNode = ptr;

        if ( !ptr.isLeaf() ) {
            stack.push( ptr.dir().getChildren().iterator());
        }

        while ( !stack.empty() ) {
            if ( stack.peek().hasNext() ) {
                ptr = stack.peek().next();
                return oldNode;
            } else {
                stack.pop();
            }
        }

        ptr = Null.of( LindenNode.class );
        return oldNode;
    }

    public void remove() {
        throw new Error( "not impl" );
    }
}
