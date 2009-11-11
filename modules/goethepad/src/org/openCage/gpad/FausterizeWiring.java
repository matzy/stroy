package org.openCage.gpad;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.openCage.application.protocol.Application;
import org.openCage.application.wiring.ApplicationWiring;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.impl.UILocalizeProvider;
import org.openCage.ui.wiring.UIWiring;

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
        binder.install( new UIWiring());
        binder.bind( Application.class ).toProvider( ApplicationProvider.class );
        binder.bind(Localize.class).
                annotatedWith(Names.named("fausterize")).toProvider(LocalizeProvider.class);

    }
}
