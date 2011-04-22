package org.openCage.ui.impl;

import com.google.inject.Singleton;
import com.muchsoft.util.Sys;
import org.openCage.ui.protocol.GlobalKeyEventHandler;
import org.openCage.ui.protocol.GlobalKeyEventListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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

@Singleton
public class GlobalKeyEventHandlerImpl implements GlobalKeyEventHandler, KeyEventPostProcessor, KeyEventDispatcher {

    private final List<GlobalKeyEventListener> listeners = new ArrayList<GlobalKeyEventListener>();
    
    public GlobalKeyEventHandlerImpl() {
        // this call requires singleton scope in guice
        //KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor( this );
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( this );
        }


    public boolean postProcessKeyEvent(KeyEvent e) {

        List<GlobalKeyEventListener> current = new ArrayList<GlobalKeyEventListener>();

        // select all relevant
        for ( GlobalKeyEventListener listener : listeners ) {
            if ( listener.keyMatches( e )) {
                current.add( listener );
            }
        }

        if ( current.isEmpty() ) {
            return false;
        }

        // walk source parent chain

        Object src = e.getSource();
        while( src != null ) {
            for ( GlobalKeyEventListener listener : current ) {
                if ( src == listener.getComponent() ) {
                    if ( listener.getComponent().isVisible() ) {
                        listener.action();
                        return true;
                    }
                }
            }

            if ( src instanceof Component ) {
                src = ((Component)src).getParent();
            } else {
                return false;
            }
        }


        return false;
    }

    public void addListener(GlobalKeyEventListener listener) {
        listeners.add( listener );
    }

    public void addCloseWindow( final Component compo  ) {
        listeners.add( new GlobalKeyEventListener() {

            public Component getComponent() {
                return compo;
            }

            public boolean keyMatches(KeyEvent event) {
                if ( Sys.isMacOSX() ) {
                    return (event.getKeyChar() == 'w') && event.getModifiersEx() == 256;
                }

                // Linux windows todo
                return false;
            }

            public void action() {
                compo.setVisible( false );
            }
        });
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return postProcessKeyEvent( keyEvent );
    }
}
