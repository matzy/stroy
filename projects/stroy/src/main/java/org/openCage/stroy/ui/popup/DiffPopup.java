package org.openCage.stroy.ui.popup;

import com.google.inject.name.Named;
import org.openCage.lang.structure.ObservableRef;
import org.openCage.lang.structure.T2;
import org.openCage.lang.structure.Tu;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.util.external.DesktopX;
import org.openCage.util.io.FileUtils;
import org.openCage.util.external.ExternalProgs;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.content.Content;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

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

/**
 * Popupmenu for stroy
 * offer view diff prefs ... depending on selection type
 * TODO unify with popupselector
 */
public class DiffPopup<T extends Content> extends JPopupMenu {

    private final TreeMatchingTask<T> taskRight;
    private final TreeMatchingTask<T> taskLeft;
    private TreePath                  currentPath;

//    private FileTypes     fileTypes;
    private final FileTypes fileTypes;

    private JMenuItem diffMenu;
    private JMenuItem diffWith;
    private JMenuItem openWith;
    private JMenuItem open;
    private JMenuItem openAsText;

    private DiffPopupDecider decider;
    private PrefsUI prefsUI;
    private final ObservableRef<String> editor;
    private final ObservableRef<String> diffProg;
    private final IgnoreCentral central;

    private final DesktopX desktop;

    public DiffPopup(final PrefsUI prefsUI,
                     @Named(value = "Editor") ObservableRef<String> editor,
                     @Named(value = "DiffProg") ObservableRef<String> diffProg,
                     final FileTypes fileTypes,
                     IgnoreCentral central,
                     DesktopX desktop,
                     final TreeMatchingTask<T> taskLeft,
                     final TreeMatchingTask<T> taskRight) {
        this.diffProg = diffProg;
        this.fileTypes = fileTypes;
        this.central = central;
        this.desktop = desktop;
        this.decider =  new DiffPopupDecider(fileTypes);


        this.taskLeft  = taskLeft;
        this.taskRight = taskRight;
        this.prefsUI = prefsUI;
        this.editor = editor;

        diffMenu();
        diffWithMenu();
        openMenu();
        openWithMenu();
        openAsText();

        addSeparator();

        ignoreMenue();

        // TODO
//        menuItem = new JMenuItem("done and filter");
////        menuItem.addActionListener( new ActionListener() {
////            public void actionPerformed(ActionEvent actionEvent) {
////                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();
////
////                new IgnoreOneUI( NodeToNode.getStringPath( currentPath ), file.getName(), FileUtils.getExtension( file )).setVisible( true );
////            }
////        });
//        add(menuItem);


        prefMenu();
    }

    private void prefMenu() {
        JMenuItem menuItem;
        menuItem = new JMenuItem( Message.get( "Menu.Preference"));
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.getFile( currentPath );

                // TODO soemthing different for not file contents
                if ( file == null ) {
                    return;
                }

                String name = file.getName();
                prefsUI.showFileType( name );
                prefsUI.setVisible( true );
            }
        });

        add(menuItem);
    }

    private void ignoreMenue() {
        JMenuItem menuItem = new JMenuItem( Message.get( "Popup.ignore"));
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.getFile( currentPath );

                // TODO something different for not file contents
                if ( file == null ) {
                    return;
                }

                new IgnoreOneUI( NodeToNode.getStringPath( currentPath ), file.getName(), FileUtils.getExtension( file ), central).setVisible( true );
            }
        });
        add(menuItem);
    }

    private void openAsText() {
        openAsText = new JMenuItem( Message.get( "Popup.openAsText"));
        openAsText.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.getFile( currentPath );
                if ( file == null ) {
                    return;
                }

                desktop.openAsText( file );

//                String cmd = editor.get().get();
//
//                ExternalProgs.execute( cmd, file.getAbsolutePath() );
            }
        });

        add(openAsText);
    }

    private void openWithMenu() {
        openWith = new JMenuItem( Message.get( "Popup.openWith"));
        openWith.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TreeNode node = NodeToNode.pathToNode( currentPath );

                prefsUI.showFileType( ((Content)node.getContent()).getName() );
                prefsUI.setVisible( true );

            }
        });

        add(openWith);
    }

    private void openMenu() {
        open = new JMenuItem( Message.get( "Popup.open"));
        open.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.getFile( currentPath );
                if ( file == null ) {
                    return;
                }

                desktop.open( file );
            }
        });

        add(open);
    }

    private void diffWithMenu() {
        diffWith = new JMenuItem( Message.get( "Popup.diffwith"));
        diffWith.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.getFile( currentPath );

                // TODO soemthing different for not file contents
                if ( file == null ) {
                    return;
                }

                String name = file.getName();
                prefsUI.showFileType( name );
                prefsUI.setVisible( true );
            }
        });

        add(diffWith);
    }

    private void diffMenu() {
        JMenuItem menuItem;

        menuItem = new JMenuItem( Message.get( "Popup.diff"));
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent actionEvent ) {
                if ( NodeToNode.getFile( currentPath ) == null ) {
                    return;
                }

                T2<TreeNode<T>,TreeNode<T>> nodes =
                        getLeftAndRightNode( NodeToNode.pathToNode( currentPath ));

                String cmd = fileTypes.getDiffType(FileUtils.getExtension(nodes.i0.getContent().getName()));

                if ( cmd != null && cmd.equals( ExternalProgs.STANDARD_DIFF )) {
                    cmd = diffProg.get();
                }

                ExternalProgs.execute( cmd,
                        nodes.i0.getContent().getLocation(),
                        nodes.i1.getContent().getLocation() );
            }
        });
        add(menuItem);

        diffMenu = menuItem;
    }

    public void open( MouseEvent event, TreePath path ) {

        currentPath = path;

        TreeNode<T>                 node    = NodeToNode.pathToNode( currentPath );
        boolean                     matched = false;

        if ( taskRight != null ) {
            matched = taskRight.isContentChanged( node );
        } else if ( taskLeft != null ) {
            matched = taskLeft.isContentChanged( node );
        }

        diffMenu.setVisible( matched && decider.showDiff( node ));
        diffWith.setVisible( matched && decider.showDiffWith( node ));

        open.setVisible( decider.showOpen( node ));
        openWith.setVisible( decider.showOpenWith( node ));
        openAsText.setVisible( decider.showOpenAsText( node ));

//        if ( diffMenu != null ) {
//            if ( hideDiff ) {
//                diffMenu.setVisible( false );
//                diffWith.setVisible( false );
//            } else {
//                String cmd = fileTypes.getDiffType(FileUtils.getExtension(rl.i0.getContent().getName()));
//
//                if ( cmd == null || cmd.equals( ExternalProgs.unknown ) ) {
//                    diffWith.setVisible( true );
//                    diffMenu.setVisible( false );
//                } else {
//                    diffWith.setVisible( false );
//                    diffMenu.setVisible( true );
//                }
//            }
//
//        } else {
//            diffWith.setVisible( false );
//        }

        show( event.getComponent(), event.getX(), event.getY());
    }

    T2<TreeNode<T>,TreeNode<T>> getLeftAndRightNode( TreeNode<T> node ) {

        if ( taskRight != null ) {
            return Tu.c(node, taskRight.getMatch(node));
        } else if ( taskLeft != null ) {
            return Tu.c( taskLeft.getMatch( node ), node );
        }

        throw new IllegalStateException( "no tasks ?" );
    }

}
