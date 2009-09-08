package org.openCage.stroy.fuzzyHash;

import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;

public interface FuzzyHashFactory {

    public FuzzyHash get( String type, Class clazz );
}
