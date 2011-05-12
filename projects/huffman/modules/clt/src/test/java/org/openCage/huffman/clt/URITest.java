//package org.openCage.huffman.clt;
//
//import org.junit.Test;
//
//import static junit.framework.Assert.assertEquals;
//
///**
// * Created by IntelliJ IDEA.
// * User: SPF
// * Date: 11.05.11
// * Time: 17:46
// * To change this template use File | Settings | File Templates.
// */
//public class URITest {
//
//    @Test
//    public void testFromURI() {
//        assertEquals( "http://foo.org", Main.getURI("http://foo.org").toString());
//    }
//
//
//    @Test
//    public void testFromFile() {
//        assertEquals( "file://C:/duh", Main.getURI("C:\\tmp").toString());
//    }
//
//    @Test( expected = IllegalArgumentException.class )
//    public void testNotExists() {
//        Main.getURI( "/dev/flupdisch");
//    }
//}
