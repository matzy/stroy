package org.openCage.ui.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 15, 2009
 * Time: 3:13:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalKeyListener implements KeyEventPostProcessor {
    private Component hideable = null;

    public boolean postProcessKeyEvent(KeyEvent e) {
        KeyStroke ks = KeyStroke.getKeyStroke( KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        System.out.println( "woooooooo   " + e );
        if ( e.getID() == KeyEvent.KEY_TYPED ) {

            KeyStroke ke = KeyStroke.getKeyStroke( e.getKeyChar(), e.getModifiersEx() );

            System.out.println( e.getKeyChar() + "::" + e.getModifiersEx() + ":::" + e.getModifiers() + "::::" + e.getKeyCode());

            if ( e.getKeyChar() == 'w' && e.getModifiersEx() == 256 ) {
                System.out.println("huuu");
                if ( hideable != null ) {
                    hideable.setVisible( false );
                }
            } else {

            }

            System.out.println( ke + " ::: " + ks);

//            System.out.println( e.getKeyCode() );
//            new KeyStroke( e );
//            System.out.println( ks.getKeyCode() + " " + ks.getKeyChar() + " " + ks.getModifiers() );
//            int i = 0;
        }

//        System.out.println( Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());

//        if (e.getID() == KeyEvent.KEY_PRESSED) {
//            Object s = e.getComponent();
//            if (!(s instanceof JTextField) &&
//                    !(s instanceof JTable && ((JTable) s).isEditing())
//                    ) {
//                music_player.pauseEvent();
//            }
//
//            //System.out.println(s.getClass().toString());
//        }
//        return true;
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addCloseable( Component compo ) {
        hideable = compo;
    }



}
