package org.openCage.util.prefs;

import org.openCage.comphy.property.ImmuProp;
import org.openCage.comphy.property.MapProperty;
import org.openCage.lang.listeners.Observer;
import org.openCage.lang.inc.Str;
import org.openCage.stroy.file.Action;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class MComboBox extends JComboBox{

    public MComboBox(final MapProperty<ImmuProp<Str>> progSelection, final ImmuProp<Str> selectionProp) {
        setModel( new DefaultComboBoxModel());

        for ( Str elem : progSelection.keySet() ) {
            ((DefaultComboBoxModel)getModel()).addElement( elem );
        }

        if ( !selectionProp.get().isEmpty() ) {
            setSelectedItem(selectionProp.get());
        }

        addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                selectionProp.set((Str) getSelectedItem());
            }
        });

        selectionProp.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                setSelectedItem(selectionProp.get());
            }
        });

        progSelection.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                throw new Error("impl me");
            }
        });


    }

    public MComboBox( final MapProperty<ImmuProp<Str>> progSelection, final Action action ) {
        setModel( new DefaultComboBoxModel());

        for ( Str elem : progSelection.keySet() ) {
            ((DefaultComboBoxModel)getModel()).addElement( elem );
        }



        setSelectedItem( S(action.getOpen()) );

        addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                action.setOpen( ((Str)getSelectedItem()).get());
            }
        });


//        selection.getListenerControl().add( new Observer() {
//            @Override
//            public void call() {
//                setSelectedItem( selection );
//            }
//        });

        progSelection.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                throw new Error("impl me");
            }
        });

    }

}
