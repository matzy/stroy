/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFileChooser;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.FileChooser;

/**
 *
 * @author stephan
 */
public class FileChooserWindows implements FileChooser {

    @Inject @Named( "ui") private Localize localize;


    public String getDir(Frame fr, String path) {


        // Windows does not have a filter for directories lets  use the Java one
        // TODO Linux?

        JFileChooser chooser = new JFileChooser(path);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (0 == chooser.showOpenDialog(fr)) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    public String open(Frame fr, String path) {
        //	System.setProperty( "apple.awt.fileDialogForDirectories", "true" );
        FileDialog fd = new FileDialog(fr, localize.localize( "xpaltform.choose_a_file"), FileDialog.LOAD);
        fd.setDirectory(path);
        fd.setVisible(true);

        String dir = fd.getDirectory();
        String name = fd.getFile();

        if (name == null) {
            return null;
        }

        return dir + name;
    }

    public String saveas(Frame fr, String path) {
        //System.setProperty( "apple.awt.fileDialogForDirectories", "true" );
        FileDialog fd = new FileDialog(fr, "Save As", FileDialog.SAVE);
        fd.setDirectory(path);
        fd.setVisible(true);

        String dir = fd.getDirectory();
        String name = fd.getFile();
        if (name == null) {
            return null;
        }
        return dir + File.pathSeparator + name;
    }
}
