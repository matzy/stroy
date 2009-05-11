//package org.openCage.lang;
//
//import org.junit.Test;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//public class MaybeTest {
//
//    @Test
//    public void testNo() {
//        assertFalse( Maybe.no().is );
//    }
//
//    @Test
//    public void testYes() {
//        assertEquals( "duu", Maybe.yes( "duu").get() );
//    }
//
//    @Test
//    public void testNoAccess() {
//        try {
//            Maybe.no().get();
//
//        } catch ( IllegalStateException e ) {
//            // expected
//        }
//    }
//
//    @Test
//    public void testUseCase() {
//        Maybe<String> foo = Maybe.no();
//
//        if ( foo.is ) {
//            fail( "nop" );
//        }
//
//        foo = Maybe.yes( "duh" );
//
//        if ( foo.is ) {
//            assertEquals( "duh", foo.get() );
//        }
//    }
//
//    public interface Ret {
//        public void foo();
//    }
//
//    public class RetImpl implements Ret {
//
//        public void foo() {
//            //To change body of implemented methods use File | Settings | File Templates.
//        }
//    }
//
//
//    @Test
//    public void testRet() {
//        Maybe<? extends Ret> ret = Maybe.yes( new RetImpl() );
//
//        if ( ret.is ) {
//            ret.get().foo();
//        }
//    }
//}
