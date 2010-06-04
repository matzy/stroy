package org.openCage.other;

import org.junit.Before;
import org.junit.Test;
import org.openCage.lang.Sisl;
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
        Sisl.bind( Localize.class, new F0<Localize>() {
            @Override
            public Localize call() {
                return new DictLocalize( Sisl.get(Property.class, LocaleProperty.THE_LOCALE ));
            }
        });

        Sisl.bindSingleton( Property.class, LocaleProperty.THE_LOCALE,
                new F0<Property>() {
                    @Override
                    public Property call() {
                        return new SimpleProp<Locale>( Locale.US );
                    }
                });

        Sisl.bind( DictLocalize.class, new F0<DictLocalize>() {
            @Override
            public DictLocalize call() {
                return new DictLocalize( Sisl.get(Property.class, "loca" ));
            }
        });

    }


    @Test
    public void testSimple() {
        Localize loc = Sisl.get( Localize.class);

        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));

    }

    @Test
    public void testSimple2() {
        Localize loc = Sisl.get( DictLocalize.class);

        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));

    }
}
