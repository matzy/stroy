package org.openCage.io.fspath;

import net.jcip.annotations.Immutable;

import java.io.File;
import java.net.URI;
import java.util.Iterator;

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
 * A file path util, platform independent
 */
@Immutable
public interface FSPath extends Iterable<FSPath> {

    String toString();

    File toFile();

    /**
     * create a new fspath relative to this one
     * @param elements path elements.
     * @return A new FSPath
     */
    FSPath add( String ... elements );

    /**
     * Iterate over the elements of this path
     * @return
     */
    Iterator<FSPath> iterator();

    /**
     * the number of elements
     * @return
     */
    int size();

    URI toURI();

    /**
     * A new FSPath i steps up
     * @param i number of parents to move up
     * @return a new dspath
     */
    FSPath parent(int i);

    FSPath parent();
}
