package org.openCage.stroy.app;

import java.io.File;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ApplicationProvider implements Provider<Application >{

	@Inject
	private ApplicationFromConfig fromConfig;
	
	public Application get() {
		
		String pathToXmlFile = getClass().getResource( "StroyApp.xml" ).getPath();
		
		return fromConfig.get( new File( pathToXmlFile ));
	}

}
