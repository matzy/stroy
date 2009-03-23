package org.openCage.stroy.tree;

import org.openCage.lang.Maybe;

public interface NoedGenSelector {

    public Maybe<? extends NoedGen> get( String path, boolean single );
}
