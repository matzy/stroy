package org.openCage.other;

import java.io.File;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class AppProviderXML implements Provider<Application>{

	@Inject
	private ApplicationFromConfig appFromConfig;
	
	public Application get() {

		String xmlpath = getClass().getResource("TestApp.xml").getPath();
        
        boolean ex = new File( xmlpath ).exists();
        

        return appFromConfig.get( new File( xmlpath ));
	}

}
