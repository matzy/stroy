//package org.openCage.test;
//
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;
//import org.openCage.test.other.NotSimple;
//
///**
// * Test how xstream persisted classes could be refactored without destroying
// * backward compatibility
// */
//public class XStreamTest {
//    public static void main(String[] args) {
//
//        String inbetween;
//
//        {
//            XStream xstream = new XStream();
//            xstream.processAnnotations(Simple.class);
//
//            Simple simple = new Simple( "grdh");
//            inbetween = xstream.toXML(simple);
//            System.out.println(inbetween);
//        }
//
//        {
//            XStream xstream = new XStream(new DomDriver());
//            xstream.processAnnotations(NotSimple.class);
//
//            Object oo = xstream.fromXML( inbetween );
//
//            System.out.println(oo );
//        }
//    }
//}
