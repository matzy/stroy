package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.junit.Ignore;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.lang.artifact.Artifact;

@Ignore
public class ModuleTest {

    public static class StringProp implements Provider<String> {

        @Override
        public String get() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    public static class SimpleM implements Module {

        @Override
        public void configure(Binder binder) {
            binder.install( new Module() {
                @Override
                public void configure(Binder binder) {
                }
            });

            binder.bind(String.class ).
                    to( String.class );

            binder.bind( String.class ).toProvider( StringProp.class );

            binder.bind( String.class ).annotatedWith( Names.named("std"));

            binder.bind( String.class ).in( Singleton.class ) ;




        }
    }


    public void testCreate() {

    }
}
