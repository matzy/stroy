package org.openCage.other;

import org.junit.Test;
import org.openCage.application.protocol.AboutSheet;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class UITest {

	@Test
	public void AboutTest() {
        Injector injector = Guice.createInjector( new UIWiring() );

		AboutSheet about = injector.getInstance( AboutSheet.class );
		
		about.setVisible( true );
		
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
