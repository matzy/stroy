package org.openCage.gpad;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.openCage.application.protocol.Application;
import org.openCage.application.wiring.ApplicationWiring;
import org.openCage.gpad.providers.ApplicationProvider;
import org.openCage.gpad.providers.LocalizeProvider;
import org.openCage.gpad.providers.PropStoreProvider;
import org.openCage.lang.protocol.LangWiring;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;
import org.openCage.property.protocol.PropStore;
import org.openCage.ui.impl.pref.LocalePrefBuilderImpl;
import org.openCage.ui.protocol.PrefBuilder;
import org.openCage.ui.wiring.UIWiring;
import org.openCage.withResource.wiring.IoWiring;

import static org.openCage.gpad.Constants.FAUSTERIZE;
import static org.openCage.ui.Constants.LOCALE;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FausterizeWiring implements Module {
    public void configure(Binder binder) {
        binder.install( new ApplicationWiring());
        binder.install( new IoWiring());
        binder.install( new UIWiring());
        binder.install( new LangWiring());
        binder.install( new LocalizeWiring());

        binder.bind( Application.class ).toProvider( ApplicationProvider.class );
        binder.bind( PropStore.class ).toProvider( PropStoreProvider.class );
        binder.bind( Localize.class).
                annotatedWith( Names.named(FAUSTERIZE)).
                toProvider( LocalizeProvider.class);

        binder.bind(PrefBuilder.class ).annotatedWith( Names.named(FAUSTERIZE)).to(CodePrefBuilder.class);


    }
}
