package org.openCage.kleinod.ui;

import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.VF0;
import org.openCage.kleinod.observe.ObservableReference;
import org.openCage.kleinod.observe.Observer;
import org.openCage.kleinod.type.Null;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/3/12
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Binding {

    public static void bind( final JCheckBox checkbox, final ObservableReference<Boolean> bool ) {
        checkbox.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bool.set( checkbox.isSelected()  );
            }
        });

        bool.getListenerControl().add( new VF0() {
            @Override
            public void call() {
                checkbox.setSelected( bool.get() );
            }
        });

        if ( bool.get() != null ) { checkbox.setSelected( bool.get() );}
    }


    public static void bind( final JTextComponent txtField, final ObservableReference<String> txt ) {

        txtField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                txt.set(txtField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                txt.set(txtField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                txt.set(txtField.getText());
            }
        });


        txt.getListenerControl().add( new VF0() {
            @Override
            public void call() {
                if ( !txtField.getText().equals( txt.get())) {
                    txtField.setText( txt.get());
                }
            }
        });
        txtField.setText( txt.get());
    }

    public static <T extends Enum> void bind( final JComboBox comboBox, final Class<T> clazz, final ObservableReference<T> enm ) {

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                enm.set((T) comboBox.getSelectedItem());
            }
        });
        enm.getListenerControl().add(new Observer() {
            @Override
            public void call() {
                comboBox.setSelectedItem( enm.get());
            }
        });

        for ( T elem : clazz.getEnumConstants() ) {
            ((DefaultComboBoxModel)comboBox.getModel()).addElement( elem );
        }


        if ( enm.get()!= null ) {
            comboBox.setSelectedItem( enm.get());
        }
    }

    public static <T> void bind( final JComboBox comboBox, final List<T> possibilities, final ObservableReference<T> prop ) {

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                prop.set((T) comboBox.getSelectedItem());
            }
        });
        prop.getListenerControl().add(new Observer() {
            @Override
            public void call() {
                comboBox.setSelectedItem(prop.get());
            }
        });

        for ( T elem : possibilities ) {
            ((DefaultComboBoxModel)comboBox.getModel()).addElement( elem );
        }


        if ( prop.get()!= null ) {
            comboBox.setSelectedItem( prop.get());
        }
    }

    public static <T> void bind( final JComboBox comboBox, final List<T> possibilities, final F1<String,T> toString, final ObservableReference<T> prop ) {

        final Map<String, T> reverse = new HashMap<String, T>();

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                prop.set(reverse.get(comboBox.getSelectedItem()));
            }
        });
        prop.getListenerControl().add(new Observer() {
            @Override
            public void call() {
                comboBox.setSelectedItem( toString.call( prop.get()));
            }
        });

        for ( T elem : possibilities ) {
            String trans = toString.call( elem );
            ((DefaultComboBoxModel)comboBox.getModel()).addElement( trans );
            reverse.put( trans, elem );
        }


        if ( prop.get()!= null ) {
            comboBox.setSelectedItem( toString.call( prop.get()));
        }

    }

    public static ComboBoxBinding bind( JComboBox combobox ) {
        return new ComboBoxBinding( combobox );
    }

    public static class ComboBoxBinding<T> {
        private final JComboBox comboBox;
        private List<T>         possibilities;
        private F1<String, T>   trans = new F1<String, T>() {
            @Override
            public String call(T t) {
                return t.toString();
            }
        };

        public ComboBoxBinding(JComboBox combobox) {
            this.comboBox = combobox;
        }

        public ComboBoxBinding<T> outOf( Collection<T> possibilities ) {
            this.possibilities = new ArrayList<T>();
            this.possibilities.addAll( possibilities );
            return this;
        }

        public <Y extends Enum> ComboBoxBinding<Y> outOf( Class<Y> enumClazz ) {
            this.possibilities = (List)Arrays.asList(enumClazz.getEnumConstants());
            return (ComboBoxBinding<Y>) this;
        }

        public ComboBoxBinding<T> trans( F1<String,T> trans ) {
            this.trans = trans;
            return this;
        }

        public void to( final ObservableReference<T> prop ) {

            if (Null.is(possibilities)) {
                throw new IllegalArgumentException( "possibilities missing" );
            }

            final Map<String, T> reverse = new HashMap<String, T>();
            for ( T elem : possibilities ) {
                String rev = trans.call( elem );
                reverse.put( rev, elem );
            }

            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    Object sel = comboBox.getSelectedItem();
                    if ( sel != null ) {
                        prop.set(reverse.get(sel));
                    }
                }
            });
            prop.getListenerControl().add(new Observer() {
                @Override
                public void call() {
                    comboBox.setSelectedItem( trans.call( prop.get()));
                }
            });

            for ( T elem : possibilities ) {
                String rev = trans.call( elem );
                ((DefaultComboBoxModel)comboBox.getModel()).addElement(rev);
            }


            if ( prop.get()!= null ) {
                comboBox.setSelectedItem( trans.call(prop.get()));
            }

        }

    }
}
