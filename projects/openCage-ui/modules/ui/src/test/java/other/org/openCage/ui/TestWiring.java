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
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/
@Ignore
public class TestWiring implements Module {

    public static class DeployedProvider extends GetDeployed implements Provider<Deployed> {
        public DeployedProvider() {
            super( "duda" );
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
