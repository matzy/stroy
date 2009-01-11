package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.diff.ContentDiff;

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
 * Used to store the summmery of changes within a folder
 */
public class ChangeNumbers {
    public int content;
    public int structure;
    public int only;

    public ChangeNumbers( ChangeVector cv ) {
        content = (!cv.content.equals( ContentDiff.same)) ? 1 : 0;
        structure = (cv.name |cv.parent)? 1 : 0;
        only = cv.only ? 1 : 0;
    }

    public void add( ChangeNumbers other ) {
        content += other.content;
        structure += other.structure;
        only += other.only;
    }
}
