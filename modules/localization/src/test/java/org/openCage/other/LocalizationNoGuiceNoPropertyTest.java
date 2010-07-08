//package org.openCage.other;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openCage.io.fspath.FSPath;
//import org.openCage.io.fspath.FSPathBuilder;
//import org.openCage.lang.BackgroundExecutorImpl;
//import org.openCage.localization.CombinedLocalize;
//import org.openCage.localization.DictLocalize;
//import org.openCage.localization.Localize;
//import org.openCage.localization.impl.LocalePropertyProvider;
//import org.openCage.property.PersistingPropStore;
//import org.openCage.property.PropStore;
//import org.openCage.property.Property;
//
//import java.util.Locale;
//
//import static junit.framework.Assert.assertEquals;
//
//public class LocalizationNoGuiceNoPropertyTest {
//
//    @Test
//    public void testAuthor() {
//        Localize loc = new DictLocalize( null );
//        loc.setLocale( Locale.ENGLISH );
//
//        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));
//    }
//
//
//    @Test
//    public void testCombine() {
//        Localize loc = new CombinedLocalize( "org.openCage.localization.combine", null, new DictLocalize( null ) );
//        loc.setLocale( Locale.ENGLISH );
//
//        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));
//        assertEquals( "Tadaa", loc.localize( "combine" ));
//    }
//
//    @Test
//    public void testAddLanguage() {
//        Localize loc = new CombinedLocalize( "org.openCage.localization.text", null, new DictLocalize( null ) );
//        loc.setLocale( new Locale( "xy") );
//
//        assertEquals( "xyxy-author", loc.localize( "org.openCage.localization.dict.author" ));
//    }
//
//    @Test
//    public void testOverride() {
//        Localize loc = new CombinedLocalize( "org.openCage.localization.override", null, new DictLocalize( null ) );
//        loc.setLocale( new Locale( "en") );
//
//        assertEquals( "no-author", loc.localize( "org.openCage.localization.dict.author" ));
//    }
//
//    private FSPath backing = FSPathBuilder.getTmpFile( "foo");
//
//    @Before @After
//    public void clean() {
//        if ( backing.toFile().exists() ) {
//            backing.toFile().delete();
//        }
//    }
//
//    @Test
//    public void testLocale() {
//
//
//        PropStore store = new PersistingPropStore( new BackgroundExecutorImpl(), backing.toFile() );
//        Property<Locale> theLocale = new LocalePropertyProvider( store ).get();
//
//        Localize loc = new DictLocalize( theLocale );
//        loc.setLocale( Locale.ENGLISH );
//        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));
//
//        loc.setLocale( Locale.GERMAN );
//        assertEquals( "Autor", loc.localize( "org.openCage.localization.dict.author" ));
//    }
//
//}
