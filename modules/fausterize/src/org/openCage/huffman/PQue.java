package org.openCage.huffman;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Nov 10, 2009
 * Time: 11:41:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class PQue {

    public ArrayList<HNode> que = new ArrayList<HNode>();

    public int size() {
        return que.size();
    }

    public void push( HNode node ) {
        int idx  = 0;
        for ( HNode elem: que ) {
            if ( elem.weight > node.weight ) {
                que.add( idx, node );
                return;
            }
            idx++;
        }

        que.add( node );
    }

    @Override
    public String toString() {
        String ret = "(";
        for ( HNode node : que  ) {
            ret += node.toString() + " ";
        }
        return ret + ")";
    }

    public HNode pop() {
        HNode top = que.get(0);
        que.remove(0);
        return top;
    }
}
