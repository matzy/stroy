/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.vfs.impl;

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

        private String name;
        private String str;

        public StringContent( String str ) {
            this.str = str;
            this.name = str;
        }

        private StringContent(String name, String content) {
            this.str = content;
            this.name = name;
        }

        public String getName() {
            return str;
        }

        public String getChecksum() {
            return str;
        }

        @Override
        public String toString() {
            if ( str.endsWith(name)) {
                return str + "-";
            }

            return name + " : " + str;
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

    public TreeNode l( String name, String content ) {
        return new SimpleTreeNode( new StringContent(name,content) );
    }

}
