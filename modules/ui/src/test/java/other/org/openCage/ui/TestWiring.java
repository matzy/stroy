package other.org.openCage.ui;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import org.junit.Ignore;
import org.openCage.artig.GetDeployed;
import org.openCage.artig.stjx.Deployed;
import org.openCage.property.NonPersistingPropStore;
import org.openCage.property.PropStore;
import org.openCage.property.PropertyConstants;
import org.openCage.ui.impl.about.AboutSheetFromApplication;
import org.openCage.ui.wiring.UIWiring;

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
@Ignore
public class TestWiring implements Module {

    public static class DeployedProvider extends GetDeployed implements Provider<Deployed> {
        public DeployedProvider() {
            super( AboutSheetFromApplication.class );
        }
    }

    public static class NonPersi implements Module {

        @Override
        public void configure(Binder binder) {
            binder.bind( PropStore.class ).
                    annotatedWith(Names.named(PropertyConstants.STANDARD_PROPSTORE)).
                    to( NonPersistingPropStore.class );
        }


    }

    public void configure(Binder binder) {
        binder.install( Modules.override( new UIWiring()).with( new NonPersi() ));
        binder.bind( Deployed.class ).toProvider( DeployedProvider.class );
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof TestWiring;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
