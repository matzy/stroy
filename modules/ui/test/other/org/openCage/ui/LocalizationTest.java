package other.org.openCage.ui;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.junit.Test;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.Constants;
import org.openCage.ui.protocol.AboutSheet;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 4, 2010
 * Time: 7:08:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocalizationTest {

    public static class Loc {
        @Inject
        @Named( Constants.UI )
        public Localize loc;
    }

    @Test
    public void testAbout() {
        Injector injector = Guice.createInjector( new TestWiring() );

		Loc loc = injector.getInstance( Loc.class );

        assertEquals( "†ber foo", loc.loc.localize(Locale.GERMAN, "org.openCage.ui.about_prog", "foo"));

    }

    @Test
    public void testDisplay() {
        assertEquals( "German", Locale.GERMAN.getDisplayLanguage( Locale.US ) );
        assertEquals( "English", Locale.US.getDisplayLanguage( Locale.US ) );
        assertEquals( "English", Locale.UK.getDisplayLanguage( Locale.US ) );
        assertEquals( "English", Locale.ENGLISH.getDisplayLanguage( Locale.US ) );
        assertEquals( "Japanese", Locale.JAPAN.getDisplayLanguage( Locale.US ) );
    }

}
