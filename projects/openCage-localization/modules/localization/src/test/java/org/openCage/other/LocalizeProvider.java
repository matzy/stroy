package org.openCage.other;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.LocalizeFactory;

public class LocalizeProvider implements Provider<Localize> {

    private final LocalizeFactory factory;
    private final DictLocalize dict;

    @Inject
    public LocalizeProvider( LocalizeFactory factory, DictLocalize dict ) {
        this.factory = factory;
        this.dict = dict;
    }

    @Override public Localize get() {
        return dict.get();
    }
}
