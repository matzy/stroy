package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFileChooser;
import org.openCage.localization.protocol.Localize;
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

public class FileChooserGeneral implements FileChooser {

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
        FileDialog fd = new FileDialog(fr, localize.localize( "org.openCage.ui.chooseAFile"), FileDialog.LOAD);
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
        FileDialog fd = new FileDialog(fr, "org.openCage.localization.dict.saveAs", FileDialog.SAVE);
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
