package org.openCage.stroy.ui.prefs;

import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.observe.ObservableDetailRef;
import org.openCage.kleinod.observe.ObservableRef;
import org.openCage.kleinod.observe.ObservableReference;
import org.openCage.kleinod.ui.Binding;
import org.openCage.kleinod.ui.RootedButtonGroup;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.property.MapProperty;
import org.openCage.stroy.file.Action;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.file.SimilarityAlgorithm;
import org.openCage.stroy.locale.Message;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import static org.openCage.kleinod.collection.Forall.forall;


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
    private MapProperty<ObservableRef<String>> progList;
    private final JList extensions;
    private JComboBox openCombo = new JComboBox();
    private JTextField descrText = new JTextField();
    private JComboBox algoCombo = new JComboBox();
    private JCheckBox isText = new JCheckBox("isText");
    private ObservableReference<Action> currentAction = new ObservableRef<Action>(null);
    private final JCheckBox isXML = new JCheckBox(Message.get("Pref.FileType.isXML"));
    private final JCheckBox isJSON = new JCheckBox(Message.get("Pref.FileType.isJSON"));
    private final JCheckBox isBundle = new JCheckBox(Message.get("Pref.FileType.isBundle"));


    public ExternalPref2( final JFrame frame, final FileTypes filesTypes, MapProperty<ObservableRef<String>> progList) {

        this.frame = frame;

        this.fileTypes = filesTypes;
        this.progList  = progList;
        extensions     = createExtensions();

//        final ImmuBridge<Action> openBridge = new ImmuBridge<Action>( new F1<String, Action>() {
//            @Override
//            public String call(Action action) {
//                return (action.getOpen());
//            }
//        }, new F2<Void, Action, String>() {
//            @Override
//            public Void call(Action action, String open) {
//                action.setOpen( open );
//                return null;
//            }
//        });

//        this.openCombo = new MComboBox( progList, openBridge.getProp() );

        Binding.bind( openCombo ).
                outOf( forall(progList.keySet()).trans( new F1<String, ThreeKey>() {
                    @Override
                    public String call(ThreeKey threeKey) {
                        return threeKey.toString();
                    }
                }).toList() ).
                to(new ObservableDetailRef<String>(currentAction, "open"));

        Binding.bind( descrText, new ObservableDetailRef<String>( currentAction, "description" ));
        Binding.bind( algoCombo, SimilarityAlgorithm.class, new ObservableDetailRef<SimilarityAlgorithm>( currentAction, "algo" ));
        Binding.bind( isText, new ObservableDetailRef<Boolean>( currentAction, "isText" ));
        Binding.bind( isXML, new ObservableDetailRef<Boolean>( currentAction, "isXML" ));
        Binding.bind( isJSON, new ObservableDetailRef<Boolean>( currentAction, "isJSON" ));
        Binding.bind( isBundle, new ObservableDetailRef<Boolean>( currentAction, "isBundle" ));



        extensions.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String ext = (String) extensions.getSelectedValue();
                Action action = filesTypes.getAction( (ext) );
                currentAction.set( action );
            }
        });

//        isText.addActionListener( new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                if ( !isText.isSelected() ) {
//                    isJSON.setSelected( false );
//                    isXML.setSelected( false );
//                } else {
//                    isBundle.setSelected( false );
//                }
//            }
//        });
//

        new RootedButtonGroup().addRoot( isText ).add( isXML ).add(isJSON);
        new RootedButtonGroup().add( isText ).add( isBundle );


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
        final JLabel is       = new JLabel(Message.get("Pref.FileType.is"));

        dgl.row().grid().add( lp ).add(descr).grid(2).add( descrText, 3 );
        dgl.row().grid().spanRow().add(is).grid(2).add( isText, 3 );
        dgl.row().grid().spanRow().empty().grid(2).empty()/*indent(1)*/.add(isXML, 2);
        dgl.row().grid().spanRow().empty().grid(2).empty()/*indent(1)*/.add(isJSON, 2);
        dgl.row().grid().spanRow().empty().grid(2).add(isBundle,3);
        //dgl.row().grid().spanRow().add(algo).grid(2).add( algoCombo,3);
        dgl.row().grid().spanRow().add(external).grid(2).add( new JButton( "drop" ),3);
        dgl.row().grid().spanRow().add(open).grid(2).add( openCombo, 3 );
    }

    public void showExtension(String extension) {
        extensions.setSelectedValue( extension, true );

    }
}
