package org.openCage.huffman;

import org.junit.Assert;
import org.junit.Test;
import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.F2;
import org.openCage.lang.structure.T2;

import java.nio.charset.Charset;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: spf
 * Date: 28.04.11
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class BitFieldImplTest {

    @Test
    public void testAppend() {
        BitFieldImpl bf = BitFieldImpl.valueOf(false);
        assertEquals( 0, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 1, bf.size());
        assertFalse( bf.get(0));

        bf.append( true );
        assertEquals( 1, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 2, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));

        bf.append( false );
        assertEquals( 2, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 3, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));

        bf.append( false );
        assertEquals( 3, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 4, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));
        assertFalse( bf.get(3));

        bf.append( true );
        assertEquals( 4, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 5, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));
        assertFalse( bf.get(3));
        assertTrue( bf.get(4));

        bf.append( true );
        assertEquals( 5, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 6, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));
        assertFalse( bf.get(3));
        assertTrue( bf.get(4));
        assertTrue( bf.get(5));

        bf.append( true );
        assertEquals( 6, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 7, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));
        assertFalse( bf.get(3));
        assertTrue( bf.get(4));
        assertTrue( bf.get(5));
        assertTrue( bf.get(6));


        bf.append( false );
        assertEquals( 7, bf.internalGetLast());
        assertEquals( 1, bf.internalGetBytes().size());
        assertEquals( 8, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));
        assertFalse( bf.get(3));
        assertTrue( bf.get(4));
        assertTrue( bf.get(5));
        assertTrue( bf.get(6));
        assertFalse( bf.get(7));


        bf.append( true );
        assertEquals( 0, bf.internalGetLast());
        assertEquals( 2, bf.internalGetBytes().size());
        assertEquals( 9, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));
        assertFalse( bf.get(3));
        assertTrue( bf.get(4));
        assertTrue( bf.get(5));
        assertTrue( bf.get(6));
        assertFalse( bf.get(7));
        assertTrue( bf.get(8));

        bf.append( false );
        assertEquals( 1, bf.internalGetLast());
        assertEquals(2, bf.internalGetBytes().size());
        assertEquals( 10, bf.size());
        assertFalse( bf.get(0));
        assertTrue( bf.get(1));
        assertFalse( bf.get(2));
        assertFalse( bf.get(3));
        assertTrue( bf.get(4));
        assertTrue( bf.get(5));
        assertTrue( bf.get(6));
        assertFalse( bf.get(7));
        assertTrue( bf.get(8));
        assertFalse( bf.get(9));

    }

    @Test
    public void testString() {
        BitField a = BitFieldImpl.valueOf( "a".getBytes(Charset.forName("utf8")));

        assertEquals( 8, a.size() );
        System.out.println(a);

        BitField b = BitFieldImpl.valueOf( "b".getBytes(Charset.forName("utf8")));

        assertEquals(8, b.size());
        System.out.println(b);


        for ( int i = 0; i < a.size(); i++ ) {
            System.out.println( "a.get(" + i + ")= " + a.get(i));
        }

        assertFalse(a.get(7));
        assertTrue(a.get(6));
        assertTrue(a.get(5));
        assertFalse(a.get(4));
        assertFalse(a.get(3));
        assertFalse(a.get(2));
        assertFalse(a.get(1));
        assertTrue(a.get(0));

        assertTrue(0 > a.compareTo(b));


    }

    @Test
    public void testPlusOne() {
        BitField bf = BitFieldImpl.valueOf( "100111" );

        assertEquals( "101000", bf.clonePlusOne().toString());

        assertEquals( "100", BitFieldImpl.valueOf( "11" ).clonePlusOne().toString());
        assertEquals( "0100", BitFieldImpl.valueOf( "0011" ).clonePlusOne().toString());

        BitField count = BitFieldImpl.valueOf(false);
        for ( int i = 0; i < 10; i++ ) {
            System.out.println(count);
            count = count.clonePlusOne();
        }
    }


    @Test
    public void testToString() {
        assertEquals( "100111", BitFieldImpl.valueOf( "100111" ).toString());


        assertEquals( "100111", BitFieldImpl.valueOf( "10011" ).append(true).toString());
    }

    @Test
    public void testGetInt() {
        assertEquals( 5, BitFieldImpl.valueOf((byte)5).getInt(0,3));

        assertEquals( 8, BitFieldImpl.valueOf("100").getInt(0,2));

        //assertEquals( 100, BitFieldImpl.valueOf((byte)100).getInt(0,7));
        System.out.println(BitFieldImpl.valueOf((byte)100));
        System.out.println(BitFieldImpl.valueOf((byte)100).append( BitFieldImpl.valueOf( "00")));
        assertEquals( 400, BitFieldImpl.valueOf((byte)100).append( BitFieldImpl.valueOf( "00")).getInt(0,9));


    }

    @Test
    public void testBitOrder() {
        BitField one = BitFieldImpl.valueOf((byte)1);
        BitField two = BitFieldImpl.valueOf((byte)2);

        System.out.println("one " + one );
        System.out.println("two " + two );

        assertTrue(one.compareTo(two) < 0);

    }

    public <A,B> void with( T2<A,B> res, F2<Void, A,B> do_) {
        do_.call( res.i0, res.i1 );
    }

    @Test
    public void testWith() {
        with( T2.valueOf( 5, 7), new F2<Void, Integer, Integer>() {
            @Override public Void call(Integer a, Integer b) {
                System.out.println("simulating multiple return processing with pairs (" + a + ", " + b + " )");
                return null;
            }
        });
    }

    public static <A,B> void foreach( Iterable<A> it1, Iterable<B> it2, F2<Void, A, B> body ) {

    }
}
