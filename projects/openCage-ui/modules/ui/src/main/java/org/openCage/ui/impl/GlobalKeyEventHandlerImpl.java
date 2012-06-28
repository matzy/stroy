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
