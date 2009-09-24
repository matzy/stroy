package org.openCage.application.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

import org.openCage.application.impl.pojos.ApplicationByBuilder;
import org.openCage.application.impl.pojos.ApplicationImpl;
import org.openCage.application.impl.pojos.AuthorImpl;
import org.openCage.application.impl.pojos.ContactImpl;
import org.openCage.application.impl.pojos.VersionImpl;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.application.protocol.Contact;
import org.openCage.withResource.protocol.Reader;
import org.openCage.withResource.protocol.ReaderException;
import org.openCage.withResource.protocol.With;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ApplicationFromConfigXStream implements ApplicationFromConfig {

	@Inject
	private With with;

	public Application get( File path, final URL iconPath ) {
		
		return with.withOpenStreamQuiet( path , new Reader<Application, InputStream>() {

				public Application read(InputStream stream) throws ReaderException {
					
					XStream xs = new XStream( new DomDriver());
					xs.alias( "Application", ApplicationByBuilder.class);
					xs.alias( "Author", AuthorImpl.class );
					xs.alias( "Version", VersionImpl.class );
					xs.alias( "Contact", ContactImpl.class );
					ApplicationByBuilder app = (ApplicationByBuilder)xs.fromXML( stream );
					
					if ( iconPath != null ) {
						app.setIcon( new ImageIcon( iconPath ));
					}
					return app;
				}
			});

	}
}
