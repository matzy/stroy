/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.xplatform.impl;

import com.google.inject.Inject;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import org.openCage.localization.protocol.Localize;
import org.openCage.xplatform.protocol.FileChooser;
import org.openCage.xplatform.protocol.FilePathBuilder;

/**
 *
 * @author stephan
 */
public class FileChooserOSX implements FileChooser {

    @Inject private Localize localize;
    @Inject private FilePathBuilder pathBuilder;


    /**
     * open a file filebrowser and allow directory selection only
     * @param fr Frame a swing frame
     * @param path A path to start from.
     * @return A valid directory or null
     */
    public String getDir( Frame fr, String path ) {

        // OSX has filters for directories: use native osx finder

        System.setProperty( "apple.awt.fileDialogForDirectories", "true" );
        FileDialog fd = new FileDialog( fr, localize. localize( "xplatform.choose_a_directory"), FileDialog.LOAD );
        fd.setDirectory( path );
        fd.setVisible( true );

        String dir = fd.getDirectory();
        String name = fd.getFile();

        if ( name == null ) {
            return null;
        }

        //return dir + File.pathSeparator + name;
        return pathBuilder.fromString( dir ).buildChild(name).getAbsolutePath();
    }

    public String open( Frame fr, String path ) {
    //	System.setProperty( "apple.awt.fileDialogForDirectories", "true" );
        FileDialog fd = new FileDialog( fr, "Choose a File", FileDialog.LOAD );
        fd.setDirectory( path );
        fd.setVisible( true );

        String dir = fd.getDirectory();
        String name = fd.getFile();

        if ( name == null ) {
        	return null;
        }

        return dir + name;
    }

    public String saveas( Frame fr, String path ) {
    	//System.setProperty( "apple.awt.fileDialogForDirectories", "true" );
        FileDialog fd = new FileDialog( fr, "Save As", FileDialog.SAVE );
        fd.setDirectory( path );
        fd.setVisible( true );

        String dir = fd.getDirectory();
        String name = fd.getFile();
        if ( name == null ) {
        	return null;
        }

        return dir + File.pathSeparator + name;
    }

}
