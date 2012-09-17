package org.openCage.util.external;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.kleinod.io.FileUtils;
import org.openCage.kleinod.observe.ObservableRef;
import org.openCage.stroy.file.FileTypes;
import org.openCage.util.logging.Log;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.openCage.kleinod.errors.Unchecked.wrap;
import static org.openCage.kleinod.io.IOUtils.closeQuietly;

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

public class OSXDesktopX implements DesktopX {

    private final ObservableRef<String> editor;
    private final FileTypes fileTypes;

    @Inject
    public OSXDesktopX(@Named("Editor") ObservableRef<String> editor, FileTypes fileTypes) {
        this.editor = editor;
        this.fileTypes = fileTypes;
    }


    @Override
    public void open( File file ) {
        String cmd = fileTypes.getOpen(FileUtils.getExtension(file));

        if ( isStandardOpen(cmd ) ) {
            try {
                Desktop.getDesktop().open( file );
                return;
            } catch (IOException e) {
                throw wrap(e);
            }
        }

        openWith( cmd, file );
    }

    private void openWith(String prog, File file) {

        if ( !prog.endsWith(".app")) {
            throw new IllegalArgumentException("not a app");
        }

        BufferedReader input = null;

        try {
            String[] cmd = new String[ 4 ];

            cmd[0] = "open";
            cmd[1] = "-a";
            cmd[2] = prog;
            cmd[3] = file.getAbsolutePath();

//            int idx = 3;               x
//            for ( String arg : args ) {
//                cmd[idx] = arg;
//                idx++;
//
//            }

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            input = new BufferedReader( new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( "external prog " + prog + " threw " + e1  );
        } finally {
            closeQuietly( input );
        }
    }


    @Override
    public void openAsText(File file) {
        if ( isStandarEditor() ) {
            openWithStandardEditor( file );
            return;
        }

        openWith( editor.get(), file );

    }

    @Override
    public void edit(File file) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getStandardEditorName() {
        return "TextEdit";
//        if ( Sys.isMacOSX()) {
//            stdText = "TextEdit";
//        } else if ( Sys.isWindows() ) {
//            stdText = "Notepad";
//        } else if ( Sys.isLinux() ) {
//            stdText = "vi";
//        }

    }

    @Override
    public String getStandardDiffProgName() {
////        if ( Sys.isMacOSX()) {
//            stdDiffProg = "FileMerge";
//        } else if ( Sys.isWindows() ) {
//            stdDiffProg = "WinMerge";
//        } else if ( Sys.isLinux() ) {
//            stdDiffProg = "diff";
//        }

        return "FileMerge";
    }

    public void openWithStandardEditor(File file) {

        if ( !file.exists() ) {
            throw wrap( new FileNotFoundException("file not found " + file.getAbsolutePath()));
        }

        BufferedReader input = null;
        try {

            String[] cmd = new String[3];
            cmd[0] = "/usr/bin/open";
            cmd[1] = "-e";
            cmd[2] = file.getAbsolutePath();

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            input = new BufferedReader( new InputStreamReader( proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e1) {
            Log.warning("open -e" + file + "threw " + e1);
        } finally {
           closeQuietly(input);
        }
    }

    private boolean isStandarEditor() {
        return editor.get().equals(DesktopXs.OS_STANDARD_TEXT_EDITOR);
    }

    private boolean isStandardOpen(String type ) {
        return type.equals(DesktopXs.STANDARD_OPEN);



    }
}
