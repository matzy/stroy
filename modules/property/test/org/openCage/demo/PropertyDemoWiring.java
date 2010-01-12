package org.openCage.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import org.openCage.lang.protocol.LangWiring;
import org.openCage.property.impl.PropStoreImpl;
import org.openCage.property.protocol.PropStore;
import org.openCage.property.protocol.Property;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 23, 2009
 * Time: 2:24:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyDemoWiring implements Module {
    @Override
    public void configure(Binder binder) {
        binder.install( new LangWiring());

        binder.bind( PropStore.class ).toProvider( PropStoreProvider.class );

        binder.bind( new TypeLiteral<Property<String>>() {} ).
                annotatedWith( Names.named("demo")).
                toProvider( FooPropertyProvider.class ).
                in( Singleton.class );

        binder.bind( new TypeLiteral<Property<Integer>>() {} ).
                annotatedWith( Names.named( DuhProp.HICKER )).
                toProvider( DuhProp.class ).
                in( Singleton.class );

    }
}