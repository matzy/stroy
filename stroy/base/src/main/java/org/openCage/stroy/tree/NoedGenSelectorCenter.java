package org.openCage.stroy.tree;

import org.openCage.lang.Maybe;
import org.openCage.stroy.tree.filesystem.FSNoedGen;
import org.openCage.stroy.tree.filesystem.FsNoedGenSelector;

import java.util.List;
import java.util.ArrayList;

// use guice here ?
public class NoedGenSelectorCenter implements NoedGenSelector {

    private List<NoedGenSelector> selectors = new ArrayList<NoedGenSelector>();

    public NoedGenSelectorCenter() {
        selectors.add( new FsNoedGenSelector() );
    }

    public Maybe<? extends NoedGen> get( String path, boolean single ) {

        for ( NoedGenSelector sel : selectors ) {
            Maybe< ? extends  NoedGen> mb = sel.get( path, single );

            if ( mb.is ) {
                return mb;
            }
        }

        return Maybe.no();
    }
}
