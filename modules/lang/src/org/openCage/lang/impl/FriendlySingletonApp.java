package org.openCage.lang.impl;

import org.openCage.lang.protocol.SingletonApp;

import java.io.File;
import java.io.IOException;

public class FriendlySingletonApp implements SingletonApp {

    private final File markerFile;
    private final boolean isMaster;

    public FriendlySingletonApp( File markerFile ) {
        this.markerFile = markerFile;

        isMaster = !markerFile.exists();

        if ( isMaster ) {
            try {
                markerFile.createNewFile();
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
