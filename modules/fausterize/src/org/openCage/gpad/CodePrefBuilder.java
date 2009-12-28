package org.openCage.gpad;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 24, 2009
 * Time: 8:32:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class CodePrefBuilder implements PrefBuilder {

    private final Localize localize;

    @Inject
    public CodePrefBuilder( @Named( Constants.FAUSTERIZE) Localize localize) {
        this.localize = localize;
    }

    private class CodePref extends JPanel {

        public CodePref() {
            setLayout( new BorderLayout());
            add( new JLabel( "TODO" ), BorderLayout.CENTER  );

//            List<String> countryList = localized.getLocalizedLocales();
//            ValueModel countryModel = new PropertyAdapter(localized, "locale", false);
//            ComboBoxAdapter adapter = new ComboBoxAdapter(countryList, countryModel);
//            JComboBox countryBox    = new JComboBox(adapter);
//
//            DesignGridLayout layout = new DesignGridLayout( this );
//            setLayout( layout );
//
//            layout.row().label( new JLabel( localize.localize("org.openCage.localization.dict.language" )) ).add( countryBox );
//            layout.row().add( new JLabel(""));
//            layout.row().add( new JLabel( localize.localize( "org.openCage.ui.languageWarning" )));

        }
    }


    @Override
    public JPanel getPrefPanel() {
        return new CodePref();
    }

    @Override
    public JButton getPrefButton() {
        JButton button = new JButton( localize.localize( "org.openCage.fausterize.code" ));
        button.setIcon( new ImageIcon( getClass().getResource( "code.png" )));

        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition( AbstractButton.CENTER);

        return button;
    }
}
