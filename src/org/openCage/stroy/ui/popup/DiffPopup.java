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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class DiffPopup<T extends Content> extends JPopupMenu {

    private final TreeMatchingTask<T> taskRight;
    private final TreeMatchingTask<T> taskLeft;
    private TreePath                  currentPath;

    private FileTypes     fileTypes;
    private ExternalProgs externalProgs;

    private JMenuItem diffMenu;
    private JMenuItem diffWith;

    public DiffPopup( final TreeMatchingTask<T> taskLeft,
                      final TreeMatchingTask<T> taskRight,
                      boolean                   file,
                      boolean                   app,
                      boolean                   matched ) {

        this.taskLeft  = taskLeft;
        this.taskRight = taskRight;
        fileTypes      = FileTypes.create();

        JMenuItem menuItem;

        if ( (file || app) &&  matched ) {
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

                    externalProgs.execute( cmd,
                                           nodes.i0.getContent().getFile().getAbsolutePath(),
                                           nodes.i1.getContent().getFile().getAbsolutePath() );
                }
            });
            add(menuItem);

            diffMenu = menuItem;
        }


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


        if ( file || app ) {
            menuItem = new JMenuItem( Message.get( "Popup.open"));
            menuItem.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    File file = NodeToNode.getFile( currentPath );
                    if ( file == null ) {
                        return;
                    }

                    String cmd = fileTypes.getOpen(FileUtils.getExtension(file));
                    externalProgs.execute( cmd, file.getAbsolutePath() );

                }
            });

            add(menuItem);
        }

        if ( file ) {
            menuItem = new JMenuItem( Message.get( "Popup.openAsText"));
            menuItem.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    File file = NodeToNode.getFile( currentPath );
                    if ( file == null ) {
                        return;
                    }

                    String cmd = PreferenceString.get( StandardProgUI.STANDARD_TEXT_EDITOR_KEY ).get();
                    externalProgs.execute( cmd, file.getAbsolutePath() );

                }
            });

            add(menuItem);
        }

        addSeparator();

        menuItem = new JMenuItem( Message.get( "Popup.ignore"));
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



        if ( file ) {
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
    }

    public void open( MouseEvent event, TreePath path ) {

        currentPath = path;

        boolean hideDiff = false;
        T2<TreeNode<T>,TreeNode<T>> rl= null;
        if ( NodeToNode.pathToNode( currentPath ) == null ) {
            hideDiff = true;
        } else {
             rl = getLeftAndRightNode(NodeToNode.pathToNode( currentPath ));

            if ( rl.i0 == null || rl.i1 == null ) {
                hideDiff = true;
            }
        }

        if ( diffMenu != null ) {
            if ( hideDiff ) {
                diffMenu.setVisible( false );
                diffWith.setVisible( false );
            } else {
                String cmd = fileTypes.getDiffType(FileUtils.getExtension(rl.i0.getContent().getName()));

                if ( cmd == null || cmd.equals( ExternalProgs.unknown ) ) {
                    diffWith.setVisible( true );
                    diffMenu.setVisible( false );
                } else {
                    diffWith.setVisible( false );
                    diffMenu.setVisible( true );                    
                }
            }

        } else {
            diffWith.setVisible( false );
        }

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
