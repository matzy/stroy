package org.openCage.osashosa;

import com.google.inject.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
        return bind( Key.get(clazz) );
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
                msg += "   and now to " + builder;
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
