package org.openCage.util.prefs;

import javax.swing.*;
import java.util.List;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class PComboBox extends JComboBox {

    private final PreferenceItem<ListSelection<String>> item;

    public PComboBox( final String key ) {

        setModel( new DefaultComboBoxModel());

        item = PListSelectionString.create( key );

        for ( String elem : item.get().list ) {
            ((DefaultComboBoxModel)getModel()).addElement( elem );
        }



        setSelectedItem( item.get().getSelection() );

        addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                item.get().setSelection( (String)getSelectedItem() );
                item.setDirty();
            }
        });


        final JComboBox me = this;

        item.addListener( new PreferencesChangeListener<ListSelection<String>>() {
            public void changed(ListSelection<String> lsel ) {
                if ( lsel.getSelection().equals( (String)me.getSelectedItem() )) {
                    return;
                }

                me.setSelectedItem( lsel.getSelection() );
            }
        });

    }

    public PComboBox( final String key, ListSelection<String> ini ) {

        setModel( new DefaultComboBoxModel());

        item = PListSelectionString.create( key, ini );

        for ( String elem : item.get().list ) {
            ((DefaultComboBoxModel)getModel()).addElement( elem );
        }

        setSelectedItem( item.get().getSelection() );

        addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                item.get().setSelection( (String)getSelectedItem() );
                item.setDirty();
            }
        });
    }

//    public PreferenceItem<ListSelection<String>> getItem() {
//        return item;
//    }
    


}
