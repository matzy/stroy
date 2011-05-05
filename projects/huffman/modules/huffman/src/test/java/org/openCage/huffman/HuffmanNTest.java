package org.openCage.huffman;

import org.junit.Test;
import org.openCage.lang.structure.T2;

import java.nio.charset.Charset;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 08.04.11
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class HuffmanNTest {


    @Test
    public void testA() {
        String src = "aaaaabcabbbbeeeaf";
        BitField dba = BitFieldImpl.valueOf( src.getBytes(Charset.forName("utf8")));

        System.out.println( dba.toString8() );

        HuffmanN hn = new HuffmanN( dba );

        HuffmanN.printCodes( hn.getCode( 8 ));

        System.out.println("-------");

        HuffmanN.printCodes( new Canonical().canonisize( hn.getCode( 8 )).i0);
    }


    @Test
    public void testWriteCanonical() {
        String src = "aaaaabcabbbbeeeaf";
        BitField dba = BitFieldImpl.valueOf( src.getBytes(Charset.forName("utf8")));

        Canonical can = new Canonical();

        T2<Map<BitField, BitField>, Integer> code = can.canonisize(new HuffmanN(dba).getCode(8));
        BitField ww = can.writeCode( code.i0, (byte)code.i1.intValue() );

        System.out.println( code.i1);
        System.out.println(ww.size() / 8);

        System.out.println(ww.toString8());

        System.out.println(" --- ");

        HuffmanN.printCodes( can.readCode(ww));
    }

    @Test
    public void testEncodeDecode() {
        String src = "aaaaabcabbbbeeeaf";
        BitField bsrc = BitFieldImpl.valueOf( src.getBytes(Charset.forName("utf8")));

        HuffmanN hn = new HuffmanN( bsrc );

        System.out.println( bsrc.toString8() );

        System.out.println( hn.encode( hn.getCode(4)).toString8());
        System.out.println( hn.encode( hn.getCode(6)).toString8());
        System.out.println( hn.encode( hn.getCode(7)).toString8());
        System.out.println( hn.encode( hn.getCode(8)).toString8());
        System.out.println( hn.encode( hn.getCode(12)).toString8());

        Map<BitField,BitField> code = hn.getCode(8);

        System.out.println( hn.decode(code, hn.encode(code)).toString8());

        code = hn.getCode(7);

        System.out.println(hn.decode(code, hn.encode(code)).toString8());

        for ( int len = 2; len < 16; len++ ) {
            Map<BitField,BitField> cod = hn.getCode(len);

            BitField res =  hn.decode(code, hn.encode(code));
            System.out.println(bsrc);
            System.out.println(res.toString());

            assertEquals("len is " + len, bsrc, res);

        }

    }

}
