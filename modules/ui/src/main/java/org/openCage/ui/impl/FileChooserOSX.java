package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.awt.*;

import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.localization.Localize;
import org.openCage.ui.protocol.FileChooser;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class FileChooserOSX implements FileChooser {

    private final Localize localize;

    @Inject
    public FileChooserOSX( @Named( "ui") Localize localize ) {
        this.localize = localize;
    }

    /**
     * open a file filebrowser and allow directory selection only
     * @param fr Frame a swing frame
     * @param path A path to start from.
     * @return A valid directory or null
     */
    @Override
    public String getDir( Frame fr, String path ) {
        return getDirInternal( fr, path);
    }

    @Override
    public String getDir(Dialog fr, String path) {
        return getDirInternal( fr, path);
    }

    private String getDirInternal( Object fr, String path) {
        // OSX has filters for directories: use native osx finder
        System.setProperty( "apple.awt.fileDialogForDirectories", "true" );

        FileDialog fd = fileDialog( fr, localize. localize("org.openCage.ui.chooseADir"), FileDialog.LOAD );
        fd.setDirectory( path );
        fd.setVisible( true );

        String dir = fd.getDirectory();
        String name = fd.getFile();

        if ( name == null ) {
            return null;
        }

        return FSPathBuilder.getPath( dir ).add( name ).toString();

    }

    @Override
    public String open( Frame fr, String path ) {
        return openInternal( fr, path);
    }

    @Override
    public String open(Dialog fr, String path) {
        return openInternal( fr, path);
    }

    private String openInternal( Object df, String path) {
        FileDialog fd = fileDialog( df, localize.localize( "org.openCage.ui.chooseAFile"), FileDialog.LOAD );
        fd.setDirectory( path );
        fd.setVisible( true );

        String dir = fd.getDirectory();
        String name = fd.getFile();

        if ( name == null ) {
            return null;
        }

        return FSPathBuilder.getPath( dir ).add( name ).toString();

    }

    @Override public String saveas( Frame fr, String path ) {
        return saveasInternal( fr, path );
    }

    @Override public String saveas(Dialog fr, String path) {
        return saveasInternal( fr, path );
    }

    private String saveasInternal( Object fr, String path) {
        FileDialog fd = fileDialog( fr, localize.localize( "org.openCage.localization.dict.saveAs"), FileDialog.SAVE );
        fd.setDirectory( path );
        fd.setVisible( true );

        String dir = fd.getDirectory();
        String name = fd.getFile();
        if ( name == null ) {
            return null;
        }

        return FSPathBuilder.getPath( dir ).add( name ).toString();
    }

    private FileDialog fileDialog( Object parent, String txt, int kind ) {
        if ( parent instanceof Frame) {
            return new FileDialog( (Frame)parent, txt, kind );
        }

        if ( parent instanceof Dialog ) {
            return new FileDialog( (Dialog)parent, txt, kind );
        }

        throw new IllegalArgumentException( "parent needs to be a Frame or a Dialog" );
    }
}
