package org.openCage.stroy.ui;

import java.awt.*;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
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

public class ChangeAsColorImpl implements ChangeAsColor {

    public Color getForground(ChangeVector cv) {

        if ( cv.only ) {
            return Colors.ONLYHERE;
        }

        if ( cv.content ) {

            if ( cv.name || cv.parent ) {
                return Colors.CONTENT_AND_STRUCTUR;
            }
            return Colors.CONTENT;
        }


        if ( cv.name || cv.parent ) {
            return Colors.STRUCTUR;
        }

        return Colors.STD;
    }

    public Color getBackground(ChangeVector cv) {

        return Color.WHITE;
    }

    public Color getForground(ChangeVector cv1, ChangeVector cv2) {

        if ( cv1.only && cv2.only ) {
            return Color.RED;
        }

        if ( (cv1.content || cv2.content ) && (cv1.name || cv2.name || cv1.parent || cv2.parent )) {
            return Colors.CONTENT_AND_STRUCTUR;
        }

        if ( cv1.name || cv2.name || cv1.parent || cv2.parent ) {
            return Colors.STRUCTUR;
        }

        if ( cv1.content || cv2.content ) {
            return Colors.CONTENT;
        }


        return Colors.STD;
    }

    public Color getBackground(ChangeVector cv1, ChangeVector cv2) {
        return Color.WHITE;
    }

}
