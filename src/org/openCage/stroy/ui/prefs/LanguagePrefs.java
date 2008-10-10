package org.openCage.stroy.ui.prefs;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.openCage.stroy.locale.Message;
import org.openCage.stroy.locale.LocalizedComboBox;

import java.awt.*;

public class LanguagePrefs extends JPanel {

    private LocalizedComboBox languageBox;

    public LanguagePrefs() {

        languageBox = new LocalizedComboBox( Message.localKey );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().add( new JLabel(""));
        layout.row().empty().add( Message.getl( "Pref.language.which" )).add( languageBox ).empty();
        layout.centerRow().add( Message.getl( "Pref.language.warning" ));

        top.setBorder( new TitledBorder( Message.get( "Pref.language.title" ) ));

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );


    }

}
