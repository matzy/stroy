//package org.openCage.util.checksum;
//
//import org.junit.Test;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//import java.util.zip.ZipInputStream;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created with IntelliJ IDEA.
// * User: stephan
// * Date: 7/9/12
// * Time: 8:08 AM
// * To change this template use File | Settings | File Templates.
// */
//public class ChecksumTest {
//
//    @Test
//    public void testFile() {
//        InputStream is = getClass().getResourceAsStream( "/org/openCage/util/checksum/somefile.txt" );
//
//        for ( int size = 20; size < 100; size++ ) {
//            assertEquals( "da39a3ee5e6b4b0d3255bfef95601890afd80709", new Sha1(size).getChecksum(is));
//        }
//    }
//
//    @Test
//    public void testZip() throws IOException {
//        ZipInputStream zis = new ZipInputStream( getClass().getResourceAsStream( "/org/openCage/util/checksum/somefile.zip" ));
//
//        ZipEntry ze = zis.getNextEntry();
//
//        for ( int size = 20; size < 100; size++ ) {
//            assertEquals( "da39a3ee5e6b4b0d3255bfef95601890afd80709", new Sha1(size).getChecksum(zis));
//        }
//    }
//}
