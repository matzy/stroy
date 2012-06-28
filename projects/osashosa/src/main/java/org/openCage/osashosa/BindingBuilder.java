package org.openCage.osashosa;

import com.google.inject.*;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.name.Named;

import java.lang.annotation.Annotation;

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

public class BindingBuilder<T> implements AnnotatedBindingBuilder<T>{

    // only one of the following can be not null
    private Class<? extends T> to;
    private Class<? extends Provider<? extends T>> provider;
    private Provider<? extends T> concreteProvider;

    private String name = "";
    private Class scope;
    private final Key<T> key;
    private T singleton;
    private String moduleName;
    private final OsashosaBinder binder;
    private boolean eager = false;

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

    @Override
    public ScopedBindingBuilder to(Key<? extends T> key) {
        throw new Error("impl me");
    }

    public ScopedBindingBuilder to(Class<? extends T> clazz) {
        if ( clazz.isAssignableFrom( Provider.class ) ) {
            // TODO huh
            int i = 0;
        }
        to = clazz;
        if ( binder != null ) {
            binder.bind( this );
        }
        return this;
    }

    public ScopedBindingBuilder to(TypeLiteral<? extends T> implementation) {
        to = (Class<? extends T>)implementation.getRawType();
        if ( binder != null ) {
            binder.bind( this );
        }
        return this;
    }


    public ScopedBindingBuilder toProvider( Class<? extends Provider<? extends T>> provider ) {
        this.provider = provider;
        if ( binder != null ) {
            binder.bind( this );
        }
        return this;
    }

    @Override
    public ScopedBindingBuilder toProvider(Provider<? extends T> provider) {
        this.concreteProvider = provider;
        if ( binder != null ) {
            binder.bind( this );
        }
        return this;
    }

    @Override
    public ScopedBindingBuilder toProvider(Key<? extends Provider<? extends T>> key) {
        throw new Error("impl me");
    }

    @Override
    public LinkedBindingBuilder<T> annotatedWith(Class<? extends Annotation> aClass) {
        throw new Error("impl me");
    }

    @Override
    public LinkedBindingBuilder<T> annotatedWith(Annotation annotation) {
        if ( !(annotation instanceof Named )) {
            throw new Error("impl me");
        }

        this.name = ((Named)annotation).value();
        return this;
    }

    public void in( Class<? extends Annotation> scope ) {
        this.scope = scope;
    }

    @Override
    public void in(Scope scope) {
        throw new Error( "impl me" );
    }

    public void asEagerSingleton() {
        scope = Singleton.class;
        eager = true;
    }

    public void toInstance( T instance ) {
        scope = Singleton.class;
        // eager = true; TODO not setting this is a bit strange
        // the value is already set and needs not be constructed, maybe can;t
        singleton = instance;
        if ( binder != null ) {
            binder.bind( this );
        }
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
        String ret = "BindingBuilder{";

        if ( scope != null ) {
            ret += " scope = " + scope;
        }

        if ( moduleName != null ) {
            ret += " Module = " + moduleName;
        }

        if ( name != null && !name.isEmpty()) {
            ret += " Named( " + name +" )";
        }

        ret += " Key = " + key;

        if ( to != null ) {
            ret += " to = " + to;
        }

        if ( provider != null ) {
            ret += " provider = " + provider;
        }

        return ret;
    }

    public boolean isEager() {
        return eager;
    }

    public Provider<? extends T> getConcreteProvider() {
        return concreteProvider;
    }
}
