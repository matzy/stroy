package org.openCage.stroy.algo.fingerprint;

import org.openCage.stroy.algo.tree.IOState;

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
 * A fingerprint is related to checksum and hash.
 * The point here is that a fingerprint is good enough to identify
 * an object, i.e. it is stronger than a checksum.
 * http://wikipedia ... 
 */
public interface FingerPrint<T> {

    public String getFingerprint( T obj, IOState state );
}