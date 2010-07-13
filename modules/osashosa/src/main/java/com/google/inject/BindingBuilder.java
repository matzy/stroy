package com.google.inject;

import com.google.inject.name.Named;
import org.openCage.osashosa.Key;
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

public class BindingBuilder<T> {

    private Class<? extends T> to;
    private Class<? extends Provider<? extends T>> provider;
    private String name = "";
    private Class scope;
    private final Key<T> key;
    private T singleton;
    private String moduleName;
    private final OsashosaBinder binder;

    BindingBuilder( OsashosaBinder binder, Class<T> clazz ) {
        this( binder, Key.get( clazz ));
    }

    BindingBuilder( OsashosaBinder binder, TypeLiteral<T> literal) {
        this( binder, new Key<T>( literal ));
    }

    public BindingBuilder( OsashosaBinder binder, Key<T> key) {
        this.binder = binder;
        this.key = key;
    }

    public BindingBuilder to(Class<? extends T> clazz) {
        if ( clazz.isAssignableFrom( Provider.class ) ) {
            int i = 0;
        }
        to = clazz;
        if ( binder != null ) {
            binder.bind( this );
        }
        return this;
    }

    public BindingBuilder toProvider( Class<? extends Provider<? extends T>> provider ) {
        this.provider = provider;
        if ( binder != null ) {
            binder.bind( this );
        }
        return this;
    }

    public BindingBuilder annotatedWith(Named named) {
        this.name = named.value();
        return this;
    }

    public BindingBuilder in( Class scope ) {
        this.scope = scope;
        return this;
    }


    public Class<? extends T> getTo() {
        return to;
    }

    public Class getProvider() {
        return provider;
    }

    public String getName() {
        return name;
    }

    public Class getScope() {
        return scope;
    }

    public Key<T> getKey() {
        return key;
    }

    public boolean isDirect() {
        return to != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BindingBuilder)) return false;

        BindingBuilder that = (BindingBuilder) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }

    public void setSingleton( T sing ) {
        this.singleton = sing;
    }

    public T getSingleton() {
        return singleton;
    }

    public boolean isSingletonScope() {
        return scope != null && scope == Singleton.class;
    }

    public void setModuleName( String name ) {
        this.moduleName = name;
    }

    @Override
    public String toString() {
        return "BindingBuilder{" +
                "to=" + to +
                ", provider=" + provider +
                ", name='" + name + '\'' +
                ", scope=" + scope +
                ", key=" + key +
                ", singleton=" + singleton +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
