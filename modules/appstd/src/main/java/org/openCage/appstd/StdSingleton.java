package org.openCage.appstd;

import com.google.inject.Inject;
import org.openCage.artig.stjx.Artifact;
import org.openCage.io.FriendlySingletonApp;
import org.openCage.io.fspath.FSPathBuilder;

public class StdSingleton extends FriendlySingletonApp {

    @Inject
    public StdSingleton( Artifact arti ) {
        super( FSPathBuilder.getPreferences().add( arti.getName(), "application.runs").toFile());
    }
}
