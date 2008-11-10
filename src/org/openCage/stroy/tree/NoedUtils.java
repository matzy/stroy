package org.openCage.stroy.tree;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 09.11.2008
 * Time: 15:50:02
 * To change this template use File | Settings | File Templates.
 */
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

}
