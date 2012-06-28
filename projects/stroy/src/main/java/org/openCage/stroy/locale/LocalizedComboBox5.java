package org.openCage.stroy.locale;

import org.openCage.lang.functions.F1;
import org.openCage.util.prefs.ListSelectionProperty5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

public class    LocalizedComboBox5<T> extends JComboBox  {

    private Map<String, T> reverse = new HashMap<String, T>();

    public LocalizedComboBox5( final F1<String,T> toPropertyKey, final ListSelectionProperty5<T> listSelection ) {

        setModel( new DefaultComboBoxModel());

        for ( T elem : listSelection.getList() ) {
            String loc = Message.get( toPropertyKey.call(elem) );
            reverse.put( loc, elem );
            ((DefaultComboBoxModel)getModel()).addElement( loc );
        }

        setSelectedItem( Message.get( toPropertyKey.call(listSelection.getSelection())));

        addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent actionEvent) {
                listSelection.setSelection(reverse.get( getSelectedItem() ));
            }
        });


//        final JComboBox me = this;
//
//        item.addListener( new PreferencesChangeListener<ListSelection<String>>() {
//            public void changed( ListSelection<String> lsel ) {
//                if ( lsel.getSelection().equals( reverse.get(me.getSelectedItem() ))) {
//                    return;
//                }
//
//                me.setSelectedItem( Message.get(lsel.getSelection() ));
//            }
//        });
    }

}
