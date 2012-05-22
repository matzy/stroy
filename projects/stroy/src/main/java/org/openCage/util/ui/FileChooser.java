package org.openCage.util.ui;

import com.muchsoft.util.Sys;

import javax.swing.*;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * Filechoosers for OSX Windows Linux
 */
public class FileChooser {

    /**
     * open a file filebrowser and allow directory selection only
     * @param fr Frame a swing frame
     * @param path A path to start from.
     * @return A valid directory or null
     */
    static public String getDir( Frame fr, String path ) {

        if ( false /* Sys.isMacOSX() */ ) {    // trouble with java 7 or lion ?

            // OSX has filters for directories: use native osx finder

            System.setProperty( "apple.awt.fileDialogForDirectories", "true" );
            FileDialog fd = new FileDialog( fr, "Choose a Directory", FileDialog.LOAD );
            fd.setDirectory( path );
            fd.setVisible( true );

            String dir = fd.getDirectory();
            String name = fd.getFile();

            if ( name == null ) {
                return null;
            }

            return dir + File.pathSeparator + name;
        } else {

            // Windows does not have a filter for directories lets  use the Java one
            // TODO Linux?

            JFileChooser chooser = new JFileChooser( path );
            chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
            if ( 0 == chooser.showOpenDialog( fr )) {
                return chooser.getSelectedFile().getAbsolutePath();
            } else {
                return null;
            }
        }
    }
    /**
     * open a file filebrowser and allow directory selection only
     * @param fr Frame a swing frame
     * @param path A path to start from.
     * @return A valid directory or null
     */
    static public String getAnyFile( Frame fr, String path ) {

        JFileChooser chooser = new JFileChooser( path );
        chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
        if ( 0 == chooser.showOpenDialog( fr )) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    /**
     * return a path to a file via the os native dialog.
     * @param fr
     * @param path
     * @return A valid filepath or null
     */
    static public String open( Frame fr, String path ) {
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

    /**
     * return a path to a file via the os native dialog. (saveas title)
     * @param fr
     * @param path
     * @return A valid filepath or null
     */
    static public String saveas( Frame fr, String path ) {
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
