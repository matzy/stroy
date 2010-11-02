package org.openCage.babel;

import org.junit.Test;

import java.util.Arrays;
import java.util.Locale;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 2, 2010
 * Time: 4:46:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBableBundle {

    @Test
    public void testStd() {

        BabelBundle bb = new BabelBundle( "org.openCage.babel.testbundle", new LocalePreference());

        assertEquals( "eee", bb.get( "everywhere" ));
    }

    @Test
    public void testChange() {

        LocalePreference lp = new LocalePreference();
        BabelBundle bb = new BabelBundle( "org.openCage.babel.testbundle", lp);
        assertEquals( "eee", bb.get( "everywhere" ));

        lp.setLocales( Locale.GERMAN );
        assertEquals( "ddd", bb.get( "everywhere" ));
    }
}
