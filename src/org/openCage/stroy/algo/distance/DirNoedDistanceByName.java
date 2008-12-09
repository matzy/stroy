package org.openCage.stroy.algo.distance;

import org.openCage.stroy.algo.tree.Noed;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 07.12.2008
 * Time: 15:53:21
 * To change this template use File | Settings | File Templates.
 */
public class DirNoedDistanceByName implements Distance<Noed> {
    public double distance( Noed a, Noed b ) {

        if ( a.isLeaf() || b.isLeaf() ) {
            throw new IllegalArgumentException( "not a dir" );
        }

        // child names -> array of string

        // arrray compare

        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
