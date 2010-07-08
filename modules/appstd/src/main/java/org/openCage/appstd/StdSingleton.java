package org.openCage.appstd;

import com.google.inject.Inject;
import org.openCage.io.FriendlySingletonApp;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.artifact.Artifact;

public class StdSingleton extends FriendlySingletonApp {

    @Inject
    public StdSingleton( Artifact arti ) {
        super( FSPathBuilder.getPreferences().add( arti.gettName(), "application.runs").toFile());
    }
}
