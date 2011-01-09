package org.openCage.appstd;

import com.google.inject.Inject;
//import org.openCage.artig.stjx.Artifact;
//import org.openCage.artig.stjx.Deployed;
import org.openCage.io.FriendlySingletonApp;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.pom4app.AppMeta;

public class StdSingleton extends FriendlySingletonApp {

    @Inject
    public StdSingleton( AppMeta arti ) {
        super( FSPathBuilder.getPreferences().add( arti.getName(), "application.runs").toFile());
    }
}
