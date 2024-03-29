package org.openCage.ui.warning;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import com.explodingpixels.macwidgets.HudWindow;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.jetbrains.annotations.NotNull;
import org.openCage.ui.CombinedBabel;
import org.openCage.ui.Constants;
import org.openCage.ui.protocol.GlobalKeyEventHandler;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple modal error dialog in a HUD
 */
public class HUDWarning {

    private final CombinedBabel localize;
    private final GlobalKeyEventHandler keyEventHandler;

    @Inject public HUDWarning( CombinedBabel localize, GlobalKeyEventHandler keyEventHandler ) {
        this.localize = localize;
        this.keyEventHandler = keyEventHandler;
    }
    
    public void show( @NotNull String message ) {

        final HudWindow hud = new HudWindow( localize.ui.get( "Warning") );
        hud.getJDialog().setSize(500, 120 );
        hud.getJDialog().setLocationRelativeTo(null);
        hud.getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = HudWidgetFactory.createHudLabel( message );
        JButton button = HudWidgetFactory.createHudButton( localize.ui.get("Close"));
        Icon icon = new ImageIcon( getClass().getResource( "warning.png" ));
        JLabel pic = new JLabel("");
        pic.setIcon( icon );
        final JDialog jdialog = hud.getJDialog();

        // TODO improve layout
        // tried designgrid but did not work here
        jdialog.setLayout( new GridBagLayout());

        GridBagConstraints cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 0;
        cnstraint.weightx = 0.33;
        cnstraint.weighty = 0.5;
        cnstraint.insets = new Insets(5,5,0,0);

        jdialog.add( pic, cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 1;
        cnstraint.gridy = 0;
        cnstraint.weightx = 0.66;
        cnstraint.weighty = 0.5;
        cnstraint.insets = new Insets(5,5,0,0);

        jdialog.add( label, cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridwidth = 2;
        cnstraint.gridy = 1;
        cnstraint.weightx = 1;
        cnstraint.weighty = 0.3;
        cnstraint.insets = new Insets(5,5,0,0);

        jdialog.add( button, cnstraint );


        // ways to close the window
        hud.getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jdialog.dispose();
            }
        });
        keyEventHandler.addCloseWindow( jdialog );

        jdialog.setModal( true );


        hud.getJDialog().setVisible(true);

    }

}
