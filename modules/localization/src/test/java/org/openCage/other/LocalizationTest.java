package org.openCage.other;



import static org.junit.Assert.assertEquals;

import java.util.Locale;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openCage.localization.protocol.Localize;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class LocalizationTest {

    public static class Tmp {

        public @Inject @Named( "testing" ) Localize locl;
    }

    private Localize loc;

    @Before
    public void before() {
        Injector injector = Guice.createInjector( new TestWiring() );
        loc = injector.getInstance(Tmp.class).locl;
    }



	@Test
	public void testStd() {

        assertEquals( "Author", loc.localize("org.openCage.localization.dict.author"));
        assertEquals( "Autor", loc.localize( new Locale( "de", "DE" ), "org.openCage.localization.dict.author"));
	}

}
