package org.openCage.other;

import java.io.File;
import java.net.URL;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class AppProviderXML implements Provider<Application>{

	@Inject
	private ApplicationFromConfig appFromConfig;
	   
	public Application get() {

		String xmlpath = getClass().getResource("TestApp.xml").getPath();
		URL iconpath = getClass().getResource("bogen1.jpg");
        
        boolean ex = new File( xmlpath ).exists();
//        ex = new File( iconpath ).exists();
        

        return appFromConfig.get( new File( xmlpath ), iconpath );
	}

}
