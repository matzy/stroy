package org.openCage.property.protocol;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.lang.protocol.LangWiring;

public class PropertyWiring implements Module {
    @Override
    public void configure(Binder binder) {
        binder.install( new LangWiring());

//        binder.bind( PropStore.class ).toProvider( PropStoreProvider.class );


//        binder.bind( new TypeLiteral<Property<Integer>>() {} ).
//                annotatedWith( Names.named( DuhProp.HICKER )).
//                toProvider( DuhProp.class ).
//                in( Singleton.class );

    }

    @Override
    public int hashCode() {
        return PropertyWiring.class.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof PropertyWiring);
    }
}
