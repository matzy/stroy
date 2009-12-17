package org.openCage.ui.impl.about;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.IGridRow;


import org.apache.commons.lang.StringUtils;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.EmailAddress;
import org.openCage.application.protocol.Webpage;
import org.openCage.lang.clazz.Count;
import org.openCage.localization.protocol.Localize;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.ui.impl.BrowseTmp;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.GlobalKeyEventHandler;

public class AboutSheetFromApplication extends JDialog implements AboutSheet {

	private static final long serialVersionUID = -1275151496727359312L;
    private static final Color textColor = new Color( 25, 10, 100);

	private final Application app;
	private final Localize    localize;

    @Inject
	public AboutSheetFromApplication( final Application app, @Named( "ui" ) final Localize localize, GlobalKeyEventHandler keyEventHandler ) {
		this.app = app;		
		this.localize = localize;
		build();

        keyEventHandler.addCloseWindow( this );
	}
	
	private void build() {
        setTitle( app.gettName() );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize( 400, 200 );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        JLabel pic = new JLabel();
        pic.setIcon( app.getIcon());
        layout.row().add( pic );
        layout.row().label( new JLabel("    ")).add( new JLabel("  "));
        
        layout.row().add( newLabel( app.gettName() ));
        layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.version"))).add( newLabel( app.gettVersion().toString() ));
//        layout.row().label( new JLabel( localize.localize( "About.copyright" ))).add( new JLabel( app.getCopyright() ), 3 );

        if ( app.getDescription() != null ) {
            layout.row().label( newIntro( localize.localize( "application.about.short" ))).add( newLabel( app.getDescription()), 6 );
        }

        layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.licence"))).add( newLabel( app.getLicence().getName()), 6 );

//        layout.row().label( new JLabel( localize.localize("org.openCage.localization.dict.help")))/*.add( new JLabel(""), 3 )*/.add( help ).add( new JLabel(""), 5 );


        String str = "";
        for ( Count<? extends Author> author : Count.count(app.getAuthors()) ) {
            str += author.obj.gettName();

            if ( !author.isLast() ) {
                str += "; ";
            }
        }
        if ( !StringUtils.isEmpty(str )) {
            layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.author"))).add( newLabel( str ) );
        }

        
        str = "";
        for ( Count<? extends Author> author : Count.count(app.getContributors()) ) {
            str += author.obj.gettName();

            if ( !author.isLast() ) {
                str += "; ";
            }
        }

        if ( !StringUtils.isEmpty(str )) {
            layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.contributors"))).add( newLabel( str ) );
        }

        layout.row().label( new JLabel("    ")).add( new JLabel("  "));


//        JButton help = new JButton( ".." );
//        layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.help"))).add( newLabel( "stroy.wikidot.com" ), 5 ).add( help );

        final EmailAddress email = app.getSupportEmail();
        JButton emailButton = new JButton( ".." );
        if ( email != null ) {
            layout.row().label( newIntro( localize.localize( "About.contact" ))).add( newLabel( email.gettEmail().toString()), 5 ).add( emailButton );
        }
        

            emailButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        new BrowseTmp().browse( email.gettEmail() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        final Webpage page = app.getWebpage();
        JButton web = new JButton( ".." );
        if ( page != null ) {
            layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.web"))).add( newLabel( page.gettPage().toString() ), 5 ).add( web);
        }




        web.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try {
                    new BrowseTmp().browse( page.gettPage() );
//                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/start" );
                } catch (Exception exp ) {
                    exp.printStackTrace();
                }
            }
        } );

//        help.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                HelpLauncher.showHelp();
//            }
//        });

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );

        pack();


	}

    private JLabel newLabel( String txt ) {
        JLabel label = new JLabel( txt );
        label.setForeground( textColor );

        return label;
    }
    
    private JLabel newIntro( String txt ) {
        JLabel label = new JLabel( txt + ": ");
        return label;
    }
	
}
