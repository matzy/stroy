package org.openCage.gpad;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.application.protocol.Application;
import org.openCage.application.wiring.ApplicationWiring;
import org.openCage.xplatform.wriring.XPlatformWiring;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Oct 27, 2009
 * Time: 4:25:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FausterizeWiring implements Module {
    public void configure(Binder binder) {
        binder.install( new ApplicationWiring());
        binder.install( new XPlatformWiring());

        binder.bind( Application.class ).toProvider( ApplicationProvider.class );
    }
}
