package org.openCage.util.ui;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;

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
