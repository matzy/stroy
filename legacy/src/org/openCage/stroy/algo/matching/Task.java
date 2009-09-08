package org.openCage.stroy.algo.matching;

import org.openCage.stroy.matching.EdgeAttributes;
import org.openCage.util.lang.P2;
import org.openCage.utils.func.F2;

import java.util.Collection;

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

public interface Task<T> {
    public void addLeft( T obj );
    public void addRight( T obj );
    public void remove( T obj );

    public Collection<T> getLeft( F2<Boolean, Task<T>, T> filter );
    public Collection<T> getRight( F2<Boolean, Task<T>, T> filter );

    public boolean isMatched( T obj );
    public T       getMatch( T obj );

    public void match( T left, T right  );
    public void breakMatch( T obj );

//    public void addNodeChangeListener( NodeChangeListener listener );
//    public void removeNodeChangeListener( NodeChangeListener listener );
//
//

    public EdgeAttributes getEdgeAttributes( T node );
    
}
