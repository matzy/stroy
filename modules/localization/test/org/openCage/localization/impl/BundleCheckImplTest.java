package org.openCage.localization.impl;

import java.util.Locale;
import java.util.MissingResourceException;

import org.junit.Test;

public class BundleCheckImplTest {

	@Test(expected=MissingResourceException.class) 
	public void testNoFallback() {
		BundleCheckImpl check = new BundleCheckImpl();
		
		check.checkBundle( "org.openCage.localization.impl.nofallback"  );
	}
	
	@Test 
	public void testFallbackMisses_hasFallback() {
		BundleCheckImpl check = new BundleCheckImpl();		
		check.checkBundle( "org.openCage.localization.impl.missingKeys"  );
	}

	@Test(expected=MissingResourceException.class) 
	public void testFallbackMissesKeys() {
		BundleCheckImpl check = new BundleCheckImpl();		
		check.checkBundle( "org.openCage.localization.impl.missingKeys", Locale.GERMAN  );
	}
	
}
