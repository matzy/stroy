package org.openCage.ui.pref;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.value.ValueModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.lang.errors.Unchecked;
import org.openCage.localization.impl.TheLocaleLocalized;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
 * The preference panel for language preferences
 */
public class LocalePrefBuilderImpl implements PrefBuilder {

    private final Localize localize;
    private TheLocaleLocalized localized;

    private class LocalePref extends JPanel {

        public LocalePref() {
            List<String> countryList = localized.getLocalizedLocales();
            ValueModel countryModel = new PropertyAdapter(localized, "locale", false);
            ComboBoxAdapter adapter = new ComboBoxAdapter(countryList, countryModel);
            JComboBox countryBox    = new JComboBox(adapter);

            DesignGridLayout layout = new DesignGridLayout( this );
            setLayout( layout );

            layout.row().label( new JLabel( localize.localize("org.openCage.localization.dict.language" )) ).add( countryBox );
            layout.row().add( new JLabel(""));
            layout.row().add( new JLabel( localize.localize( "org.openCage.ui.languageWarning" )));

        }
    }

    @Inject
    public LocalePrefBuilderImpl( @Named( "ui" ) Localize localize, TheLocaleLocalized localized) {

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
