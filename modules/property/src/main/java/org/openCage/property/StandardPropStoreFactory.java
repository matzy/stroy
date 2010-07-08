package org.openCage.property;

import com.google.inject.Inject;
import org.openCage.io.SingletonApp;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.artifact.Artifact;


public class StandardPropStoreFactory  {
    private BackgroundExecutor executor;
    private Artifact arti;
    private SingletonApp single;


    @Inject
    public StandardPropStoreFactory( SingletonApp singleApp, BackgroundExecutor executor, Artifact arti ) {
        this.executor = executor;
        this.arti = arti;
        this.single = singleApp;
    }

    public PropStore get( String name ) {
        return new PersistingPropStore( executor,
                FSPathBuilder.getPreferences().add( arti.gettName(), name).toFile(),
                null,
                single );
    }


}
