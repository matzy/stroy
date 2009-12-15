package other.org.openCage.ui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;
import org.openCage.ui.protocol.AboutSheet;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 15, 2009
 * Time: 11:27:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuTest {

    @Test
    public void testMenu() {
        Injector injector = Guice.createInjector(new TestWiring());

        MiniApp miniApp = injector.getInstance( MiniApp.class );

        
//        AboutSheet about = injector.getInstance(AboutSheet.class);
//
//        about.setVisible(true);

        try {
            Thread.currentThread().sleep(50000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
