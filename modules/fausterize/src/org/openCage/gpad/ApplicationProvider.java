package org.openCage.gpad;

import com.google.inject.Inject;
import com.google.inject.Provider;

import java.io.File;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ApplicationProvider implements Provider<Application > {

	@Inject
	private ApplicationFromConfig fromConfig;

	public Application get() {

		return fromConfig.get( new File( getClass().getResource( "FausterizeApp.xml" ).getPath()),
				               getClass().getResource( "stroy.png"));
	}
}
