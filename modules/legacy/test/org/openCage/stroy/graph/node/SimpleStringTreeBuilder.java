/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.stroy.graph.node;

import org.openCage.vfs.protocol.TreeNode;
import java.io.File;
import org.openCage.lang.protocol.HasDistance;
import org.openCage.vfs.protocol.Content;

/**
 *
 * @author stephan
 */
public class SimpleStringTreeBuilder {

    public class StringContent implements Content {

        private String str;

        public StringContent( String str ) {
            this.str = str;
        }

        public String getName() {
            return str;
        }

        public String getChecksum() {
            return str;
        }

        public HasDistance getFuzzyHash() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getType() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getLocation() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public File getFile() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public TreeNode d( String t, TreeNode ... childs ) {
        return new SimpleTreeNode( new StringContent(t), childs );
    }

    public TreeNode l( String t ) {
        return new SimpleTreeNode( new StringContent(t) );
    }

}
