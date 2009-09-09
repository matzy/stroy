package org.openCage.util.ui;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
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


/**
 * An Icon build by adding serveral icons along the x-axis
 * inspired by http://forum.java.sun.com/thread.jspa?threadID=265029&messageID=1032016
 * user KPSeal
 */
public class CompositeIcon implements Icon {

    private final List<Icon> icons;

    public CompositeIcon( List<Icon> icons ) {
        this.icons = icons;
    }

    public void paintIcon( Component c, Graphics g, int x, int y ) {
        int xx = x;
        for ( Icon icon : icons ) {
            icon.paintIcon( c, g, xx, y);
            xx += icon.getIconWidth(); 
        }
    }

    public int getIconWidth() {
        int width = 0;

        for ( Icon icon : icons ) {
            width += icon.getIconWidth();
        }

        return width;
    }

    public int getIconHeight() {
        int height = 0;

        for ( Icon icon : icons ) {
            height = Math.max( height, icon.getIconHeight() );
        }

        return height;
    }
}
