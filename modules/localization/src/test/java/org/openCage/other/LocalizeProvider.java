package org.openCage.other;

import org.openCage.localization.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class LocalizeProvider implements Provider<Localize>{

	@Inject
	private LocalizeBuilder builder;
	
	public Localize get() {
		return builder.get();
	}

}
