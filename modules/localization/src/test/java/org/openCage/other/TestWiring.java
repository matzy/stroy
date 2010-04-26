package org.openCage.other;

import com.google.inject.name.Names;
import org.junit.Ignore;
import org.openCage.lang.impl.BackgroundExecutorImpl;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.property.protocol.PropStore;

@Ignore
public class TestWiring implements Module {

    private static boolean once;

    public void configure(Binder binder) {
//
//        if ( once ) {
//            return;
//        }
//        once = true;

        binder.install( new LocalizeWiring());

        binder.bind( Localize.class ).
                annotatedWith( Names.named("testing")).
                toProvider( LocalizeProvider.class );
        binder.bind( PropStore.class ).
                annotatedWith( Names.named("std")).
                toProvider( PropStoreProvider.class );

        binder.bind(BackgroundExecutor.class ).
                to(BackgroundExecutorImpl.class );


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
