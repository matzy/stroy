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
import org.openCage.localization.protocol.Localize;
import org.openCage.property.protocol.Property;
import org.openCage.ui.pref.CaretStyleProperty;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.*;
import java.util.ArrayList;

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

/**
 * The preference panel for text editor preferences
 */
public class TextEditorPref implements PrefBuilder {

    @Inject @Named( "ui" )  private Localize localize;
    @Inject @Named( CaretStyleProperty.KEY ) private Property<Integer> cursorStyle;

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
            setLayout( layout );

            layout.row().label( new JLabel( localize.localize("org.openCage.ui.caretType") )).add( caretBox );
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
