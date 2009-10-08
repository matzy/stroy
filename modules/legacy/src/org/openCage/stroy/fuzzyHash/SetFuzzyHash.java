package org.openCage.stroy.fuzzyHash;

import org.openCage.stroy.algo.fuzzyHash.HasDistance;

import java.util.Set;

public class SetFuzzyHash implements HasDistance {

    private String type;
    private Set<Integer> lines;


    public double distance( HasDistance other ) {

        if ( other == null ) {
            return 1;
        }

        if ( !(other instanceof SetFuzzyHash )) {
            return 1;
        }

        SetFuzzyHash otherFuzzy = (SetFuzzyHash)other;

        if ( !type.equals(  otherFuzzy.type )) {
            return 1;
        }

        throw new Error( "not impl" );

        //return 1;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
