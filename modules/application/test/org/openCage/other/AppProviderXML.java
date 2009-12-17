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

		URL xmlpath = getClass().getResource("TestApp.xml");
		URL iconpath = getClass().getResource("bogen1.jpg");
        
        boolean ex = new File( xmlpath.toString() ).exists();
//        ex = new File( iconpath ).exists();
        

        return appFromConfig.get( xmlpath, iconpath, null );
	}

}
