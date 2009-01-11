package org.openCage.stroy.ui.popup;

import org.openCage.util.io.FileUtils;
import org.openCage.util.external.ExternalProgs;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.ui.prefs.StandardProgUI;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.content.Content;
import org.openCage.util.prefs.PreferenceString;
import org.openCage.util.iterator.T2;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
 * All Rights Reserved.
 *
 * Contributor(s):
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

    private FileTypes     fileTypes;

    private JMenuItem diffMenu;
    private JMenuItem diffWith;
    private JMenuItem openWith;
    private JMenuItem open;
    private JMenuItem openAsText;

    private DiffPopupDecider decider = new DiffPopupDecider();

    public DiffPopup( final TreeMatchingTask<T> taskLeft,
                      final TreeMatchingTask<T> taskRight ) {

        this.taskLeft  = taskLeft;
        this.taskRight = taskRight;
        fileTypes      = FileTypes.create();

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
                PrefsUI.create().showFileType( name );
                PrefsUI.create().setVisible( true );
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

                new IgnoreOneUI( NodeToNode.getStringPath( currentPath ), file.getName(), FileUtils.getExtension( file )).setVisible( true );
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

                String cmd = PreferenceString.get( StandardProgUI.STANDARD_TEXT_EDITOR_KEY ).get();
                ExternalProgs.execute( cmd, file.getAbsolutePath() );

            }
        });

        add(openAsText);
    }

    private void openWithMenu() {
        openWith = new JMenuItem( Message.get( "Popup.openWith"));
        openWith.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TreeNode node = NodeToNode.pathToNode( currentPath );

                PrefsUI.create().showFileType( ((Content)node.getContent()).getName() );
                PrefsUI.create().setVisible( true );

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

                String cmd = fileTypes.getOpen(FileUtils.getExtension(file));
                ExternalProgs.execute( cmd, file.getAbsolutePath() );

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
                PrefsUI.create().showFileType( name );
                PrefsUI.create().setVisible( true );
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
//                    if ( cmd != null && cmd.equals( ExternalProgs.STANDARD_DIFF )) {
//                        cmd = PreferenceString.getOrCreate( StandardProgUI.STANDARD_DIFF_KEY).get();
//                    }

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
            return T2.c( node, taskRight.getMatch( node ));
        } else if ( taskLeft != null ) {
            return T2.c( taskLeft.getMatch( node ), node );
        }

        throw new IllegalStateException( "no tasks ?" );
    }

}
