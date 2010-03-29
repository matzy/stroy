package org.openCage.property.protocol;

import org.openCage.lang.protocol.F1;

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
 * A property in the sense of a per application singleton
 * possible persisted
 * The intended use is for small objects
 * TODO: tie in property updates messages
 * @param <T>
 */
public interface Property<T> {

    /**
     * return the wrapped object
     * expects no modification of that object (by convention)
     * @return the property value
     */
    public T    get();


    /**
     * set object to its default value
     */
    public void setDefault();

    /**
     * modify the object, i.e. call modi on the object and mark the property as changed afterwards
     * @param modi the modification method
     */
    public void modify( F1<T,T> modi );
}
