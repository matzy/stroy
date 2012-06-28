package org.openCage.ui.impl.about;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

import javax.swing.*;

import net.java.dev.designgridlayout.DesignGridLayout;


import org.openCage.artig.stjx.Address;
import org.openCage.artig.stjx.Artifact;
import org.openCage.artig.stjx.Author;
import org.openCage.artig.stjx.Deployed;
import org.openCage.lang.Strings;
import org.openCage.lang.iterators.Count;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.localization.Localize;
import org.openCage.ui.impl.BrowseTmp;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.GlobalKeyEventHandler;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class AboutSheetFromApplication extends JDialog implements AboutSheet {

    private static final long serialVersionUID = -1275151496727359312L;
    private static final Color textColor = new Color( 25, 10, 100);

    private final Artifact app;
    private final Localize localize;
    private final Deployed deployed;

    @Inject
    public AboutSheetFromApplication( final Deployed deployed, @Named( "ui" ) final Localize localize, GlobalKeyEventHandler keyEventHandler ) {
        this.deployed = deployed;
        this.app = deployed.getArtifact();
        this.localize = localize;
        build();

        keyEventHandler.addCloseWindow( this );
    }

    private void build() {
//        setTitle( app.getName() );
//        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
//        setBounds( 20, 20,400, 200 );
//
//        JPanel top = new JPanel();
//        DesignGridLayout layout = new DesignGridLayout( top );
//        top.setLayout( layout );
//
//        JLabel pic = new JLabel();
//        //pic.setIcon( deployed.getIcon()); TODO
//        layout.row().add( pic );
//        layout.row().label( new JLabel("    ")).add( new JLabel("  "));
//
//        layout.row().add( newLabel( app.getName() ));
//        layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.version"))).add( newLabel( app.getVersion() ));
////        layout.row().label( new JLabel( localize.localize( "About.copyright" ))).add( new JLabel( app.getCopyright() ), 3 );
//
//        if ( app.getDescription() != null ) {
//            layout.row().label( newIntro( localize.localize( "application.about.short" ))).add( newLabel( app.getDescription()), 6 );
//        }
//
//        layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.licence"))).add( newLabel( app.getLicence()), 6 ); // TODO address?
//
////        layout.row().label( new JLabel( localize.localize("org.openCage.localization.dict.help")))/*.add( new JLabel(""), 3 )*/.add( help ).add( new JLabel(""), 5 );
//
//
//        String str = "";
//        for ( Count<? extends Author> author : Count.count(app.getAuthors()) ) {
//            str += author.obj().getName();
//
//            if ( !author.isLast() ) {
//                str += "; ";
//            }
//        }
//        if ( !Strings.isEmpty(str )) {
//            layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.author"))).add( newLabel( str ) );
//        }
//
//
//        str = "";
//        for ( Count<? extends Author> author : Count.count(app.getContributors()) ) {
//            str += author.obj().getName();
//
//            if ( !author.isLast() ) {
//                str += "; ";
//            }
//        }
//
//        if ( !Strings.isEmpty(str )) {
//            layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.contributors"))).add( newLabel( str ) );
//        }
//
//        layout.row().label( new JLabel("    ")).add( new JLabel("  "));
//
//
////        JButton help = new JButton( ".." );
////        layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.help"))).add( newLabel( "stroy.wikidot.com" ), 5 ).add( help );
//
//        final String email = app.getSupport();
//        JButton emailButton = new JButton( ".." );
//        if ( email != null ) {
//            layout.row().label( newIntro( localize.localize( "About.contact" ))).add( newLabel( email ), 5 ).add( emailButton );
//        }
//
//
//        emailButton.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    new BrowseTmp().browse( new URI( "mailto:" + email ));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        final Address page = app.getAddress();
//        JButton web = new JButton( ".." );
//        if ( page != null ) {
//            layout.row().label( newIntro( localize.localize("org.openCage.localization.dict.web"))).add( newLabel( page.getShrt() ), 5 ).add( web);
//        }
//
//        layout.row().label( new JLabel("    ")).add( new JLabel("  "));
//        layout.row().label( newIntro( "used software and their licences" )).add( newLabel(""));
//
//
//
//        web.addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent e ) {
//                try {
//                    new BrowseTmp().browse( new URI(page.getPage()) ); // TODO check
////                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/start" );
//                } catch (Exception exp ) {
//                    exp.printStackTrace();
//                }
//            }
//        } );
//
////        help.addActionListener( new ActionListener() {
////            public void actionPerformed(ActionEvent actionEvent) {
////                HelpLauncher.showHelp();
////            }
////        });
//
//        getContentPane().setLayout( new BorderLayout());
//        getContentPane().add( top, BorderLayout.CENTER  );
//
//        JScrollPane scrollLice = new JScrollPane( getLicenceComponent() );
//
//        getContentPane().add( scrollLice, BorderLayout.SOUTH  );
//
//        pack();


    }


    JComponent getLicenceComponent() {
        final List<Artifact> refs = new ArrayList<Artifact>();
        refs.addAll( deployed.getDependencies()); // TODO compile only

        Collections.sort( refs, new Comparator<Artifact>() {
            @Override
            public int compare(Artifact o1, Artifact o2) {
                return o1.getName().compareToIgnoreCase( o2.getName() );
            }
        });

        String[] lic = new String[refs.size()];
        for ( Count<Artifact> dep : Count.count( refs )) {
            String name = dep.obj().getName();
            lic[dep.idx()] =  name + "         " + dep.obj().getLicence();
        }

        final JList licences = new JList( lic );
        licences.setMinimumSize( new Dimension( 100,100 ));

        licences.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent e){

                if(e.getClickCount() == 2){
                    System.out.println( e );
                    int index = licences.locationToIndex(e.getPoint());
                    ListModel dlm = licences.getModel();
                    licences.ensureIndexIsVisible(index);

                    Artifact selected = refs.get( index);


                    try {
                        Desktop.getDesktop().browse( new URI( selected.getAddress().getPage() ));
                    } catch (IOException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        } );


        return licences;

    }

    private JLabel newLabel( String txt ) {
        JLabel label = new JLabel( txt );
        label.setForeground( textColor );

        return label;
    }

    private JLabel newIntro( String txt ) {
        return new JLabel( txt + ": ");
    }

    @Override
    public String toString() {
        return "AboutSheetFromApplication{" +
                "app=" + app +
                '}';
    }
}
