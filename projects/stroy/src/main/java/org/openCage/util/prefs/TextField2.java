package org.openCage.util.prefs;

import com.google.inject.Inject;
import org.openCage.comphy.Observable;
import org.openCage.comphy.Observer;
import org.openCage.comphy.StringProperty;
import org.openCage.lang.listeners.VoidListenerControl;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextField2 extends JTextField implements Observable {

    private final StringProperty prop;

    @Inject
    public TextField2( final StringProperty prop ) {
        this.prop = prop;
        super.setText( prop.get() );

        addListeners();


    }

    private void addListeners() {

        final TextField2 that = this;

        addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                // first make sure the key changed the text
                super.keyReleased( keyEvent );
                prop.set(that.getText());
            }
        });
    }


    @Override
    public void setText(String txt ) {
        // prevents update loops
        if ( txt.equals( super.getText() ) ) {
            return;
        }

        super.setText(txt);
        prop.set(txt);
    }


    @Override
    public VoidListenerControl getListenerControl() {
        return prop.getListenerControl();
    }
}
