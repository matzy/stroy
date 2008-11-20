package org.openCage.stroy.fuzzyHash;

import java.util.Set;

public class SetFuzzyHash implements FuzzyHash{

    private String type;
    private Set<Integer> lines;


    public double fuzzyEqual( FuzzyHash other ) {

        if ( other == null ) {
            return 0;
        }

        if ( !(other instanceof SetFuzzyHash )) {
            return 0;
        }

        SetFuzzyHash otherFuzzy = (SetFuzzyHash)other;

        if ( !type.equals(  otherFuzzy.type )) {
            return 0;
        }

        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
