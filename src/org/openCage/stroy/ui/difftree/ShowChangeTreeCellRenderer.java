package org.openCage.stroy.ui.difftree;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.util.ui.CompositeIcon;


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

public class ShowChangeTreeCellRenderer extends DefaultTreeCellRenderer {

    private JTree tree;

//    @Inject
//    private ChangeAsColor changeAsColor;

    private static Color STD_BACK        = Color.WHITE;
    private static Color STD_FOR         = Color.BLACK;


    public void setTree( JTree tree ) {
        this.tree = tree;
    }

//    public int getHorizontalTextPosition() {
//        return super.getHorizontalTextPosition() + 70;
//    }


    public Dimension getPreferredSize() {
//        if ( tree != null ) {
//            return new Dimension( tree.getWidth(), super.getPreferredSize().height );
//        }
        return new Dimension( 300, super.getPreferredSize().height );
    }

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

        super.getTreeCellRendererComponent(
                pTree,
                pValue,
                pIsSelected,
                pIsExpanded,
                pIsLeaf,
                pRow,
                pHasFocus );


        setToolTipText( uiNode.getToolTip() );

        List<Icon> icons = new ArrayList<Icon>();


        int stdSize;

        if ( node.isLeaf() ) {
            icons.add( getDefaultLeafIcon());
            stdSize = getDefaultLeafIcon().getIconWidth();
        } else if ( pIsExpanded ) {
            icons.add( getDefaultOpenIcon() );
            stdSize = getDefaultOpenIcon().getIconWidth();
        } else {
            icons.add( getDefaultClosedIcon() );
            stdSize = getDefaultClosedIcon().getIconWidth();
        }

        ChangeVector cvLeft = uiNode.getChangeVectorLeft();
        ChangeVector cvRight = uiNode.getChangeVectorRight();

        addIcons( icons, cvLeft );
        addIcons( icons, cvRight );

        if ( icons.size() > 0 ) {
            Icon compo = new CompositeIcon( icons );
            setIcon( compo );

//            Rectangle rec = this.getBounds();
//            setBounds( rec.x, rec.y, rec.width + compo.getIconWidth(), rec.height);

            // TODO 
//            setText( getText() + "                        ");
//            System.out.println( ">" + getPreferredSize());
//            Dimension dim1 = getPreferredSize();
//            Dimension dim = new Dimension( Math.max( dim1.width + compo.getIconWidth() - stdSize, 100), dim1.height );
////
////            dim.setSize( Math.max( dim.width + compo.getIconWidth() - stdSize, 100), dim.height );
//            System.out.println("dim " + (dim.width + compo.getIconWidth() - stdSize));
//            setPreferredSize( dim);
//
//            System.out.println( "<" + getPreferredSize());

        }

//        if ( cvRight == null ) {
//            setForeground( changeAsColor.getForground( cvLeft ));
//            setBackgroundNonSelectionColor( changeAsColor.getBackground( cvLeft ));
//            setBackgroundSelectionColor( new Color( 200, 200, 200) );
//        } else {
//            setForeground( changeAsColor.getForground( cvLeft, cvRight ));
//            setBackgroundNonSelectionColor( changeAsColor.getBackground( cvLeft, cvRight ));
//        }

        return (this);
    }

    private void addIcons( List<Icon> icons, ChangeVector cv ) {

        if ( cv.ghost ) {
            setForeground( Color.LIGHT_GRAY );
        }


        if ( cv.parent ) {
            URL url = getClass().getResource( "/org/openCage/stroy/ui/difftree/move-icon.png" );
            icons.add( new ImageIcon( url ));
        }

        if ( cv.name ) {
            URL url = getClass().getResource( "/org/openCage/stroy/ui/difftree/rename-icon.png" );
            icons.add( new ImageIcon( url ));
        }

        if ( cv.only ) {
            URL url = getClass().getResource( "/org/openCage/stroy/ui/difftree/add-icon.png" );
            icons.add( new ImageIcon( url ));
        }

        if ( cv.ghost ) {
            URL url = getClass().getResource( "/org/openCage/stroy/ui/difftree/only-icon.png" );
            icons.add( new ImageIcon( url ));
        }

        URL url;
        ContentDiff diff = cv.content;
        if ( diff != null ) {
            switch( diff ) {
                case unknown:
                    url = getClass().getResource( "/org/openCage/stroy/ui/difftree/content-unknown-icon.png" );
                    icons.add( new ImageIcon( url ));
                    break;
                case different:
                case sameEnough:
                    url = getClass().getResource( "/org/openCage/stroy/ui/difftree/content-icon.png" );
                    icons.add( new ImageIcon( url ));
                    break;
            }
        }
    }


}
