package org.openCage.util.swing;

import org.openCage.util.logging.Log;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;

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

public class Scrolling {

    public static void scrollTo( JTree tree, JViewport viewport, TreePath treePath) {
        Rectangle rec = tree.getPathBounds( treePath );

        scrollToMiddle( viewport, rec );
    }

    public static Rectangle getRelativeLocation( JViewport viewport, Rectangle rect  ) {

        if ( rect == null ) {
            return null;
        }

        Rectangle viewRect = viewport.getViewRect();
        Rectangle ret = new Rectangle( rect );
        ret.setLocation( ret.x -  viewRect.x, ret.y - viewRect.y );

        return ret;
    }

    public static void scrollToRelative( JViewport viewport, Rectangle toScroll, Rectangle relative )  {

        if ( viewport == null || toScroll == null || relative == null ) {
            Log.warning( "bug?" );
            return;
        }
        int centerY = (viewport.getViewRect().height-toScroll.height)/2;

        if ( centerY < relative.y ) {
            scrollToMiddle( viewport, new Rectangle( toScroll.x, toScroll.y - (relative.y-centerY), toScroll.width, toScroll.height ));
        } else {
            scrollToMiddle( viewport, new Rectangle( toScroll.x, toScroll.y + (centerY - relative.y), toScroll.width, toScroll.height ));
        }

//        toScroll.setLocation(toScroll.x, toScroll.y + relative.y);

//        // The location of the view relative to the table
//        Rectangle viewRect = viewport.getViewRect();
//
//        // Translate the cell location so that it is relative
//        // to the view, assuming the northwest corner of the
//        // view is (0,0).
//        toScroll.setLocation(toScroll.x-viewRect.x, toScroll.y-viewRect.y);
//
//        // Calculate location of rect if it were at the center of view
//        int centerX = (viewRect.width-toScroll.width)/2;
//
//        int centerY = viewRect.height - relative.y - toScroll.height;
//
//        // Fake the location of the cell so that scrollRectToVisible
//        // will move the cell to the center
//        if (toScroll.x < centerX) {
//            centerX = -centerX;
//        }
//        if (toScroll.y < centerY) {
//            centerY = -centerY;
//        }
//        toScroll.translate(centerX, centerY);
//
//        // Scroll the area into view.
//        viewport.scrollRectToVisible(toScroll);
    }

    public static void scrollToMiddle( JViewport viewport, Rectangle rect ) {
        // The location of the view relative to the table
        Rectangle viewRect = viewport.getViewRect();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0).
        rect.setLocation(rect.x-viewRect.x, rect.y-viewRect.y);

        // Calculate location of rect if it were at the center of view
        int centerX = (viewRect.width-rect.width)/2;
        int centerY = (viewRect.height-rect.height)/2;

        // Fake the location of the cell so that scrollRectToVisible
        // will move the cell to the center
        if (rect.x < centerX) {
            centerX = -centerX;
        }
        if (rect.y < centerY) {
            centerY = -centerY;
        }
        rect.translate(centerX, centerY);

        // Scroll the area into view.
        viewport.scrollRectToVisible(rect);

    }
}

//
//// Make the cell (1,2) is appear in the center of the view
//    int rowIndex = 1;
//    int vColIndex = 2;
//    scrollToCenter(table, rowIndex, vColIndex);
//
//    // Assumes table is contained in a JScrollPane. Scrolls the
//    // cell (rowIndex, vColIndex) so that it is visible at the center of viewport.
//    public void scrollToCenter(JTable table, int rowIndex, int vColIndex) {
//        if (!(table.getParent() instanceof JViewport)) {
//            return;
//        }
//        JViewport viewport = (JViewport)table.getParent();
//
//        // This rectangle is relative to the table print the
//        // northwest corner of cell (0,0) is always (0,0).
//        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
//
//        // The location of the view relative to the table
//        Rectangle viewRect = viewport.getViewRect();
//
//        // Translate the cell location so that it is relative
//        // to the view, assuming the northwest corner of the
//        // view is (0,0).
//        rect.setLocation(rect.x-viewRect.x, rect.y-viewRect.y);
//
//        // Calculate location of rect if it were at the center of view
//        int centerX = (viewRect.width-rect.width)/2;
//        int centerY = (viewRect.height-rect.height)/2;
//
//        // Fake the location of the cell so that scrollRectToVisible
//        // will move the cell to the center
//        if (rect.x < centerX) {
//            centerX = -centerX;
//        }
//        if (rect.y < centerY) {
//            centerY = -centerY;
//        }
//        rect.translate(centerX, centerY);
//
//        // Scroll the area into view.
//        viewport.scrollRectToVisible(rect);
//    }
