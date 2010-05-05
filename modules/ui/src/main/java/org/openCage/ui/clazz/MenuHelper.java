package org.openCage.ui.clazz;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.muchsoft.util.Sys;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.protocol.F0;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.AboutSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuHelper {

    public static Color BAR_BACKGROUND = new Color(30, 30, 30);

    @Inject @Named( "ui" ) private Localize loca;
    @Inject private Artifact app;
    @Inject private AboutSheet aboutSheet;

    public JMenuBar getMenuBar() {
        JMenuBar mbar = new JMenuBar();
        setBackground( mbar );

        return mbar;
    }

    public JMenu getFileMenu() {
        JMenu menuFile  = new JMenu( loca.localize( "org.openCage.ui.menu.file" ) );
        setBackground( menuFile );

        return menuFile;
    }

    public JMenu getHelpMenu() {
        return setBackground( new JMenu( loca.localize( "org.openCage.localization.dict.help" ) ));
    }

    public JMenu getEditMenu() {
        return setBackground( new JMenu( loca.localize( "org.openCage.localization.dict.edit" ) ));
    }

    public void addCopy( JMenu menu, final F0<Void> action  ) {
        JMenuItem item  = /* setBackground( */ new JMenuItem( loca.localize( "org.openCage.localization.dict.copy" ) ); //);
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        item.setAccelerator( stroke );
        menu.add( item );

        item.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                action.call();
            }
        });
    }

    public void addCut( JMenu menu, final F0<Void> action  ) {
        JMenuItem item  = /*setBackground( */new JMenuItem( loca.localize( "org.openCage.localization.dict.cut" ) );
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        item.setAccelerator( stroke );
        menu.add( item );

        item.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                action.call();
            }
        });
    }
    public void addPaste( JMenu menu, final F0<Void> action  ) {
        JMenuItem item  = /* setBackground( */ new JMenuItem( loca.localize( "org.openCage.localization.dict.paste" ));
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        item.setAccelerator( stroke );
        menu.add( item );

        item.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                action.call();
            }
        });
    }

    public void addExit( JMenu menu ) {
        JMenuItem ex = new JMenuItem( loca.localize("org.openCage.localization.dict.exit"));
        //setBackground( ex );
        menu.add( ex );
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        ex.setAccelerator( stroke );


        ex.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

    }

    public void addPrefs( JMenu menu, final F0<Void> prefs ) {

        if ( !Sys.isMacOSX() ) {
            JMenuItem menuItemPrefs = setBackground( new JMenuItem( loca.localize( "org.openCage.localization.dict.preference" )));
            menu.add( menuItemPrefs );

            menuItemPrefs.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    prefs.call();
                }
            });
        }
    }

    public void addAbout( JMenu menu ) {
        if ( !Sys.isMacOSX() ) {
            JMenuItem about = setBackground( new JMenuItem( loca.localize("org.openCage.ui.about_prog", app.gettName() )));
            menu.add( about );
            about.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    aboutSheet.setVisible( true );
                }
            });
        }
    }


    public <T extends JComponent> T setBackground( T compo ) {
        compo.setBackground( BAR_BACKGROUND );

        return compo;
    }
}
