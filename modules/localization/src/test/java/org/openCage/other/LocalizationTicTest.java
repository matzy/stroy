package org.openCage.other;

import org.junit.Before;
import org.junit.Test;
import org.openCage.lang.Tic;
import org.openCage.lang.functions.F0;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.impl.LocaleProperty;
import org.openCage.property.Property;
import org.openCage.property.SimpleProp;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;

public class LocalizationTicTest {

    @Before
    public void before() {
        Tic.bind( Localize.class, new F0<Localize>() {
            @Override
            public Localize call() {
                return new DictLocalize( Tic.get(Property.class, LocaleProperty.THE_LOCALE ));
            }
        });

        Tic.bindSingleton( Property.class, LocaleProperty.THE_LOCALE,
                new F0<Property>() {
                    @Override
                    public Property call() {
                        return new SimpleProp<Locale>( Locale.US );
                    }
                });
    }


    @Test
    public void testSimple() {
        Localize loc = Tic.get( Localize.class);

        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));

    }
}
