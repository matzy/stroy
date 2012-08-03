package org.openCage.util.prefs;

import org.openCage.comphy.property.ImmuProp;
import org.openCage.lang.listeners.Observer;
import org.openCage.lang.inc.Str;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.openCage.lang.inc.Strng.S;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/19/12
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class EComboBox<T extends Enum > extends JComboBox {

    public EComboBox( final Class<T> clazz, final ImmuProp<Str> prop ) {

        setModel( new DefaultComboBoxModel());


        for ( T elem : clazz.getEnumConstants() ) {
            ((DefaultComboBoxModel)getModel()).addElement( elem );
        }

        if ( !prop.get().isEmpty() ) {
            setSelectedItem( T.valueOf( clazz, prop.get().get()));
        }

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                prop.set(S(getSelectedItem().toString()));
            }
        });

        prop.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                setSelectedItem( T.valueOf(clazz, prop.get().get()));
            }
        });


    }
}
