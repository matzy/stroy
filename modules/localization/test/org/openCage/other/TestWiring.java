package org.openCage.other;

import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.property.protocol.PropStore;

public class TestWiring implements Module {

	public void configure(Binder binder) {
        binder.install( new LocalizeWiring());

        binder.bind( Localize.class ).toProvider( LocalizeProvider.class );
        binder.bind( PropStore.class ).toProvider( PropStoreProvider.class );

	}

}
