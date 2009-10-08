package org.openCage.stroy.fuzzyHash;

import org.openCage.stroy.algo.fuzzyHash.HasDistance;

public interface FuzzyHashFactory {

    public HasDistance get( String type, Class clazz );
}
