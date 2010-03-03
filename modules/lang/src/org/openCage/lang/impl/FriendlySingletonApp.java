package org.openCage.lang.impl;

import org.openCage.lang.protocol.SingletonApp;

import java.io.File;
import java.io.IOException;

public class FriendlySingletonApp implements SingletonApp {

    private final boolean isMaster;

    public FriendlySingletonApp( File markerFile ) {

        isMaster = !markerFile.exists();

        if ( isMaster ) {
            try {
                markerFile.getParentFile().mkdirs();
                markerFile.createNewFile();
                //FileExistence.createNewFile( markerFile ); // would need depemdecy to io
                // TODO find a better home
            } catch (IOException e) {
                throw new IllegalStateException( "can't create singletonapp marker file " + markerFile.getAbsolutePath() );
            }
            markerFile.deleteOnExit();
        }
    }

    @Override
    public boolean isMaster() {
        return isMaster;
    }
}
