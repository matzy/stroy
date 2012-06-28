package org.openCage.utils.prop.poc;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.openCage.utils.prop.PersistentPropStoreFactory;
import org.openCage.utils.prop.PropStore;
import org.openCage.utils.prop.PropStoreFactory;
import org.openCage.utils.prop.Prop;
import org.openCage.utils.persistence.Persistence;
import org.openCage.utils.persistence.InMemoryPeristence;

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
public class TestModule extends AbstractModule {

    protected void configure() {

        // this binds a property with name "foo"
        // the singleton is necesarry to share this one prop
        bind( new TypeLiteral<Prop<String>>() {}).annotatedWith( Names.named( "foo"  )).
                toProvider( StringPropFooProvider.class ).in( Singleton.class );

        bind( new TypeLiteral<Prop<String>>() {}).annotatedWith( Names.named( "woo"  )).
                toProvider( StringPropWooProvider.class ).in( Singleton.class );

        bind( new TypeLiteral<Prop<Double>>() {}).annotatedWith( Names.named( "pi" )).
                toProvider( PiPropProvider.class ).in( Singleton.class );

        // there should be no store sharing
        // TODO Singleton where?
        bind( PropStoreFactory.class ).to( PersistentPropStoreFactory.class );
        bind( new TypeLiteral<Persistence<PropStore>>() {} ).
                to( new TypeLiteral<InMemoryPeristence<PropStore>>() {} ).
                in( Singleton.class );
    }
}
