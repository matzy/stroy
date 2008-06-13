package org.openCage.stroy.dir;

import org.openCage.stroy.content.FileContentFactory;
import org.openCage.stroy.graph.node.ContentTreeNodeBaseImpl;
import org.openCage.stroy.graph.node.TreeLeafNode;

import java.io.File;

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

public class LeafTreeNodeImpl extends ContentTreeNodeBaseImpl<FileContent> implements TreeLeafNode<FileContent> {

    private       Object        storage;

    public LeafTreeNodeImpl( final FileContentFactory factory, final File file, final boolean generateId ) {
        super( factory.create(  file ), generateId );
    }


    public void store(final Object obj) {
        storage = obj;
    }

    public Object get() {
        return storage;
    }

    public String toString() {
        return "Leaf: " + getContent();
    }

    public boolean isLeaf() {
        return true;
    }

}
