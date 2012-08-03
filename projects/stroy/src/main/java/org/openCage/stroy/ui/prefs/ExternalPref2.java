package org.openCage.stroy.ui.prefs;

import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.comphy.property.ImmuProp;
import org.openCage.comphy.property.MapProperty;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.F2;
import org.openCage.lang.inc.Str;
import org.openCage.stroy.file.Action;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.file.ImmuBridge;
import org.openCage.stroy.file.SimilarityAlgorithm;
import org.openCage.stroy.locale.Message;
import org.openCage.util.prefs.EComboBox;
import org.openCage.util.prefs.MComboBox;
import org.openCage.util.prefs.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import static org.openCage.lang.inc.Strng.S;

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

public class ExternalPref2 extends JPanel {

    private JFrame frame;
    private FileTypes fileTypes;
    private MapProperty<ImmuProp<Str>> progList;
    private final JList extensions;
    private MComboBox openCombo;
    private TextField descrText;
    private EComboBox algoCombo;


    public ExternalPref2( final JFrame frame, final FileTypes filesTypes, MapProperty<ImmuProp<Str>> progList) {

        this.frame = frame;

        this.fileTypes = filesTypes;
        this.progList  = progList;
        extensions     = createExtensions();

        final ImmuBridge<Action> openBridge = new ImmuBridge<Action>( new F1<Str, Action>() {
            @Override
            public Str call(Action action) {
                return S(action.getOpen());
            }
        }, new F2<Void, Action, Str>() {
            @Override
            public Void call(Action action, Str open) {
                action.setOpen( open.get() );
                return null;
            }
        });

        this.openCombo = new MComboBox( progList, openBridge.getProp() );

        final ImmuBridge<Action> decrBridge = new ImmuBridge<Action>( new F1<Str, Action>() {
            @Override
            public Str call(Action action) {
                return S(action.getDescription());
            }
        }, new F2<Void, Action, Str>() {
            @Override
            public Void call(Action action, Str open) {
                action.setDescription(open.get());
                return null;
            }
        });

        descrText = new TextField( decrBridge.getProp() );

        final ImmuBridge<Action> algoBridge = new ImmuBridge<Action>( new F1<Str, Action>() {
            @Override
            public Str call(Action action) {
                return S(action.getAlgo().toString());
            }
        }, new F2<Void, Action, Str>() {
            @Override
            public Void call(Action action, Str open) {
                action.setAlgo( SimilarityAlgorithm.valueOf( open.get()));
                return null;
            }
        });

        algoCombo = new EComboBox( SimilarityAlgorithm.class, algoBridge.getProp() );



        extensions.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String ext = (String) extensions.getSelectedValue();
                Action action = filesTypes.getAction( S(ext) );
                openBridge.set( action );
                decrBridge.set( action );
                algoBridge.set( action);
//                descriptionField.setText( fileTypes.getDescription( ext ));
//                algoBox.setSelectedItem( algo2mesg.get( fileTypes.getAlgo(ext ).toString() ));
//                setDiff(ext);
//                setOpen(ext);
//                setEnabledAll( true );
            }
        });



        createExtensions();
        createLayout();


    }

    private JList createExtensions() {
        List<String> exts = new ArrayList<String>( fileTypes.getTypeList());
        Collections.sort(exts);
        return new JList( new Vector(exts ));
    }

    private void createLayout() {
        DesignGridLayout dgl = new DesignGridLayout(this);

        JScrollPane lp = new JScrollPane(extensions);

        final JLabel descr    = new JLabel(Message.get("Pref.FileType.description"));
        final JLabel algo     = new JLabel(Message.get("Pref.FileType.algo"));
        final JLabel external = new JLabel(Message.get("Pref.FileType.external"));
        final JLabel open     = new JLabel(Message.get("Pref.FileType.open"));

        dgl.row().grid().add( lp ).add( descr    ).add( descrText );
        dgl.row().grid().spanRow().add( algo     ).add( algoCombo);
        dgl.row().grid().spanRow().add( external ).add( new JButton( "drop" ));
        dgl.row().grid().spanRow().add( open     ).add( openCombo );
    }

    public void showExtension(String extension) {
        extensions.setSelectedValue( extension, true );

    }
}
