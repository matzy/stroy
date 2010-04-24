package org.openCage.gpad;

import java.net.URI;

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

/**
 * encode object of type T to a string
 * use a URI as key (i.e. the content of the URI)
 * @param <T>
 */
public interface TextEncoderIdx<T,Z> {

    /**
     * encode a item to a string
     * @param item arbitrary item of type T
     * @param idx  index into the pad file
     * @return
     */
    Z encode( T item, int idx );

    /**
     * decode a string to object of type T
     * @param line crypt input
     * @param idx  index into pad file
     * @return
     */
    T      decode( Z line, int idx );

    /**
     * set the pad file
     * @param path a file or url .. in uri form
     */
    void   setPad( URI path );

    /**
     * is the pad set?
     * @return
     */
    boolean isSet();
}
