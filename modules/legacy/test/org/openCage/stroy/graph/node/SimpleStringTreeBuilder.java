/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.stroy.graph.node;

import java.io.File;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.stroy.content.Content;

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

        public FuzzyHash getFuzzyHash() {
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
