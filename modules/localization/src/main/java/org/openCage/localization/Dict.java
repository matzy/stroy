package org.openCage.localization;

import com.google.inject.Inject;

public class Dict {

    private final Localize loc;

    @Inject
    public Dict( Localize loc ) {
        this.loc = loc;
    };

    public String ok() {
        return loc.localize( "org.openCage.dict.ok" );
    }
}
