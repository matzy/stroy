package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.BindingBuilder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

public class OsashosaBinder implements Binder {

    private ESet<BindingBuilder<?>> bindingBuilders = new ESet<BindingBuilder<?>>();
    private Set<Module> known = new HashSet<Module>();
    private String moduleName;
    private boolean allowOverride = false;

    @Override
    public void install(Module module) {
        if ( !known.contains( module )) {
            known.add( module );
            String current = moduleName;
            moduleName = module.getClass().getName();
            module.configure( this );
            moduleName = current;
        } else {
            //System.out.println("known module installed: " + module.getClass().getName() );
        }
    }

    @Override
    public <T> BindingBuilder<T> bind(Class<T> clazz) {
        return bind( Key.get( clazz ) );
    }

    @Override
    public <T> BindingBuilder<T> bind(TypeLiteral<T> literal) {
        return bind( new Key<T>( literal ));
    }

    private <T> BindingBuilder<T> bind( Key key) {
        BindingBuilder<T> builder = new BindingBuilder( this, key );
        builder.setModuleName( moduleName );
        return builder;

    }

    public <T> BindingBuilder<T> bind( BindingBuilder<T> builder ) {
        if ( bindingBuilders.contains( builder )) {
            if ( !allowOverride ) {
                String msg = "Class " + builder.getKey() + (!(builder.getName().isEmpty()) ? (" named " + builder.getName()) : "") + "\n";

                msg += "   was bound to " + bindingBuilders.get( builder ) + "\n";
                msg += "   anf now to " + builder;
                throw new IllegalArgumentException( msg );
            }
        }

        bindingBuilders.add( builder );
        return builder;
    }

    public Collection<BindingBuilder<?>> getBuilders() {
        return bindingBuilders;
    }

    public void setCurrentModuleName(String name) {
        this.moduleName = name;
    }

    public void install( OsashosaBinder other ) {
        bindingBuilders.addAll( other.bindingBuilders );
    }

    public void setAllowOverride(boolean allowOverride) {
        this.allowOverride = allowOverride;
    }

    public boolean getAllowOverride() {
        return allowOverride;
    }
}
