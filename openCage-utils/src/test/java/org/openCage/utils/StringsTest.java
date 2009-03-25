package org.openCage.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openCage.util.string.Strings;

public class StringsTest {

    @Test
    public void testSimple() {
        byte[] bytes = new byte[20];

        for ( byte i = 0; i < 20; ++i ) {
            bytes[i] = i;
        }
        assertEquals( "000102030405060708090a0b0c0d0e0f10111213", Strings.toHexString( bytes ));

        for ( byte i = 0; i < 20; ++i ) {
            bytes[i] = (byte)(i + 108);
        }
        assertEquals( "6c6d6e6f707172737475767778797a7b7c7d7e7f", Strings.toHexString( bytes ));

        for ( byte i = 0; i < 20; ++i ) {
            bytes[i] = (byte)(i - 127);
        }
        assertEquals( "8182838485868788898a8b8c8d8e8f9091929394", Strings.toHexString( bytes ));
    }
}
