package org.openCage.stroy.dir;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.ContentTreeNodeBaseImpl;
import org.openCage.stroy.content.FileContentFactory;

import java.io.File;
import java.util.List;
import java.util.Collection;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/
public class DirTreeNodeImpl extends ContentTreeNodeBaseImpl<FileContent> implements TreeNode<FileContent> {

    private List<TreeNode<FileContent>> kids;

    public DirTreeNodeImpl( final FileContentFactory factory, final File file, List<TreeNode<FileContent>> kids, boolean generateId  ) {
        super( factory.create(  file ), generateId );
        this.kids   = kids;

        for ( TreeNode<FileContent> kid : this.kids ) {
            kid.setParent( this );
        }

    }


    public Collection<TreeNode<FileContent>> getChildren() {
        return kids;
    }

    public void removeChild(TreeNode<FileContent> child) {
        kids.remove( child );
        child.setParent( null );
    }

    public String toString() {
        return "DirTreeNodeImpl " + getContent();
    }

    public boolean isLeaf() {
        return false;
    }

    public void unsetId() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}