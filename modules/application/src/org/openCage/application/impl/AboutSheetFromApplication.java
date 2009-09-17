package org.openCage.application.impl;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.java.dev.designgridlayout.DesignGridLayout;

import org.openCage.application.protocol.AboutSheet;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;

import org.openCage.localization.protocol.Localize;

import com.google.inject.Inject;

public class AboutSheetFromApplication extends JDialog implements AboutSheet {

	private static final long serialVersionUID = -1275151496727359312L;

	private final Application  app;
	private final Localize     localize; 
	
	@Inject
	public AboutSheetFromApplication( final Application app, final Localize localize ) {
		this.app = app;		
		this.localize = localize;
		build();
	}
	
	private void build() {
        setTitle( app.getName() );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize( 400, 200 );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        JLabel pic = new JLabel();
        ImageIcon icon = new ImageIcon( "/Users/stephan/Documents/prs/stroy-ng/modules/application/test/org/openCage/Photo 1.jpg" );
        pic.setIcon(icon);
        layout.row().add( pic );
        
        layout.row().add( new JLabel( app.getName() ));
        layout.row().label( new JLabel( localize.localize( "About.version" ))).add( new JLabel( app.getVersion().toString() ));
//        layout.row().label( new JLabel( localize.localize( "About.copyright" ))).add( new JLabel( app.getCopyright() ), 3 );
        layout.row().label( new JLabel( localize.localize( "About.short" ))).add( new JLabel( localize.localize( "About.description" )), 6 );
        layout.row().label( new JLabel( localize.localize( "About.licence" ))).add( new JLabel( app.getLicence().getName()), 6 );

        JButton help = new JButton( localize.localize( "Menu.Help" ) );
        layout.row().label( new JLabel( localize.localize( "Menu.Help" )))/*.add( new JLabel(""), 3 )*/.add( help ).add( new JLabel(""), 5 );

        boolean first = true;
        for ( final Author author : app.getAuthors() ) {

            if ( first ) {
                first = false;
                layout.row().label( new JLabel( localize.localize( "About.author" ))).add( new JLabel( author.getName() ),2 );
            } else {
                layout.row().add( new JLabel( author.getName() ), 2 );
            }
        }
        
        first = true;
        for ( final Author author : app.getContributors()) {
            if ( first ) {
                first = false;
                layout.row().label( new JLabel( localize.localize( "About.contributors" ))).add( new JLabel( author.getName() ),2 );
            } else {
                layout.row().add( new JLabel( author.getName() ), 2 );
            }
        }
        
//        layout.row().label( new JLabel( localize.localize( "About.contributors" ))).add( new JLabel( "Misa Inabe, Miguel Cuadron Marion" ),6 );

//        if ( app.getContactEmail() != null ) {
//            JButton contact = new JButton( app.getContactEmail());
//            final String mailto = "mailto:" + app.getContactEmail();
//            contact.addActionListener( new ActionListener() {
//                public void actionPerformed(ActionEvent actionEvent) {
//                    try {
//                        BrowserLauncher.displayURL( mailto );
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            layout.row().label( new JLabel( localize.localize( "About.contact" )))/*.add( new JLabel(""),5)*/.add( contact, 3 ).add( new JLabel(""),3);
//        }

        JButton web = new JButton( "http://stroy.wikidot.com" );
        layout.row().label( new JLabel( localize.localize( "About.web" ))).add( web, 3 ).add( new JLabel(""), 3);

//        web.addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent e ) {
//                try {
//                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/start" );
//                } catch (Exception exp ) {
//                    exp.printStackTrace();
//                }
//            }
//        } );

//        help.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                HelpLauncher.showHelp();
//            }
//        });

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );

        pack();

	}
	
}
