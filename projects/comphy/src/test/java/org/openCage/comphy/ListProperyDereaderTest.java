//package org.openCage.comphy;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.openCage.comphy.DereaderUtil.deread;
//
///**
// * Created with IntelliJ IDEA.
// * User: stephan
// * Date: 5/30/12
// * Time: 3:46 PM
// * To change this template use File | Settings | File Templates.
// */
//public class ListProperyDereaderTest {
//
//    @Test
//    public void testSimple() {
//        RList rl = new RList( Key.valueOf("elems" ));
//        rl.add( RString.valueOf("one"));
//        rl.add( RString.valueOf("two"));
//        rl.add( RString.valueOf("three"));
//
//        ListProperty<StringProperty> prop = deread( new ListPropertyDereader<StringProperty>( new StringPropertyDereader()), rl );
//        assertNotNull( prop );
//
//        assertEquals( "two", prop.get(1).get());
//
//    }
//}
