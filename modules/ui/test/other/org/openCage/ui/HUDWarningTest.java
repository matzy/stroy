package other.org.openCage.ui;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.junit.Test;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.Constants;
import org.openCage.ui.clazz.HUDWarning;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 19.02.2010
 * Time: 19:58:12
 * To change this template use File | Settings | File Templates.
 */
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
