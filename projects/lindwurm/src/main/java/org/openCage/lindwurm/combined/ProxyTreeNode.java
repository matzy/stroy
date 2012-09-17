package org.openCage.lindwurm.combined;

import org.openCage.lindwurm.SimpleTreeNode;
import org.openCage.lindwurm.content.Content;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/7/12
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProxyTreeNode extends SimpleTreeNode {

//    private List<LindenNode> to;
//    private LindenDirNode parent = Null.of( LindenDirNode.class );
//
//    @Override
//    public boolean isLeaf() {
//        return primary.isLeaf();
//    }
//
//    @Override
//    public LindenDirNode getParent() {
//        return parent;
//    }
//
//    @Override
//    public Content getContent() {
//        return primary.getContent();
//    }
//
//    @Override
//    public LindenDirNode dir() {
//        throw new IllegalArgumentException("not a LindenDirNode " + this );
//    }

    public ProxyTreeNode(Content t) {
        super(t);
    }
}
