package org.openCage.localization.wiring;

import org.openCage.lang.Tic;
import org.openCage.lang.functions.F0;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.impl.LocaleProperty;
import org.openCage.property.PersistentProp;
import org.openCage.property.PropStore;
import org.openCage.property.Property;

import java.util.Locale;

public class TicLocalize {

    public static void bind() {
        Tic.bind( Localize.class, new F0<Localize>() {
            @Override
            public Localize call() {
                return new DictLocalize( Tic.get(Property.class, LocaleProperty.THE_LOCALE ));
            }
        });

        Tic.bindSingleton( Property.class, LocaleProperty.THE_LOCALE,
                new F0<Property>() {
                    @Override
                    public Property<Locale> call() {
                        return PersistentProp.get( Tic.get(PropStore.class), // TODO tag ?? 
                                                   LocaleProperty.THE_LOCALE,
                                                   Locale.ENGLISH, "locale prop" );
                    }
                });

    }
}
