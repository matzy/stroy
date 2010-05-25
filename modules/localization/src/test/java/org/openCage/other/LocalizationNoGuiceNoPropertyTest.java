package org.openCage.other;

import org.junit.Test;
import org.openCage.localization.CombinedLocalize;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;

public class LocalizationNoGuiceNoPropertyTest {

    @Test
    public void testAuthor() {
        Localize loc = new DictLocalize( null );
        loc.setLocale( Locale.ENGLISH );

        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));
    }


    @Test
    public void testCombine() {
        Localize loc = new CombinedLocalize( "org.openCage.localization.combine", null, new DictLocalize( null ) );
        loc.setLocale( Locale.ENGLISH );

        assertEquals( "Author", loc.localize( "org.openCage.localization.dict.author" ));
        assertEquals( "Tadaa", loc.localize( "combine" ));
    }

    @Test
    public void testAddLanguage() {
        Localize loc = new CombinedLocalize( "org.openCage.localization.text", null, new DictLocalize( null ) );
        loc.setLocale( new Locale( "xy") );

        assertEquals( "xyxy-author", loc.localize( "org.openCage.localization.dict.author" ));
    }

    @Test
    public void testOverride() {
        Localize loc = new CombinedLocalize( "org.openCage.localization.override", null, new DictLocalize( null ) );
        loc.setLocale( new Locale( "en") );

        assertEquals( "no-author", loc.localize( "org.openCage.localization.dict.author" ));
    }


}
