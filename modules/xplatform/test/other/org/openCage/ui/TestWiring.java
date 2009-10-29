package other.org.openCage.ui;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.application.protocol.Application;
import org.openCage.ui.wiring.UIWiring;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 29, 2009
 * Time: 8:46:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestWiring implements Module {
    @Override
    public void configure(Binder binder) {
        binder.install( new UIWiring());

        binder.bind( Application.class ).toProvider( ApplicationProvider.class );        
    }
}
