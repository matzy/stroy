package org.openCage.other;



import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.openCage.localization.protocol.Localize;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class LocalizationTest {
	
	@Test
	public void testStd() {
        Injector injector = Guice.createInjector( new TestWiring() );

        Localize loc = injector.getInstance(Localize.class);
        
        assertEquals( "Author", loc.localize("org.openCage.localization.dict.author"));
	}

	@Test
	public void testOtherLocale() {
        Injector injector = Guice.createInjector( new TestWiring() );

        Localize loc = injector.getInstance(Localize.class);
        
        assertEquals( "Autor", loc.localize( new Locale( "de", "DE" ), "org.openCage.localization.dict.author"));
	}
}
