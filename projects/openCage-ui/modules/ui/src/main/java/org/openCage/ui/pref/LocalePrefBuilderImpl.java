package org.openCage.ui.pref;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.value.ValueModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.lang.errors.Unchecked;
import org.openCage.ui.CombinedBabel;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.net.URL;
import java.util.List;

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
 * The preference panel for language preferences
 */
public class LocalePrefBuilderImpl implements PrefBuilder {

    private final CombinedBabel localize;
    private TheLocaleLocalized localized;

    private class LocalePref extends JPanel {

        public LocalePref() {
            List<String> countryList = localized.getLocalizedLocales();
            ValueModel countryModel = new PropertyAdapter(localized, "locale", false);
            ComboBoxAdapter adapter = new ComboBoxAdapter(countryList, countryModel);
            JComboBox countryBox    = new JComboBox(adapter);

            DesignGridLayout layout = new DesignGridLayout( this );
//            setLayout( layout );

//            layout.row().label( new JLabel( localize.localize("org.openCage.localization.dict.language" )) ).add( countryBox );
//            layout.row().add( new JLabel(""));
//            layout.row().add( new JLabel( localize.localize( "org.openCage.ui.languageWarning" )));

        }
    }

    @Inject
    public LocalePrefBuilderImpl( CombinedBabel localize, TheLocaleLocalized localized) {

        this.localize = localize;
        this.localized = localized;
    }

    @Override
    public JPanel getPrefPanel() {
        return new LocalePref();
    }

    @Override
    public JButton getPrefButton() {
        JButton button = new JButton( localize.localize( "org.openCage.localization.dict.language" ));

        URL base = getClass().getResource( "." );
        URL base2 = getClass().getResource( "LocalePrefBuilderImpl.class" );
        URL imageResource = getClass().getResource( "../../pref/locale.png" );

        try {
            button.setIcon( new ImageIcon( getClass().getResource( "locale.png" )));
        } catch ( Error e ) {
            throw new Unchecked( new IllegalArgumentException( "can not find " + base  + "  " + base2) );
        } catch ( Exception e ) {
            throw new Unchecked( new IllegalArgumentException( "can not find " + base  + "  " + base2) );
        }

        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition( AbstractButton.CENTER);

        return button;
    }

}
