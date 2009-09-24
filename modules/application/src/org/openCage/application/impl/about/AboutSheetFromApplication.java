package org.openCage.application.impl.about;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.IGridRow;

import org.openCage.application.protocol.AboutSheet;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;

import org.openCage.localization.protocol.Localize;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class AboutSheetFromApplication extends JDialog implements AboutSheet {

	private static final long serialVersionUID = -1275151496727359312L;

	private final Application  app;
	private final Localize     localize; 
	
	@Inject
	public AboutSheetFromApplication( final Application app, @Named( "application" ) final Localize localize ) {
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
        pic.setIcon( app.getIcon());
//        pic.setIcon( new ImageIcon( "/Users/stephan/Documents/prs/stroy-ng/modules/application/test/org/openCage/other/Photo 1.jpg" ));
        layout.row().add( pic );
        
        layout.row().add( new JLabel( app.getName() ));
        layout.row().label( new JLabel( localize.localize( "version" ))).add( new JLabel( app.getVersion().toString() ));
//        layout.row().label( new JLabel( localize.localize( "About.copyright" ))).add( new JLabel( app.getCopyright() ), 3 );
        layout.row().label( new JLabel( localize.localize( "application.about.short" ))).add( new JLabel( localize.localize( "description" )), 6 );
        layout.row().label( new JLabel( localize.localize( "licence" ))).add( new JLabel( app.getLicence().getName()), 6 );

        JButton help = new JButton( localize.localize( "help" ) );
        layout.row().label( new JLabel( localize.localize( "help" )))/*.add( new JLabel(""), 3 )*/.add( help ).add( new JLabel(""), 5 );

        boolean first = true;
        for ( final Author author : app.getAuthors() ) {

            if ( first ) {
                first = false;
                layout.row().label( new JLabel( localize.localize( "author" ))).add( new JLabel( author.getName() ),2 );
            } else {
                layout.row().add( new JLabel( author.getName() ), 2 );
            }
        }
        
        // 5 in a row max
        first = true;        
    	IGridRow row = null;
    	int idx = 0;
        for ( final Author author : app.getContributors()) {

        	if ( first ) {
            	row = layout.row();
                first = false;
                row.label( new JLabel( localize.localize( "contributors" )));
            } else if ( idx < 5 ) {
            	idx++;
            } else {
            	idx = 0;
            	row = layout.row();            	
            }
        	
        	row.add( new JLabel( author.getName() ),2 );
        }
        

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
        layout.row().label( new JLabel( localize.localize( "web" ))).add( web, 3 ).add( new JLabel(""), 3);

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
