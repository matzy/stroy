package org.openCage.stroy.tree;

import org.openCage.stroy.tree.filesystem.FsNoedGenSelector;

import java.util.List;
import java.util.ArrayList;

// use guice here ?
public class NoedGenSelectorCenter implements NoedGenSelector {

    private List<NoedGenSelector> selectors = new ArrayList<NoedGenSelector>();

    public NoedGenSelectorCenter() {
        selectors.add( new FsNoedGenSelector() );
    }

    public NoedGen find( String path, boolean single ) {

        for ( NoedGenSelector sel : selectors ) {
            NoedGen mb = sel.find( path, single );

            if ( mb != null ) {
                return mb;
            }
        }

        return null;
    }
}
