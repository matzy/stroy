package org.openCage.gpad;

import com.explodingpixels.macwidgets.*;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.muchsoft.util.mac.Java14Adapter;
import com.muchsoft.util.mac.Java14Handler;
import com.sun.xml.internal.xsom.util.NameGetter;
import org.openCage.application.protocol.Application;
import org.openCage.lang.protocol.FE0;
import org.openCage.lang.protocol.FE1;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.FileChooser;
import org.openCage.ui.protocol.OSXStandardEventHandler;
import org.openCage.withResource.protocol.BackgroundSaver;
import org.openCage.withResource.protocol.FileLineIterable;
import org.openCage.withResource.protocol.With;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EventObject;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 18, 2009
 * Time: 5:08:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FaustUI extends JFrame {

    final private Application application;
    final private With with;
    final private FileChooser fileChooser;
    final private AboutSheet about;
    final private OSXStandardEventHandler osxEventHandler;
    private URI pad;
    private String message = "/Users/stephan/woo.txt";
    private BackgroundSaver saver;

    private JTextArea textUI = new JTextArea();
    TextEncoderIdx<String> tts;
    final private Localize localize;

    @Inject
    public FaustUI( Application application,
                    With wth,
                    FileChooser chooser,
                    AboutSheet about,
                    OSXStandardEventHandler osxEventHandler,
                    BackgroundSaver saver,
                    @Named( "fausterize") Localize localize ) {
        this.application = application;
        this.with = wth;
        this.fileChooser = chooser;
        this.about = about;
        this.osxEventHandler = osxEventHandler;
        this.saver = saver;
        this.localize = localize;

//        JButton save = new JButton("save");
//        save.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                new WithImpl().withWriter( new File(message), new FE1<Object, Writer>() {
//                    public Object call(Writer writer) throws Exception {
//                        System.out.println(textUI.getText());
//                        String code = tts.encode( textUI.getText(),0);
//                        System.out.println( code );
//                        writer.append( code );
//                        return null;
//                    }
//                });
//            }
//        });

        JButton padButton = new JButton( localize.localize( "org.openCage.fausterize.decode"));
        final JFrame theFrame = this;
        padButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = fileChooser.open( theFrame, "/");
                if ( path != null ) {
                    try {
                        pad = new URI("file://" + path);
                        setPad( "file://" + path, message );
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        });

        JScrollPane scroll =  new JScrollPane(textUI);
        scroll.setSize( 800, 600 );
        scroll.setMinimumSize( new Dimension(400,400));
        textUI.setMinimumSize( new Dimension( 400, 400 ));
        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( scroll, BorderLayout.CENTER  );

        // For some versions of Mac OS X, Java will handle painting the Unified Tool Bar.
        // Calling this method ensures that this painting is turned on if necessary.
        MacUtils.makeWindowLeopardStyle( getRootPane());

        UnifiedToolBar toolBar = new UnifiedToolBar();
//        save.putClientProperty("JButton.buttonType", "textured");
//        toolBar.addComponentToLeft(save);
        toolBar.addComponentToLeft(padButton);
        final JTextField textField = new JTextField(10);
        textField.putClientProperty("JTextField.variant", "search");
        toolBar.addComponentToRight(new LabeledComponentGroup("Search", textField).getComponent());
        

        getContentPane().add( toolBar.getComponent(), BorderLayout.NORTH );

        BottomBar bottomBar = new BottomBar(BottomBarSize.LARGE);
//        bottomBar.addComponentToCenter(MacWidgetFactory.createEmphasizedLabel(message));
        getContentPane().add( bottomBar.getComponent(), BorderLayout.SOUTH );

        setTitle( application.gettName());
        setSize( 800, 600 );


        textField.addKeyListener( new KeyAdapter(){
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );
                String searchStr = textField.getText();
                String text = textUI.getText();
                int pos = text.indexOf( searchStr );
                System.out.println("" + searchStr + " " + pos );
                if ( pos > -1 ) {
                    textUI.setCaretPosition( pos );
                    Highlighter h = textUI.getHighlighter();
                    h.removeAllHighlights();
                    try {
                        h.addHighlight( pos, pos + searchStr.length(), DefaultHighlighter.DefaultPainter);
                    } catch (BadLocationException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        });

        pack();

        saver.addTask( new FE0<Void>() {
            @Override
            public Void call() throws Exception {

                with.withWriter( new File(message), new FE1<Void, Writer>() {
                    @Override
                    public Void call(Writer writer) throws Exception {
                        if ( textUI.getText().length() > 0 ) {
                            writer.write( tts.encode( textUI.getText(), 0 ));
                        }
                        return null;
                    }
                });
                return null;
            }
        });

    }

    private void setPad(String pad, String message) {
        try {
            tts = new FaustString();
            tts.setPad(  new URI( pad ) );

            File filem = new File(message);
            String text = "";

            if ( filem.exists() ) {
                FileLineIterable it =  with.getLineIteratorCloseInFinal( filem );
                try {
                    for ( String str : it ) {
                        text += str + "\n";
                    }
                } finally {
                    it.close();
                }
                textUI.append( tts.decode( text, 0 ));
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
