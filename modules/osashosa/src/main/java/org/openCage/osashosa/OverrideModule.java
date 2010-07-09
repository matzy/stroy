package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.openCage.osashosa.OsashosaBinder;

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

// todo naming
public abstract class OverrideModule implements Module, Modules.OverriddenModuleBuilder {

//    List<BindingBuilder> bindings = new ArrayList<BindingBuilder>();

    private OsashosaBinder binder = new OsashosaBinder();

    public OverrideModule(Module ... modules) {
        for ( Module mod : modules ) {
            binder.setCurrentModuleName( mod.getClass().getName() );
            mod.configure( binder );
        }


    }

    @Override
    public void configure( Binder binder ) {
        ((OsashosaBinder)binder).install( this.binder );
    }

    @Override
    public Module with(Module... modules) {

        boolean oldOverride = binder.getAllowOverride();

        try {
            binder.setAllowOverride( true );
            for ( Module mod : modules ) {
                binder.setCurrentModuleName( mod.getClass().getName() );
                mod.configure( binder );
            }
        } finally {
            binder.setAllowOverride( oldOverride );
        }


        return this;
    }
}
