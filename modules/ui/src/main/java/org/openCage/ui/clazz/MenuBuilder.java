package org.openCage.ui.clazz;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.muchsoft.util.Sys;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.functions.F0;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.HelpViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBuilder {


    @Inject @Named( "ui" ) private Localize loca;
    @Inject private Artifact app;
    @Inject private AboutSheet aboutSheet;
    @Inject private HelpViewer helpViewer;



    private JMenuBar mbar = new JMenuBar();


    public class MenuIM {
        private final JMenu menu;

        public MenuIM( JMenu menu ) {
            this.menu = menu;
        }

        public MenuIM add( ItemIM item ) {
            if ( item.getItem() != null ) {
                menu.add( item.getItem() );
            }
            return this;
        }

        public MenuIM add( MenuIM subMenu ) {
            if ( subMenu.getMenu() != null ) {
                menu.add( subMenu.getMenu() );
            }
            return this;
        }

        public MenuIM separator() {
            menu.addSeparator();
            return this;
        }

        public JMenu getMenu() {
            return menu;
        }
    }

    public static class ItemIM {
        private JMenuItem item;

        public ItemIM(String title) {
            item = new JMenuItem( title );
        }

        public JMenuItem getItem() {
            return item;
        }

        public ItemIM action( final F0<Void> act ) {
            item.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    act.call();
                }
            });
            return this;
        }

        public ItemIM accelerator( KeyStroke stroke ) {
            item.setAccelerator( stroke );
            return this;
        }
    }


    public static class NullItem extends ItemIM {

        public NullItem() {
            super("null");
        }

        @Override
        public JMenuItem getItem() {
            return null;
        }
    }

    private class SubMenuIM {
    }

    public MenuIM add(String title ) {
        JMenu menu = new JMenu( title );
        menu.setBackground( getBarBackground() );
        mbar.add( menu );
        return new MenuIM( menu );
    }

    private MenuIM subMenu(String title) {
        JMenu menu = new JMenu( title );
        return new MenuIM( menu );
    }

    public MenuIM addFile() {
        return add(  loca.localize( "org.openCage.localization.menu.file" ));
    }

    public MenuIM addEdit() {
        return add(  loca.localize( "org.openCage.localization.dict.edit" ));
    }

    public MenuIM addSearch() {
        return add(  loca.localize( "org.openCage.localization.dict.search" ));
    }

    public MenuIM addHelp() {
        return add(  loca.localize( "org.openCage.localization.dict.help" ));
    }

    public ItemIM item(String  title ) {
        return new ItemIM( title );
    }

    public ItemIM itemExit() {
        return item( loca.localize("org.openCage.localization.dict.exit") ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())).
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        System.exit(0);
                        return null;
                    }
                });
    }

    public ItemIM itemCopy() {
        return item( loca.localize("org.openCage.localization.dict.copy") ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }

    public ItemIM itemCut() {
        return item( loca.localize("org.openCage.localization.dict.cut") ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }

    public ItemIM itemUndo() {
        return item( loca.localize("org.openCage.localization.dict.undo") ).
                        accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }

    public ItemIM itemRedo() {

        return item( loca.localize("org.openCage.localization.dict.redo") ).
            accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | ActionEvent.SHIFT_MASK)); 
    }

    public ItemIM itemPaste() {
        return item( loca.localize("org.openCage.localization.dict.paste") ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }

    public ItemIM itemPrefs() {
        if ( Sys.isMacOSX() ) {
            return new NullItem();
        }

        return item( loca.localize( "org.openCage.localization.dict.preference") ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }

    public ItemIM itemAbout() {
        if ( Sys.isMacOSX() ) {
            return new NullItem();
        }

        return item( loca.localize("org.openCage.ui.about_prog", app.gettName() ) ).
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        aboutSheet.setVisible( true );
                        return null;
                    }
                });
    }

    public ItemIM itemHelp() {
        return item( loca.localize("org.openCage.localization.dict.help" ) ).
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        helpViewer.viewHelp();
                        return null;
                    }
                });
    }

    public ItemIM itemOpen() {
        return item( loca.localize("org.openCage.localization.dict.open" ) ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }

    public ItemIM itemNew() {
        return item( loca.localize("org.openCage.localization.dict.new" ) ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); 
    }

    public ItemIM itemSaveAs() {
        return item( loca.localize("org.openCage.localization.dict.saveAs" ) ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }



    public void setOnFrame( JFrame frame ) {
        mbar.setBackground( getBarBackground() );
        frame.setJMenuBar( mbar );
    }

    public MenuIM menuOpenRecent() {
        return subMenu( loca.localize("org.openCage.localization.dict.openRecent") );
    }


    public Color getBarBackground() {
        if ( Sys.isMacOSX() ) {
            new Color( 30, 30, 30);
        }

        if ( Sys.isLinux() ) {
            return new Color(230, 230, 230);
        }

        return new Color(180, 180, 180);
    }


}
