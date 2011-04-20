package org.openCage.localization;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DictLocalize implements Provider<Localize> {

        private final LocalizeFactory factory;

        @Inject
        public DictLocalize( LocalizeFactory factory ) {
            this.factory = factory;
        }

        @Override
        public Localize get() {
            return factory.get( "org.openCage.localization.text" );
        }
}
