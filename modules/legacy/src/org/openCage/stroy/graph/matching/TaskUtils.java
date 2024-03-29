package org.openCage.stroy.graph.matching;

import org.openCage.vfs.protocol.Content;
import org.openCage.vfs.protocol.VNode;

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

public class TaskUtils {
    public static VNode getBestMatchOrParent( TreeMatchingTask matching,
                                                                        VNode         node ) {
        VNode match = matching.getMatch( node );

        while ( match == null ) {
            node  = node.getParent();
            match = matching.getMatch( node );
        }

        return match;
    }

    public static VNode getMatchOr( TreeMatchingTask matching,
                                                              VNode         node ) {
        VNode match = matching.getMatch( node );

        if ( match == null ) {
            return node;
        }
        return match;
    }

}
