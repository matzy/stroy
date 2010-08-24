package org.openCage.appstd;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import org.openCage.artig.stjx.Deployed;
import org.openCage.io.SingletonApp;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.BackgroundExecutorImpl;

public class AppStdWiring implements Module {

    @Override public void configure(Binder binder) {

        binder.bind( SingletonApp.class ).to( StdSingleton.class ).in( Singleton.class ) ;

        binder.bind(BackgroundExecutor.class ).to(BackgroundExecutorImpl.class );

        binder.bind( Deployed.class ).toProvider( DeployedProvider.class );

    }

    @Override public boolean equals(Object o) {
        return o instanceof AppStdWiring;
    }

    @Override public int hashCode() {
        return AppStdWiring.class.hashCode();
    }
}
