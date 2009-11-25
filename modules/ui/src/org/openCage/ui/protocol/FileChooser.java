/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.ui.protocol;

import java.awt.Frame;

/**
 *
 * @author stephan
 */
public interface FileChooser {

    /**
     * open a file filebrowser and allow directory selection only
     * @param fr Frame a swing frame
     * @param path A path to start from.
     * @return A valid directory or null
     */
    public String getDir( Frame fr, String path );

    /**
     * return a path to a file via the os native dialog.
     * @param fr
     * @param path
     * @return A valid filepath or null
     */
    public String open( Frame fr, String path );

    /**
     * return a path to a file via the os native dialog. (saveas title)
     * @param fr
     * @param path
     * @return A valid filepath or null
     */
    public String saveas( Frame fr, String path );




}
