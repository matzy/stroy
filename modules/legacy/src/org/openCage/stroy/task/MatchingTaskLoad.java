//package org.openCage.stroy.task;
//
//import org.openCage.stroy.content.Content;
//import org.openCage.stroy.diff.ContentDiff;
//import org.openCage.stroy.graph.node.TreeNode;
//import org.openCage.util.lang.Once;
//
//import java.util.Collection;
//
///***** BEGIN LICENSE BLOCK *****
// * Version: MPL 1.1
// *
// * The contents of this file are subject to the Mozilla Public License Version
// * 1.1 (the "License"); you may not use this file except in compliance with
// * the License. You may obtain a copy of the License at
// * http://www.mozilla.org/MPL/
// *
// * Software distributed under the License is distributed on an "AS IS" basis,
// * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
// * for the specific language governing rights and limitations under the
// * License.
// *
// * The Original Code is stroy code.
// *
// * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
// * Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
// * All Rights Reserved.
// *
// * Contributor(s):
// ***** END LICENSE BLOCK *****/
//
//public class MatchingTaskLoad implements MatchingTask<TreeNode>{
//
//
//    private final Once<TreeNode> leftRoot  = new Once<TreeNode>();
//    private final Once<TreeNode> rightRoot = new Once<TreeNode>();
//
//    private Map<T, Quality> left2Right   = new HashMap<T, Quality>();
//    private Map<T, T>       right2Left   = new HashMap<T, T>();
//
//
//    public void addLeft(TreeNode obj) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void addRight(TreeNode obj) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public Collection<TreeNode> getUnmatchedLeft() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public Collection<TreeNode> getUnmatchedRight() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public Collection<TreeNode> getMatchedLeft() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public Collection<TreeNode> getMatchedRight() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public boolean isMatched(TreeNode obj) {
//        return false;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public TreeNode getMatch(TreeNode obj) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void match(TreeNode src, TreeNode tgt, double quality) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void breakMatch(TreeNode src) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public TreeNode getLeftRoot() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public TreeNode getRightRoot() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void setRoots(TreeNode src, TreeNode tgt) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void remove(TreeNode node) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public double getMatchQuality(TreeNode obj) {
//        return 0;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void addNodeChangeListener(NodeChangeListener listener) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void removeNodeChangeListener(NodeChangeListener listener) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public ContentDiff getDifference(TreeNode obj) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void setDifference(TreeNode obj, ContentDiff payload) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//}
