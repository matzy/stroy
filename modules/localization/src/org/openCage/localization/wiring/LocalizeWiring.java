package org.openCage.localization.wiring;

import org.openCage.localization.impl.LocalizeBuilderImpl;
import org.openCage.localization.impl.TheLocaleImpl;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;
import org.openCage.localization.protocol.TheLocale;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class LocalizeWiring implements Module {

	public void configure(Binder binder) {
		binder.bind( Localize.class ).
			annotatedWith( Names.named("basic")).toProvider( LocalizeBuilderImpl.class );
		binder.bind( LocalizeBuilder.class ).to( LocalizeBuilderImpl.class );
		binder.bind( TheLocale.class).to( TheLocaleImpl.class ).in( Singleton.class );
	}

}

