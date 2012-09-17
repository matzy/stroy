package org.openCage.kleinod.ui;

import org.openCage.kleinod.type.Null;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/4/12
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Regulatates States of Buttons in a group with one extra element the root
 * without the root it is just a button group where no button has to be pressed
 * the root button enables the other ones
 * i.e. deselecting a non root deselects the the root
 */
public class RootedButtonGroup {

    private AbstractButton root;
    private List<AbstractButton> boxes = new ArrayList<AbstractButton>();

    public RootedButtonGroup addRoot( final AbstractButton root ) {
        this.root = root;
//        root.addActionListener( new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//            }
//        });

//        root.addChangeListener( new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent changeEvent) {
//                System.out.println(changeEvent);
//            }
//        });

        root.addItemListener( new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if ( !root.isSelected()) {
                    for ( AbstractButton child : boxes ) {
                        if ( child.isSelected() ) {
                            child.setSelected(false );
                        }
                    }
                }
            }
        });
        
        return this;
    }

    public RootedButtonGroup add( final AbstractButton checkBox ) {
        boxes.add( checkBox );

        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (checkBox.isSelected()) {
                    if (Null.isNot(root) && !root.isSelected()) {
                        root.setSelected(true);
                    }

                    for (AbstractButton other : boxes) {
                        if (other != checkBox && checkBox.isSelected()) {
                            other.setSelected(false);
                        }
                    }
                }
            }
        });

        return this;
    }


}
