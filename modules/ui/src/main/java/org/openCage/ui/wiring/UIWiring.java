package org.openCage.ui.wiring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.muchsoft.util.Sys;
import org.openCage.localization.Localize;
import org.openCage.localization.wiring.LocalizeWiring;
import org.openCage.property.Property;
import org.openCage.ui.impl.FileChooserGeneral;
import org.openCage.ui.impl.FileChooserOSX;
import org.openCage.ui.impl.FileChooserWindows;
import org.openCage.ui.impl.GlobalKeyEventHandlerImpl;
import org.openCage.ui.impl.OSXStandardEventHandlerImpl;
import org.openCage.ui.impl.UILocalizeProvider;
import org.openCage.ui.impl.about.AboutSheetFromApplication;
import org.openCage.ui.impl.help.HelpViewerOSX;
import org.openCage.ui.pref.CaretStyleProperty;
import org.openCage.ui.pref.LocalePrefBuilderImpl;
import org.openCage.ui.pref.TextEditorPref;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.FileChooser;
import org.openCage.ui.protocol.GlobalKeyEventHandler;
import org.openCage.ui.protocol.HelpViewer;
import org.openCage.ui.protocol.PrefBuilder;
import org.openCage.ui.protocol.OSXStandardEventHandler;

import static org.openCage.ui.Constants.*;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code.
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
 */

@SuppressWarnings({"OverlyCoupledClass"})
public class UIWiring implements Module {

    @Override
    public void configure(Binder binder) {

        binder.install( new LocalizeWiring());

        binder.bind(Localize.class).
                annotatedWith(Names.named(UI)).toProvider(UILocalizeProvider.class);

        if (Sys.isMacOSX()) {
            binder.bind(FileChooser.class).to(FileChooserOSX.class);
        } else if (Sys.isWindows()) {
            binder.bind(FileChooser.class).to(FileChooserWindows.class);
        } else {
            binder.bind(FileChooser.class).to(FileChooserGeneral.class);
        }

        binder.bind(AboutSheet.class).
                to(AboutSheetFromApplication.class);

        binder.bind(OSXStandardEventHandler.class).to(OSXStandardEventHandlerImpl.class);

        binder.bind(GlobalKeyEventHandler.class).to(GlobalKeyEventHandlerImpl.class);

        binder.bind(HelpViewer.class).to(HelpViewerOSX.class);
//        if (Sys.isMacOSX()) {
////            System.out.println("mac");
//        } else if (Sys.isLinux()) {
////            System.out.println("linu");
//            binder.bind(HelpViewer.class).to(HelpViewerOSX.class); // TODO
//        } else {
////            System.out.println("else");
//            binder.bind(HelpViewer.class).to(HelpViewerOSX.class); // TODO
//        }

        binder.bind(PrefBuilder.class).annotatedWith(Names.named(LOCALE)).to(LocalePrefBuilderImpl.class);
        binder.bind(PrefBuilder.class).annotatedWith(Names.named(TEXTEDITOR)).to( TextEditorPref.class);

        binder.bind( new TypeLiteral<Property<Integer>>() {} ).
                annotatedWith( Names.named( CaretStyleProperty.KEY )).
                toProvider( CaretStyleProperty.class ).
                in( Singleton.class );



    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("---- ui --- ");
        return obj != null && obj instanceof UIWiring;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
