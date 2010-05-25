package org.openCage.other;

import org.junit.Test;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.impl.LocalizeImpl;

import static junit.framework.Assert.assertEquals;

public class LocalizationNoGuiceNoPropertyTest {

    @Test
    public void testAuthor() {
        Localize loc = new DictLocalize( null );

        assertEquals( "", loc.localize( "about" ));
    }
}
