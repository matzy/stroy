package org.openCage.huffman;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Nov 10, 2009
 * Time: 11:37:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class HNode {
    public final int character;
    public final HNode left;
    public final HNode right;
    public final int weight;

    public HNode( int ch, HNode left, HNode right, int weight ) {
        this.character = ch;
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    @Override
    public String toString() {
        if ( character > -127 && character < 127 ) {
            return "(" + (char)character + " " + left + " " + right + " " + weight + ")";
        }
        return "(" + character + " " + left + " " + right + " " + weight + ")";
    }
}
