package org.openCage.stroy.ui.popup;

import org.openCage.util.io.FileUtils;
import org.openCage.util.external.ExternalProgs;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.ui.prefs.StandardProgUI;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.util.prefs.PreferenceString;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class DiffPopup extends JPopupMenu {

    private final TreeMatchingTask<FileContent> taskRight;
    private final TreeMatchingTask<FileContent> taskLeft;
    private TreePath currentPath;

    private FileTypes fileTypes;
    private ExternalProgs externalProgs;


    public DiffPopup( final TreeMatchingTask<FileContent> taskLeft, final TreeMatchingTask<FileContent> taskRight ) {

        this.taskLeft = taskLeft;
        this.taskRight = taskRight;
        fileTypes = FileTypes.create();

        JMenuItem menuItem = new JMenuItem("diff");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();

                TreeNode<FileContent> node2 = null;
                if ( taskRight != null ) {
                    node2 = taskRight.getMatch( NodeToNode.pathToNode( currentPath ) );
                } else if ( taskLeft != null ) {
                    node2 = taskLeft.getMatch( NodeToNode.pathToNode( currentPath ) );

                }

                if ( node2 == null ) {
                    return;
                }

                File file2 = node2.getContent().getFile();


                String cmd = fileTypes.getDiffType(FileUtils.getExtension(file));
                if ( cmd != null && cmd.equals( StandardProgUI.STANDARD_DIFF_TEXT )) {
                    cmd = PreferenceString.create( StandardProgUI.STANDARD_DIFF_KEY).get();
                }
                externalProgs.execute( cmd, file.getAbsolutePath(), file2.getAbsolutePath() );
            }
        });
        add(menuItem);

        menuItem = new JMenuItem("open");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();

                String cmd = fileTypes.getOpen(FileUtils.getExtension(file));
                externalProgs.execute( cmd, file.getAbsolutePath() );

            }
        });

        add(menuItem);

        menuItem = new JMenuItem("open with texteditor");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();

//                String cmd = fileTypes.getOpen(FileUtils.getExtension(file));
                externalProgs.execute( "notepad", file.getAbsolutePath() );

            }
        });

        add(menuItem);

        addSeparator();

//        menuItem = new JMenuItem("diff with");
//        menuItem.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//
//                new OneDiffUI().setVisible( true );
//
////                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();
////
////                TreeNode<FileContent> node2 = null;
////                if ( taskRight != null ) {
////                    node2 = taskRight.getMatch( NodeToNode.pathToNode( currentPath ) );
////                } else if ( taskLeft != null ) {
////                    node2 = taskLeft.getMatch( NodeToNode.pathToNode( currentPath ) );
////
////                }
////
////                if ( node2 == null ) {
////                    return;
////                }
////
////                File file2 = node2.getContent().getFile();
////
////
////                String cmdType = fileTypes.getDiffType(FileUtils.getExtension(file));
////                String cmd = externaldiff.get().get( cmdType );
////                externalProgs.execute( cmd, file.getAbsolutePath(), file2.getAbsolutePath() );
//            }
//        });
//
//        add(menuItem);
//        menuItem = new JMenuItem("open with");
//        menuItem.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
////                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();
////
////                String cmdType = fileTypes.getOpen(FileUtils.getExtension(file));
////                String cmd = externalOpen.get().get( cmdType );
////                externalProgs.execute( cmd, file.getAbsolutePath() );
////
//            }
//        });
//        add(menuItem);
//        addSeparator();

        menuItem = new JMenuItem("ignore");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();

                new IgnoreOneUI( NodeToNode.getStringPath( currentPath ), file.getName(), FileUtils.getExtension( file )).setVisible( true );
            }
        });
        add(menuItem);

        menuItem = new JMenuItem("preferences");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                File file = NodeToNode.pathToNode( currentPath ).getContent().getFile();
                String name = file.getName();
                PrefsUI.create().showFileType( name );
                PrefsUI.create().setVisible( true );
            }
        });

        add(menuItem);
    }

    public void open( MouseEvent event, TreePath path ) {

        currentPath = path;
        show( event.getComponent(), event.getX(), event.getY());
    }


}
