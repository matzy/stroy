package org.openCage.localization.impl;

import java.util.Collections;
import java.util.List;

import org.openCage.localization.protocol.BundleCheck;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;
import org.openCage.localization.protocol.TheLocale;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class LocalizeBuilderImpl implements LocalizeBuilder, Provider<Localize> {

	private BundleCheck check;
	private TheLocale   theLocale;

    @Inject
    public LocalizeBuilderImpl( BundleCheck check, TheLocale theLocale ) {
        this.check = check;
        this.theLocale = theLocale;

    }
	
	public Localize build(String fullyqualifiedName, List<Localize> parents) {
		check.checkBundle( fullyqualifiedName );
		return new LocalizeImpl( fullyqualifiedName, parents, theLocale ); 
	}

	public Localize get() {
		return new LocalizeImpl( "org.openCage.localization.impl.text", Collections.<Localize>emptyList(), theLocale );
	}

}
