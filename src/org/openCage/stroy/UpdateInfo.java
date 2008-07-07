package org.openCage.stroy;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;

import org.openCage.util.app.AppInfo;
import org.openCage.util.app.Version;
import com.google.inject.Inject;

public class UpdateInfo extends JFrame {

    private final AppInfo appInfo;
    private JLabel current = new JLabel( "" );

    @Inject
    public UpdateInfo( final AppInfo appInfo ) {

        this.appInfo = appInfo;

        JPanel top = new JPanel();

//        setSize( 400, 400 );

        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        // TODO localize
        layout.row().add( new JLabel("There is a new version of stroy available" ));
        layout.row().add( new JLabel("Your current version is" ), 3).add( new JLabel( appInfo.getVersion().toString()) );
        layout.row().add( new JLabel("Available version is" ), 3).add( current );
        layout.row().add( new JLabel("" ), 3);
        layout.row().add( new JLabel("Never show this again" ), 3);

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );
        pack();
    }

    public UpdateInfo setCurrent( Version latest ) {
        current.setText( latest.toString() );

        return this;
    }

}
