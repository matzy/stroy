package org.openCage.stroy.fuzzyHash;

import org.openCage.lang.protocol.HasDistance;

public interface FuzzyHashFactory {

    public HasDistance get( String type, Class clazz );
}
