/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.ui.wriring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.muchsoft.util.Sys;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;
import org.openCage.ui.impl.FileChooserOSX;
import org.openCage.ui.impl.FileChooserWindows;
import org.openCage.ui.impl.XPlatformLocalizeProvider;
import org.openCage.ui.protocol.FileChooser;

/**
 *
 * @author stephan
 */
public class XPlatformWiring implements Module {

    public void configure(Binder binder) {

        binder.install(new LocalizeWiring());

        binder.bind(Localize.class).
                annotatedWith(Names.named("ui")).toProvider(XPlatformLocalizeProvider.class);

        if (Sys.isMacOSX()) {
            binder.bind(FileChooser.class).to(FileChooserOSX.class);
        } else if (Sys.isWindows()) {
            binder.bind(FileChooser.class).to(FileChooserWindows.class);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof XPlatformWiring;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
