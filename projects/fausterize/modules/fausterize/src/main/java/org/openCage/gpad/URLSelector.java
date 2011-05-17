//package org.openCage.gpad;
//
//import com.google.inject.Inject;
//import net.java.dev.designgridlayout.DesignGridLayout;
//import org.openCage.io.fspath.FSPathBuilder;
//import org.openCage.ui.MiniBrowserDialog;
//import org.openCage.ui.MiniBrowserFactory;
//import org.openCage.ui.protocol.FileChooser;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.logging.Logger;
//
//public class URLSelector {
//    private static final Logger LOG = Logger.getLogger( URLSelector.class.getName());
//
//    private final FileChooser fileChooser;
//    private final MiniBrowserFactory minibrowser;
//
//    @Inject public URLSelector( FileChooser fileChooser, MiniBrowserFactory minibrowser ) {
//        this.fileChooser = fileChooser;
//        this.minibrowser = minibrowser;
//    }
//
//    public Dialog get( JFrame parent ) {
//        return new Dialog( parent );
//    }
//
//
//    public class Dialog extends JDialog {
//
//        private JButton getFile = new JButton("File ..");
//        private JButton getWeb = new JButton("Web ..");
//        private JTextField filePath = new JTextField("http://www.");
//
//        private JButton ok = new JButton( "ok" );
//
//
//
//        private Dialog( JFrame parent ) {
//
//            super( parent, true );
//
//            setTitle( "Select a File or URL" );
//
//            JPanel top = new JPanel();
//            DesignGridLayout layout = new DesignGridLayout( top );
////            top.setLayout( layout );
//
////            layout.emptyRow( 10 );
//            layout.row().center().add( new JLabel( "Select a File or Web Document (pdf,mp3...)" ));
////            layout.emptyRow( 5 );
//            layout.row().grid().add(  filePath );
//            layout.row().grid().add( getFile).add( getWeb ).add( new JLabel(" "),3);
////            layout.emptyRow( 10 );
//            layout.row().grid().add( new JLabel(" "),5).add( ok );
//
//
//            getContentPane().setLayout( new BorderLayout());
//            getContentPane().add( top, BorderLayout.CENTER  );
//
//            pack();
//
//            final JDialog that = this;
//
//            getFile.addActionListener( new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String path = fileChooser.open( that, FSPathBuilder.getARoot().toString());
//                    if ( path != null ) {
//                        filePath.setText( new File( path ).toURI().toString());
//                    }
//                }
//            });
//
//            getWeb.addActionListener( new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    MiniBrowserDialog brows = minibrowser.get( that );
//                    brows.setVisible( true );
//                    URL url = brows.getUrl();
//                    if ( url != null ) {
//                        filePath.setText( url.toString() );
//                    }
//                }
//            });
//
//            ok.addActionListener( new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    setVisible( false );
//                }
//            });
//
//        }
//
//        public URI getURI() {
//            if ( filePath.getText().isEmpty() | filePath.getText().equals("http://www.")) {
//                return null;
//            }
//
//            try {
//                return new URL( filePath.getText()).toURI();
//
//            } catch (MalformedURLException e) {
//                LOG.warning( "not a valid URL: " + filePath.getText() );
//                return null;
//            } catch (URISyntaxException e) {
//                LOG.warning( "not a valid URI: " + filePath.getText() );
//                return null;
//            }
//        }
//
//    }
//}