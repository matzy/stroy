package other.org.openCage.ui;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.openCage.ui.protocol.AboutSheet;


public class AboutSheetTest {

	@Test
	public void AboutTest() {
        Injector injector = Guice.createInjector( new TestWiring() );

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
