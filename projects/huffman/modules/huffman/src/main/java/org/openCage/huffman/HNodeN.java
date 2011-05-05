package org.openCage.huffman;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 08.04.11
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
public class HNodeN implements Comparable<HNodeN>{
    private BitField ch;
    private int weight;
    private HNodeN left;
    private HNodeN right;

    public HNodeN(BitField key, int count ) {
        this.ch = key;
        this.weight = count;
    }

    public HNodeN(BitField key, int count, HNodeN left, HNodeN right  ) {
        this( key, count);
        this.left = left;
        this.right = right;
    }

    public BitField getCh() {
        return ch;
    }

    public int getWeight() {
        return weight;
    }

    public HNodeN getLeft() {
        return left;
    }

    public HNodeN getRight() {
        return right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HNodeN hNodeN) {
        return weight - hNodeN.getWeight();
    }

    public void setRight(HNodeN hNodeN) {
        right = hNodeN;
    }

    public void setLeft(HNodeN hNodeN) {
        left = hNodeN;
    }

    public void setCh(BitField key) {
        ch = key;
    }
}
