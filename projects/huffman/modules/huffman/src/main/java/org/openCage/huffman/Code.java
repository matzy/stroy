package org.openCage.huffman;

/**
 * Created by IntelliJ IDEA.
 * User: spf
 * Date: 28.04.11
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
public interface Code extends Iterable<Code>{

    public BitField get( BitField from );
}
