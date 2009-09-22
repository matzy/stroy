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

	@Inject private BundleCheck check;
	@Inject private TheLocale   theLocale;
	
	public Localize build(String fullyqualifiedName, List<Localize> parents) {
		check.checkBundle( fullyqualifiedName );
		return new LocalizeImpl( fullyqualifiedName, parents, theLocale ); 
	}

	public Localize get() {
		return new LocalizeImpl( "org.openCage.localization.impl.text", Collections.EMPTY_LIST, theLocale );
	}

}
