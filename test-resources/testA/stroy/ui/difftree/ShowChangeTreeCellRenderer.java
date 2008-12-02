package org.openCage.stroy.ui.difftree;

import com.google.inject.Inject;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.*;
import java.awt.*;

import org.openCage.stroy.ui.ChangeAsColor;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.ChangeVector;


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

public class ShowChangeTreeCellRenderer extends DefaultTreeCellRenderer {


    @Inject
    private ChangeAsColor changeAsColor;

    private static Color STD_BACK        = Color.WHITE;
    private static Color STD_FOR         = Color.BLACK;

    public Component getTreeCellRendererComponent( JTree   pTree,
                                                   Object  pValue,
                                                   boolean pIsSelected,
                                                   boolean pIsExpanded,
                                                   boolean pIsLeaf,
                                                   int     pRow,
                                                   boolean pHasFocus)
    {

        if ( !(pValue instanceof DefaultMutableTreeNode)) {
            setBackgroundNonSelectionColor( STD_BACK );
            setForeground( STD_FOR );
            return this;
        }

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)pValue;

        if ( !( node.getUserObject() instanceof UINode)) {
            setBackgroundNonSelectionColor( STD_BACK );
            setForeground( STD_FOR);
            return this;
        }

        UINode uiNode = (UINode)node.getUserObject();

        super.getTreeCellRendererComponent( pTree,
                                            pValue,
                                            pIsSelected,
                                            pIsExpanded,
                                            pIsLeaf,
                                            pRow,
                                            pHasFocus );

        setToolTipText( uiNode.getToolTip() );


        ChangeVector cv1 = uiNode.getChangeVector1();
        ChangeVector cv2 = uiNode.getChangeVector2();

        if ( cv2 == null ) {
            setForeground( changeAsColor.getForground( cv1 ));
            setBackgroundNonSelectionColor( changeAsColor.getBackground( cv1 ));
            setBackgroundSelectionColor( new Color( 200, 200, 200) );
        } else {
            setForeground( changeAsColor.getForground( cv1, cv2 ));
            setBackgroundNonSelectionColor( changeAsColor.getBackground( cv1, cv2 ));
        }


        return (this);
    }

}
