package org.openCage.application.impl.locale;

import java.util.Arrays;

import org.openCage.localization.protocol.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ApplicationLocalizeProvider implements Provider<Localize>{

	@Inject
	private LocalizeBuilder builder;
	
	public Localize get() {
		return builder.build( "org.openCage.application.impl.locale.abouttexts", Arrays.asList( builder.get()));
	}

}
