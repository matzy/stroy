package org.openCage.stroy.ui;

import org.openCage.stroy.graph.node.TreeNode;

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

public class NodeReport<T> {

    public TreeNode<T>  node;
    public boolean      matched;
    // public boolean      deleted;
    public String       message;

    public NodeReport( TreeNode<T>  node ) {
        this.node = node;
    }

    public NodeReport( String message ) {
        this.message = message;
    }

    public NodeReport message( String mes ) {
        this.message = mes;
        return this;
    }

    public NodeReport node( TreeNode<T>  node ) {
        this.node = node;
        return this;
    }
}
