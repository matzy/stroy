package org.openCage.gpad;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.application.protocol.Application;
import org.openCage.ui.wiring.UIWiring;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 1, 2009
 * Time: 7:35:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class FaustWiring implements Module {
    public void configure(Binder binder) {
        binder.install( new UIWiring() );

        binder.bind( Application.class ).toProvider( ApplicationProvider.class );
    }
}
