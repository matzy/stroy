package org.openCage.util.prefs;

import org.openCage.lang.listeners.Observer;
import org.openCage.lang.structure.ObservableRef;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/19/12
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class EComboBox<T extends Enum > extends JComboBox {

    public EComboBox( final Class<T> clazz, final ObservableRef<String> prop ) {

        setModel( new DefaultComboBoxModel());


        for ( T elem : clazz.getEnumConstants() ) {
            ((DefaultComboBoxModel)getModel()).addElement( elem );
        }

        if ( !prop.get().isEmpty() ) {
            setSelectedItem( T.valueOf( clazz, prop.get()));
        }

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                prop.set((getSelectedItem().toString()));
            }
        });

        prop.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                setSelectedItem( T.valueOf(clazz, prop.get()));
            }
        });


    }
}
