package org.openCage.application.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.openCage.application.impl.pojos.ApplicationByBuilder;
import org.openCage.application.impl.pojos.AuthorImpl;
import org.openCage.application.impl.pojos.VersionImpl;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.withResource.protocol.Reader;
import org.openCage.withResource.protocol.ReaderException;
import org.openCage.withResource.protocol.With;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ApplicationFromConfigXStream implements ApplicationFromConfig {

	@Inject
	private With with;

	public Application get( File path) {
		
		return with.withOpenStreamQuiet( path , new Reader<Application, InputStream>() {

				public Application read(InputStream stream) throws ReaderException {
					
					XStream xs = new XStream( new DomDriver());
					xs.alias("Application", ApplicationByBuilder.class);
					xs.alias("Author", AuthorImpl.class );
					xs.alias("Version", VersionImpl.class );
					return (Application)xs.fromXML( stream );
				}
			});

	}
}
