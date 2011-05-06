package org.openCage.huffman;

import org.junit.Test;
import org.openCage.lang.structure.T2;

import java.io.InputStream;
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

        Map<BitField, BitField> out = can.readCode(ww).i0;
        HuffmanN.printCodes( can.readCode(ww).i0);

        assertEquals( code.i0, out );
    }

    @Test
    public void testCanonical() {
        String src = "aaaaabcabbbbeeeaf";
        BitField dba = BitFieldImpl.valueOf( src.getBytes(Charset.forName("utf8")));

        for ( int len = 2; len < 17; len++ ) {

            System.out.println(" ---------------------------- " + len + " ---------------------------- ");

            T2<Map<BitField, BitField>, Integer> code = Canonical.canonisize(new HuffmanN(dba).getCode(len));
            BitField ww = Canonical.writeCode( code.i0, (byte)code.i1.intValue() );

            Map<BitField, BitField> out = Canonical.readCode(ww).i0;

            assertEquals( code.i0, out );
        }
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
            res.trimTo( bsrc.size());
            System.out.println(bsrc);
            System.out.println(res.toString());

            assertEquals("len is " + len, bsrc, res);

        }

    }

    @Test
    public void testEncodeDecodeWithCanonical () {
        String src = "aaaaabcabbbbeeeaf";
        BitField bsrc = BitFieldImpl.valueOf( src.getBytes(Charset.forName("utf8")));

        HuffmanN hn = new HuffmanN( bsrc );

        for ( int len = 2; len < 16; len++ ) {
            if ( len == 9 ) {
                continue;
            }
            Map<BitField,BitField> code1 = hn.getCode(len);
            Map<BitField,BitField> codeC = Canonical.canonisize(code1).i0;

            BitField encoded = hn.encode(codeC);
            System.out.println( "encoded to " + encoded.size());

            BitField res =  hn.decode(codeC, hn.encode(codeC));
            res.trimTo( bsrc.size());
            System.out.println(bsrc);
            System.out.println(res.toString());

            assertEquals("len is " + len, bsrc, res);

        }

    }

    @Test
    public void testCompressPic() {
        InputStream is = getClass().getResourceAsStream( "content.jpg");

    }

    @Test
    public void testEncodeDecode2() {
        String src = "aaaaabcabbbbeeeaf";
        BitField bsrc = BitFieldImpl.valueOf( src.getBytes(Charset.forName("utf8")));

        System.out.println( "orig: " + bsrc);

        HuffmanN hn = new HuffmanN( bsrc );

        for ( int len = 2; len < 13; len++ ) {

            System.out.println(" ---------------------- " + len + " ---------------------- " );

            BitField bf = hn.encode( len );
            System.out.println( "coded " + bf );
            System.out.println( "coded " + bf.toString8());
            BitField back = hn.decode( bf);

            System.out.println(bsrc);
            System.out.println(bsrc.toString8());
            System.out.println(back);
            System.out.println(back.toString8());


            assertEquals("len is " + len, bsrc, back);

        }
    }

    public static byte[] toBytes( int by ) {
        byte[] data = new byte[4];

// int -> byte[]
        for (int i = 0; i < 4; ++i) {
            int shift = i << 3; // i * 8
            data[i] = (byte)((by & (0xff << shift)) >>> shift);
        }

        return data;
    }


    @Test
    public void testTT() {
        byte[] bytes = toBytes( 1 );

        assertEquals( 1, bytes[0]);
        assertEquals( 0, bytes[1]);
        assertEquals( 0, bytes[2]);
        assertEquals( 0, bytes[3]);
    }


}
