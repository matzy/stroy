package org.openCage.ui.protocol;

import java.awt.Frame;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public interface FileChooser {

    /**
     * open a file filebrowser and allow directory selection only
     * @param fr A swing frame
     * @param path A path to start from.
     * @return A valid directory or null
     */
    public String getDir( Frame fr, String path );

    /**
     * return a path to a file via the os native dialog.
     * @param fr A swing frame
     * @param path
     * @return A valid filepath or null
     */
    public String open( Frame fr, String path );

    /**
     * return a path to a file via the os native dialog. (saveas title)
     * @param fr A swing frame
     * @param path
     * @return A valid filepath or null
     */
    public String saveas( Frame fr, String path );




}
