package org.openCage.gpad;

import org.openCage.lang.structure.BFStack;
import org.openCage.ui.GoogleQuery;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.html.*;

//from:
//http://www.java-tips.org/java-se-tips/javax.swing/how-to-create-a-simple-browser-in-swing.html

// The Simple Web Browser.
public class MiniBrowserDialog extends JDialog implements HyperlinkListener {

    // These are the buttons for iterating through the page list.
    private JButton backButton, forwardButton;

    // Page location text field.
    private JTextField locationTextField;

    // Editor pane for displaying pages.
    private JEditorPane displayEditorPane;

//    private BFStack<URL> bfstack = new BFStack<URL>();
    private BFStack<String> pages = new BFStack<String>();

    private Pattern pics = Pattern.compile( ".*\\.(gif|jpg|jpeg|png)" );
    private Pattern docs = Pattern.compile( ".*\\.(pdf|avi|mpeg3|mp3|doc)" );

    // Constructor for Mini Web Browser.
    public MiniBrowserDialog( Dialog parent ) {
        super( parent,true );

        // Set window size.
        setSize(800, 480);

        // Handle closing events.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                pages.clear();
                setVisible( false );
            }
        });

//        // Set up file menu.
//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        fileMenu.setMnemonic(KeyEvent.VK_F);
//        JMenuItem fileExitMenuItem = new JMenuItem("Exit",
//                KeyEvent.VK_X);
//        fileExitMenuItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                actionExit();
//            }
//        });
//        fileMenu.add(fileExitMenuItem);
//        menuBar.add(fileMenu);
//        setJMenuBar(menuBar);

        // Set up button panel.
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("<<");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionBack();
            }
        });
        backButton.setEnabled(false);
        buttonPanel.add(backButton);
        forwardButton = new JButton(">>");
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionForward();
            }
        });
        forwardButton.setEnabled(false);
        buttonPanel.add(forwardButton);
        locationTextField = new JTextField(35);
        buttonPanel.add(locationTextField);

        locationTextField.addKeyListener( new KeyAdapter(){
            @Override public void keyPressed( KeyEvent e ) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    Toolkit.getDefaultToolkit().beep();
                    showPage( locationTextField.getText(), true );
                }
            }
        });

//        JButton goButton = new JButton("GO");
//        goButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                actionGo();
//            }
//        });
//        buttonPanel.add(goButton);

        JButton okButton = new JButton( "ok" );
        buttonPanel.add( okButton );
        okButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actionOK();
            }
        });

        // Set up page display.
        displayEditorPane = new JEditorPane();
        displayEditorPane.setContentType("text/html");
        displayEditorPane.setEditable(false);
        displayEditorPane.addHyperlinkListener(this);

        displayEditorPane.addMouseListener( new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                if ( SwingUtilities.isRightMouseButton( mouseEvent)) {//mouseEvent.isMetaDown() ) {
                    mouseEvent.consume();

                    int     pos = displayEditorPane.viewToModel( mouseEvent.getPoint());
                    Element el  = ( (HTMLDocument)displayEditorPane.getDocument()).getCharacterElement( pos );

                    AttributeSet attis = el.getAttributes();

                    Enumeration en = attis.getAttributeNames();

                    Object key = null;
                    //HTML.Tag.
                    while ( en.hasMoreElements() ) {
                        Object n = en.nextElement();
                        if ( n.toString().equals( "src" ) ) {
                            key = n;
                            Object srcsrc = el.getAttributes().getAttribute( key );
                            try {
                                showPage( new URL(displayEditorPane.getPage(), (String)srcsrc).toString(), true);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            return;
                        }
                    }
                }

                //mouseEvent.getSource();
            }

            @Override public void mousePressed(MouseEvent mouseEvent) {}
            @Override public void mouseReleased(MouseEvent mouseEvent) {}
            @Override public void mouseEntered(MouseEvent mouseEvent) {}
            @Override public void mouseExited(MouseEvent mouseEvent) {}
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(displayEditorPane),
                BorderLayout.CENTER);


        showPage( "http://en.wikipedia.org/wiki/Main_Page", true ); // http://stroy.wikidot.com/fausterize
    }

    private void actionOK() {
        setVisible( false );
    }


    // Go back to the page viewed before the current page.
    private void actionBack() {
        if ( pages.hasBackward() )  {
            showPage( pages.backward(), false );
        }
    }

    // Go forward to the page viewed after the current page.
    private void actionForward() {
        if ( pages.hasForward() ) {
            showPage( pages.forward(), false );
        }
    }

//    // Load and show the page specified in the location text field.
//    private void actionGo() {
//        URL verifiedUrl = verifyUrl(locationTextField.getText());
//        if (verifiedUrl != null) {
//            showPage(verifiedUrl, true);
//        } else {
//            showError("Invalid URL");
//        }
//    }

    // Show dialog box with error message.
    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage,
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Verify URL format.
    private URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://"))
            return null;

        // Verify format of URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }

        return verifiedUrl;
    }

//    /* Show the specified page and add it to
//the page list if specified. */
//    private void showPage(URL pageUrl, boolean addToList) {
//        // Show hour glass cursor while crawling is under way.
//        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//
//        try {
//            // Get URL of page currently being displayed.
//            URL currentUrl = displayEditorPane.getPage();
//
//            // Load and display specified page.
//
//            URL newUrl = pageUrl;
//
//            if ( pics.matcher( pageUrl.toString()).matches() ) {
//
//
//                String newPage = "<html><body><img src=\"" +
//                        pageUrl.toString() + "\"/>" +
//                        "</body></html>";
//                displayEditorPane.setText( newPage );
//
//            } else if ( docs.matcher( pageUrl.toString()).matches()){
//
//                String newPage = "<html><body><a href=\"" +
//                        pageUrl.toString() + "\"/>" + pageUrl.toString() + "</a>" +
//                        "</body></html>";
//                displayEditorPane.setText( newPage );
//
//
//            } else {
//                displayEditorPane.setPage(pageUrl);
//                // Get URL of new page being displayed.
//                displayEditorPane.getPage();
//            }
//
//            if ( addToList ) {
//                bfstack.push( newUrl );
//            }
//
//            // Update location text field with URL of current page.
//            locationTextField.setText(newUrl.toString());
//
//            // Update buttons based on the page being displayed.
//            updateButtons();
//        } catch (Exception e) {
//            // Show error messsage.
//            showError("Unable to load page");
//        } finally {
//            // Return to default cursor.
//            setCursor(Cursor.getDefaultCursor());
//        }
//    }


    private void showPage( String pageUrl, boolean addToList) {

        // Show hour glass cursor while crawling is under way.
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {

            if ( !pageUrl.startsWith("http")) {

                String newPage = new GoogleQuery().makeQueryToString( pageUrl );
                displayEditorPane.setText( newPage );

            } else if ( pics.matcher( pageUrl ).matches() ) {

                String newPage = "<html><body><img src=\"" +
                        pageUrl + "\"/>" +
                        "</body></html>";
                displayEditorPane.setText( newPage );

            } else if ( docs.matcher( pageUrl).matches()){

                String newPage = "<html><body><a href=\"" +
                        pageUrl + "\"/>" + pageUrl+ "</a>" +
                        "</body></html>";
                displayEditorPane.setText( newPage );


            } else {
                displayEditorPane.setPage(pageUrl);
                // Get URL of new page being displayed.
                displayEditorPane.getPage();
            }

            if ( addToList ) {
                pages.push( pageUrl );
            }

            System.out.println("pages: " + pages);

            // Update location text field with URL of current page.
            locationTextField.setText(pageUrl);

            // Update buttons based on the page being displayed.
            updateButtons();
        } catch (Exception e) {
            // Show error messsage.
            showError("Unable to load page");
        } finally {
            // Return to default cursor.
            setCursor(Cursor.getDefaultCursor());
        }
    }


    /* Update back and forward buttons based on
the page being displayed. */
    private void updateButtons() {

        backButton.setEnabled( pages.hasBackward());
        forwardButton.setEnabled( pages.hasForward());
    }

    // Handle hyperlink's being clicked.
    public void hyperlinkUpdate(HyperlinkEvent event) {
        HyperlinkEvent.EventType eventType = event.getEventType();
        if (eventType == HyperlinkEvent.EventType.ACTIVATED) {

            event.getSourceElement();
            if (event instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent linkEvent =
                        (HTMLFrameHyperlinkEvent) event;
                HTMLDocument document =
                        (HTMLDocument) displayEditorPane.getDocument();
                document.processHTMLFrameHyperlinkEvent(linkEvent);

            } else {
                showPage(event.getURL().toString(), true);
            }
        }
    }

    public URL getUrl() {

        if ( pages.isEmpty() ) {
            return null;
        }

        try {
            return new URL( pages.getCurrent());
        } catch (MalformedURLException e) {
            return null;
        }
    }

}
