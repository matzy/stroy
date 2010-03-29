package org.openCage.property.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import org.openCage.lang.protocol.LangWiring;
import org.openCage.property.clazz.DummyPropStore;
import org.openCage.property.impl.NonPersistingPropStoreProvider;
import org.openCage.property.protocol.PropStore;
import org.openCage.property.protocol.Property;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 28-mar-2010
 * Time: 13:20:25
 * To change this template use File | Settings | File Templates.
 */
public class DemoWiring implements Module {
    
    @Override
    public void configure(Binder binder) {
        binder.install( new LangWiring());

        binder.bind( PropStore.class ).
                annotatedWith( Names.named( "std" )).
                to( DummyPropStore.class );

//        binder.bind( PropStore.class ).
//                annotatedWith( Names.named( "std" )).
//                toProvider( NonPersistingPropStoreProvider.class );

        binder.bind( new TypeLiteral<Property<String>>() {} ).
                annotatedWith( Names.named("Work")).
                toProvider( WorkProp.class ).
                in( Singleton.class );
//
//        binder.bind( new TypeLiteral<Property<Integer>>() {} ).
//                annotatedWith( Names.named( DuhProp.HICKER )).
//                toProvider( DuhProp.class ).
//                in( Singleton.class );
//
//        binder.bind( new TypeLiteral<Property<String>>() {} ).
//                annotatedWith( Names.named( OtherStoreProp.KEY )).
//                toProvider( OtherStoreProp.class ).
//                in( Singleton.class );
    }

}
