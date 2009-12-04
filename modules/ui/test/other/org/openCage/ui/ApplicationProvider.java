package other.org.openCage.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;

import java.io.File;

public class ApplicationProvider implements Provider<Application > {

	@Inject
	private ApplicationFromConfig fromConfig;

	public Application get() {

		return fromConfig.get( getClass().getResource( "TestApp.xml" ),
				               getClass().getResource( "bogen1.png"));
	}
}
