package org.openCage.property;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import org.openCage.appstd.AppStdWiring;

public class StandardPropertyWiring implements Module {

    public static class StdPropStoreProvider implements Provider<PropStore> {
        private StandardPropStoreFactory spsf;

        @Inject
        public StdPropStoreProvider( StandardPropStoreFactory spsf ) {
            this.spsf = spsf;
        }

        @Override
        public PropStore get() {
            return spsf.get( "std.xml" );
        }
    }

    public static class TransPropStoreProvider implements Provider<PropStore> {
        private StandardPropStoreFactory spsf;

        @Inject
        public TransPropStoreProvider( StandardPropStoreFactory spsf ) {
            this.spsf = spsf;
        }

        @Override
        public PropStore get() {
            return spsf.get( "trans.xml" );
        }
    }

    @Override public void configure(Binder binder) {

        binder.install( new AppStdWiring());

        binder.bind( PropStore.class ).
                annotatedWith( Names.named( PropertyConstants.STANDARD_PROPSTORE )).
                toProvider( StdPropStoreProvider.class );

        binder.bind( PropStore.class ).
                annotatedWith( Names.named( PropertyConstants.TRANSIENT_PROPSTORE )).
                toProvider( StdPropStoreProvider.class );

    }

    @Override public int hashCode() {
        return StandardPropertyWiring.class.hashCode();
    }

    @Override public boolean equals(Object o) {
        return o != null && (o instanceof StandardPropertyWiring);
    }
}
