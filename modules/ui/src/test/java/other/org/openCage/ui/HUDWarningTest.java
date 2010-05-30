package other.org.openCage.ui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Ignore;
import org.junit.Test;
import org.openCage.ui.warning.HUDWarning;

import java.util.Locale;

@Ignore
public class HUDWarningTest {

    @Test
    public void testHudLayout() {
        Injector injector = Guice.createInjector( new TestWiring() );

        HUDWarning warning = injector.getInstance( HUDWarning.class );

        warning.show( "message");
    }

    @Test
    public void testHudLongMessage() {
        Injector injector = Guice.createInjector( new TestWiring() );

        HUDWarning warning = injector.getInstance( HUDWarning.class );

        warning.show( "<html>This is going to be a long message about<br>/Users/foo/this/is/a/deeply/nested/file.txt<br>nah?<html>");
    }

    @Test
    public void testHudLongMessageJP() {
        Injector injector = Guice.createInjector( new TestWiring() );

        HUDWarning warning = injector.getInstance( HUDWarning.class );

        warning.show( "<html>This is going to be a long message about<br>"+ Locale.JAPAN.getDisplayLanguage( Locale.JAPANESE )+"<br>nah?<html>");
    }
}
