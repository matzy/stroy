//package org.openCage.gpad;
//
//import org.apache.commons.io.FileUtils;
//import org.jetbrains.annotations.NotNull;
//import org.openCage.lang.BackgroundExecutor;
//import org.openCage.lang.errors.Unchecked;
//import org.openCage.lang.functions.F1;
//import org.openCage.lang.functions.FV;
//import org.openCage.localization.Localize;
//
//import javax.swing.JTextArea;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
///**
// * connect a file to a JTextArea
// * TODO generalize and move to ui package
// * i.e. make it into an binding between a textarea and a file with a possible filter (e.g. encryption inbetween)
// */
//public class UI2File {
//
//    private final JTextArea               textArea;
//    private final TextEncoderIdx<String,String>  textEncoder  = new FaustString();
//    private final Localize localize;
//
//    private File                     file;
//    private URI                      pad;
//    private boolean                  encoded;
//    private boolean                  changed = false;
//    private boolean                  writable = true;
//    private List<F1<Void, Boolean>> observe = new ArrayList<F1<Void, Boolean>>();
//    private static final Logger LOG = Logger.getLogger( UI2File.class.getName());
//    private boolean warned = false;
//
//    public UI2File( @NotNull JTextArea area,
//                    @NotNull BackgroundExecutor executor,
//                    @NotNull Localize localize,
//                    @NotNull File file ) {
//        this.textArea = area;
//        this.localize = localize;
//        this.file     = file;
//
//
//        executor.addPeriodicAndExitTask( new FV() {
//            @Override public void call() {
//                write();
//            }
//        });
//
//        textArea.addKeyListener( new KeyAdapter() {
//            @Override public void keyTyped(KeyEvent e) {
//                if ( textArea.isEditable() && !changed ) {
//                    changed = true;
//                }
//            }
//        });
//
//        init();
//        setInitialText();
//    }
//
//    private void init() {
//        pad = null;
//        changed = false;
//        textArea.setText("");
//        warned = false;
//    }
//
//    private void setInitialText() {
//
//        if ( file.exists() ) {
//            if ( !file.canWrite()) {
//                writable = false;
//            }
//
//            try {
//                textArea.setText( FileUtils.readFileToString( file ));
//                encoded = true;
//                changed = false;
//            } catch (IOException e) {
//                throw Unchecked.wrap( e );
//            }
//
//            return;
//        }
//
//        textArea.append( localize.localize("org.openCage.fausterize.intro"));
//        encoded = false;
//    }
//
//
//    public synchronized void codeToggle() {
//
//        if ( pad == null ) {
//            return;
//        }
//
//        if ( encoded ) {
//            textArea.setText( textEncoder.decode( textArea.getText(), 0 ));
//            encoded = false;
//            changed = false;
//        } else {
//            if ( changed ) {
//                write();
//                textArea.setText( textEncoder.encode( textArea.getText(), 0 ));
//                encoded = true;
//                changed = false;
//            } else {
//                // the text is decoded, but the wrong pad might have been used
//                // so lets just read from disk
//                init();
//                setInitialText();
//            }
//        }
//    }
//
//    private boolean canWrite() {
//        return file != null && pad != null && changed && !encoded && writable;
//    }
//
//    synchronized void write() {
//        if ( canWrite() ) {
//            // make sure the directory exists
//            file.getParentFile().mkdirs();
//
//            assert !encoded;
//            if ( encoded ) {
//                throw new IllegalStateException( "we should not be here" );
//            }
//
//            try {
//                FileUtils.writeStringToFile( file, textEncoder.encode( textArea.getText(), 0 ));
//
//                if ( warned ) {
//                    warned = false;
//                    LOG.warning( "file writable again " + file.getAbsolutePath()  );
//                    for ( F1<Void, Boolean> listener : observe ) {
//                        try {
//                            listener.call( true );
//                        } catch ( Exception exp ) {
//                            LOG.warning( exp.getMessage() );
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                LOG.warning( "file suddenly readonly " + file.getAbsolutePath()  );
//                if ( !warned ) {
//                    warned = true;
//                    for ( F1<Void, Boolean> listener : observe ) {
//                        try {
//                            listener.call( false );
//                        } catch ( Exception exp ) {
//                            LOG.warning( exp.getMessage() );
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private synchronized void writeEncoded() {
//
//
//        if ( file == null || !encoded ) {
//            throw new IllegalStateException( "called in wrong context" );
//        }
//
//        // make sure the directory exists
//        file.getParentFile().mkdirs();
//
//        try {
//            //System.out.println("writing " + toString());
//            FileUtils.writeStringToFile( file, textArea.getText() );
//        } catch (IOException e) {
//            // huh, we just checked that the file either exists writable or is new
//            // so we probably have a new file which is not writabel
//            throw Unchecked.wrap( new IOException("not writable"));
//        }
//    }
//
//
//    public void setPad( @NotNull URI pad ) {
//        this.pad  = pad;
//        textEncoder.setPad( pad);
//    }
//
//    public boolean isPadSet() {
//        return pad != null;
//    }
//
//
//    public boolean isEncoded() {
//        return encoded;
//    }
//
//    /**
//     *  switch to a new file, new content
//     * @param file
//     */
//    public void setFile( @NotNull File file ) {
//        if ( file.exists() && !file.canRead() ) {
//            throw new Unchecked( new IOException( "file can not be read: " + file.getAbsolutePath() ) );
//        }
//
//        write();
//        init();
//        this.file = file;
//        setInitialText();
//    }
//
//    /**
//     * switch to a new file, same content (save as)
//     * @param file
//     */
//    public void changeFile(File file) {
//        this.file = file;
//        if ( file.exists() && !file.canWrite()) {
//            throw new Unchecked( new IOException( "file is readonly" ));
//        }
//
//        if ( encoded ) {
//            writeEncoded();
//        } else {
//            changed = true; // force a write out
//            writable = true; // be optimistic
//            write();
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "ui2file pad: " + pad + " file: " + file + " encoded " + encoded + " changed " + changed + " writable " + writable;
//    }
//
//    public boolean isWritable() {
//        return writable;
//    }
//
//
//    public void addWriteProblemListener( F1<Void, Boolean> listener ) {
//        this.observe.add( listener );
//    }
//}
