package org.openCage.ui.clazz;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.muchsoft.util.Sys;
import org.openCage.lang.protocol.F0;
import org.openCage.localization.impl.LocalizeImpl;
import org.openCage.localization.protocol.Localize;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextEditorBuilder {
    private JTextArea area;
    private JTextField find;
    private int        positionBeforeFind = 0;
    @Inject @Named( "ui" )  private Localize loca;

    public void setTextArea( JTextArea tarea ) {
        this.area = tarea;
    }

    public void setFindField( final JTextField find ) {
        this.find = find;

        find.addFocusListener( new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                positionBeforeFind = area.getCaretPosition();
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {}
        });

        find.addKeyListener( new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );

                String searchStr = find.getText();
                String text      = area.getText();

                int pos = findNext( text, searchStr, positionBeforeFind );

                Highlighter h = area.getHighlighter();
                h.removeAllHighlights();

                if ( keyEvent.getKeyChar() == '\n' ) {
                    // goto text again

                    if ( pos >= 0 ) {
                        area.setCaretPosition( pos );
                    } else {
                        area.setCaretPosition( positionBeforeFind );
                    }
                    area.grabFocus();
                    return;
                }

                if ( pos > -1 ) {
                    area.setCaretPosition( pos );
                    try {
                        h.addHighlight( pos, pos + searchStr.length(), DefaultHighlighter.DefaultPainter);
                    } catch (BadLocationException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        });

        addFindKey();
        addFindNextKey();


    }

    private void addFindKey() {
        Action action = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                find.grabFocus();
            }
        };

        String keyStrokeAndKey = "control F";
        if ( Sys.isMacOSX() ) {
            keyStrokeAndKey = "command F";
        }

        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        //KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        area.getInputMap( JComponent.WHEN_FOCUSED).put( stroke, keyStrokeAndKey);
        area.getActionMap().put( keyStrokeAndKey, action);
    }

    private void addFindNextKey() {
        Action actionG = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int pos = findNext( area.getText(), find.getText(), area.getCaretPosition() );

                if ( pos >= 0 ) {
                    area.setCaretPosition( pos );
                    return;
                }

                // TODO message not found

            }
        };

        String keyStrokeAndKeyG = "control G";
        if ( Sys.isMacOSX() ) {
            keyStrokeAndKeyG= "command G";
        }

        KeyStroke strokeG = KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        //KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        area.getInputMap( JComponent.WHEN_FOCUSED).put( strokeG, keyStrokeAndKeyG);
        area.getActionMap().put(keyStrokeAndKeyG, actionG);


    }

    private int findNext( String text, String searchText, int current ) {
        int pos = text.indexOf( searchText, current + 1);

        if ( pos > 0 ) {
            return pos;
        }

        pos = text.indexOf( searchText, 0 );

        if ( pos >= 0 && pos < current ) {
            return pos;
        }

        return -1;

    }

    public MenuBuilder.ItemIM itemFind( MenuBuilder mb ) {
        return mb.item( loca.localize("org.openCage.localization.dict.find" ) ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) ).
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        find.grabFocus();
                        return null;
                    }
                });
    }

    public MenuBuilder.ItemIM itemFindNext( MenuBuilder mb ) {
        return mb.item( loca.localize("org.openCage.localization.dict.findNext" ) ).
                accelerator( KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) ).
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        int pos = findNext( area.getText(), find.getText(), area.getCaretPosition() );

                        if ( pos >= 0 ) {
                            area.setCaretPosition( pos );
                            return null;
                        }

                        // TODO message not found
                        return null;
                    }
                });
    }

    public MenuBuilder.ItemIM itemCopy( MenuBuilder mb) {
        return mb.itemCopy().
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        area.copy();
                        return null;
                    }
                });
    }

    public MenuBuilder.ItemIM itemCut( MenuBuilder mb) {
        return mb.itemCut().
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        area.cut();
                        return null;
                    }
                });
    }

    public MenuBuilder.ItemIM itemPaste( MenuBuilder mb) {
        return mb.itemPaste().
                action( new F0<Void>() {
                    @Override
                    public Void call() {
                        area.paste();
                        return null;
                    }
                });
    }


}
