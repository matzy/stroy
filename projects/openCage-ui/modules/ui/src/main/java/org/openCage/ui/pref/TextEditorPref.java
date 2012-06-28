package org.openCage.ui.pref;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.value.ValueModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.openCage.lang.functions.F1;
import org.openCage.lang.structure.BiMap;
import org.openCage.property.Property;
import org.openCage.ui.CombinedBabel;
import org.openCage.ui.pref.CaretStyleProperty;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.*;
import java.util.ArrayList;

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

/**
 * The preference panel for text editor preferences
 */
public class TextEditorPref implements PrefBuilder {

    private CombinedBabel localize;
    private Property<Integer> cursorStyle;

    @Inject
    public TextEditorPref( CombinedBabel localize,
                           @Named( CaretStyleProperty.KEY ) Property<Integer> cursorStyle ) {
        this.localize = localize;
        this.cursorStyle = cursorStyle;
    }


    @Override
    public JPanel getPrefPanel() {
        return new TextEditorPrefPane( cursorStyle );
    }

    @Override
    public JButton getPrefButton() {
        JButton button = new JButton( "Text" );
        button.setIcon( new ImageIcon( getClass().getResource( "texteditor.png" )));

        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition( AbstractButton.CENTER);

        return button;
    }

    public class TextEditorPrefPane extends JPanel {

        private BiMap<Integer, String> caretStyleList = new BiMap<Integer,String>() {{
                put( ConfigurableCaret.VERTICAL_LINE_STYLE , localize.localize("org.openCage.ui.line") );
                put( ConfigurableCaret.THICK_VERTICAL_LINE_STYLE , localize.localize("org.openCage.ui.thickLine"));
                put( ConfigurableCaret.BLOCK_STYLE , localize.localize("org.openCage.ui.block")); }};

        private Property<Integer> cursorStyle;

        public TextEditorPrefPane( Property<Integer> cursorStyle ) {

            this.cursorStyle = cursorStyle;

            ValueModel caretStyleModel = new PropertyAdapter( this, "style", false);
            ComboBoxAdapter adapter    = new ComboBoxAdapter( new ArrayList<String>(caretStyleList.vals()), caretStyleModel);
            JComboBox caretBox       = new JComboBox(adapter);



            DesignGridLayout layout = new DesignGridLayout( this );
//            setLayout( layout );

  //          layout.row().label( new JLabel( localize.localize("org.openCage.ui.caretType") )).add( caretBox );
        }


        public void setStyle( final String style ) {
            cursorStyle.modify( new F1<Integer, Integer>() {
                @Override
                public Integer call(Integer integer) {
                    return caretStyleList.getReverse( style );
                }
            });
        }

        public String getStyle() {
            return caretStyleList.get( cursorStyle.get() );
        }



    }
}
