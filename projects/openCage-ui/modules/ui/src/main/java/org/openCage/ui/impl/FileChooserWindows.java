package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.awt.*;
import java.io.File;
import javax.swing.JFileChooser;

import org.openCage.localization.Localize;
import org.openCage.ui.protocol.FileChooser;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class FileChooserWindows implements FileChooser {

    @Inject @Named( "ui") private Localize localize;


    public String getDir(Frame fr, String path) {


        // Windows does not have a filter for directories lets  use the Java one

        JFileChooser chooser = new JFileChooser(path);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (0 == chooser.showOpenDialog(fr)) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    @Override
    public String getDir(Dialog fr, String path) {
        // Windows does not have a filter for directories lets  use the Java one

        JFileChooser chooser = new JFileChooser(path);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (0 == chooser.showOpenDialog(fr)) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    @Override
    public String open(Frame fr, String path) {
        return openInternal( fr, path );
    }

    @Override
    public String open(Dialog fr, String path) {
        return openInternal( fr, path );
    }

    private String openInternal( Object fr, String path) {

        FileDialog fd = fileDialog(fr, localize.localize( "org.openCage.ui.chooseAFile"), FileDialog.LOAD);
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
        return saveasInternal( fr, path );
    }

    @Override
    public String saveas(Dialog fr, String path) {
        return saveasInternal( fr, path );
    }

    public String saveasInternal( Object fr, String path) {
        FileDialog fd = fileDialog(fr, localize.localize( "org.openCage.localization.dict.saveAs"), FileDialog.SAVE);
        fd.setDirectory(path);
        fd.setVisible(true);

        String dir = fd.getDirectory();
        String name = fd.getFile();
        if (name == null) {
            return null;
        }
        return dir + File.pathSeparator + name;
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
