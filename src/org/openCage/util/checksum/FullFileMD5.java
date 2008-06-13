package org.openCage.util.checksum;

import com.twmacinta.util.MD5;
import org.openCage.util.string.Strings;

import java.io.File;
import java.io.IOException;

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

/**
 * Calculates a checksum using the full file
 */
public class FullFileMD5 implements MD5Provider {


    public String getChecksum(File file) throws IOException {
        return Strings.asHex( MD5.getHash( file ));
    }
}
