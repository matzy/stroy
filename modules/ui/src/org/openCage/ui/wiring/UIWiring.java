/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.ui.wiring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.muchsoft.util.Sys;
import org.openCage.application.wiring.ApplicationWiring;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;
import org.openCage.ui.impl.*;
import org.openCage.ui.impl.about.AboutSheetFromApplication;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.FileChooser;
import org.openCage.ui.protocol.MenuBuilder;
import org.openCage.ui.protocol.OSXStandardEventHandler;

/**
 *
 * @author stephan
 */
public class UIWiring implements Module {

    public void configure(Binder binder) {

        binder.install(new LocalizeWiring());
        binder.install(new ApplicationWiring());

        binder.bind(Localize.class).
                annotatedWith(Names.named("ui")).toProvider(UILocalizeProvider.class);

        if (Sys.isMacOSX()) {
            binder.bind(FileChooser.class).to(FileChooserOSX.class);
        } else if (Sys.isWindows()) {
            binder.bind(FileChooser.class).to(FileChooserWindows.class);
        }

        binder.bind( AboutSheet.class ).
            to( AboutSheetFromApplication.class );

        binder.bind(OSXStandardEventHandler.class ).to( OSXStandardEventHandlerImpl.class );

        binder.bind(MenuBuilder.class).to( MenuBuilderImpl.class );

    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof UIWiring;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
