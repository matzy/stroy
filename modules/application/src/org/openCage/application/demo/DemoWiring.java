package org.openCage.application.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.application.protocol.Application;
import org.openCage.application.wiring.ApplicationWiring;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 1, 2009
 * Time: 6:10:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoWiring implements Module {
    public void configure(Binder binder) {
        binder.install( new ApplicationWiring());
        binder.bind( Application.class ).toProvider( ApplicationProvider.class );
    }
}
