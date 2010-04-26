//package org.openCage.other;
//
//import org.junit.Test;
//
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//import org.openCage.ui.wiring.UIWiring;
//
//public class UpdateInfoTest {
//
//
//	@Test
//	public void testShow() {
//        Injector injector = Guice.createInjector( new UIWiring() );
//
//		UpdateInfo up = injector.getInstance( UpdateInfo.class );
//		up.setLatest( new VersionImpl(1, 2, 3, 4));
//		up.setVisible( true );
//
//		try {
//			Thread.currentThread().sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//}
