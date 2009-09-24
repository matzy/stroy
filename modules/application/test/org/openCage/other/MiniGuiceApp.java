package org.openCage.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.openCage.application.impl.pojos.ApplicationByBuilder;
import org.openCage.application.impl.pojos.AuthorImpl;
import org.openCage.application.impl.pojos.ContactImpl;
import org.openCage.application.impl.pojos.LicenceImpl;
import org.openCage.application.impl.pojos.VersionImpl;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationBuilder;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.Version;
import org.openCage.application.wiring.ApplicationWiring;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class MiniGuiceApp {

	@Test
	public void testBuilder() {
        Injector injector = Guice.createInjector( new ApplicationWiring() );

        ApplicationBuilder ab = injector.getInstance( ApplicationBuilder.class );
        
        Application app = ab.with( ab.author().name( "me" ).build()).
        				     with( ab.author().name( "you" ).build()).
        				     name( "TestApp" ).
        				     build();

		assertEquals( 2, app.getAuthors().size());
		
		for ( Author author : app.getAuthors() ) {
			assertTrue( author.getName().equals( "me") || author.getName().equals( "you"));  
		}
        assertEquals( "TestApp", app.getName() );
	}
	
	@Test
	public void testUse() {
        Injector injector = Guice.createInjector( new Wiring() );
		
        Application app = injector.getInstance( Application.class );

        assertEquals( 2, app.getAuthors().size());
		
		for ( Author author : app.getAuthors() ) {
			assertTrue( author.getName().equals( "me") || author.getName().equals( "you"));  
		}
        assertEquals( "TestApp", app.getName() );
	}
	
//	@Test
//	public void testXML() {
//        String path = getClass().getResource(".").getPath();
//        String path2 = getClass().getResource("Testing.xml").getPath();
//        
//        boolean ex = new File( path2 ).exists();
//        
//
//        Injector injector = Guice.createInjector( new Wiring() );
//		
//
//        ApplicationFromConfig appConf = injector.getInstance(ApplicationFromConfig.class );
//        
//
//        Application app = appConf.get( new File("/Users/stephan/Documents/stroy-ng/modules/application/test/org/openCage/other/TestApp.xml"));
//        assertEquals( 2, app.getAuthors().size());
//		
//		for ( Author author : app.getAuthors() ) {
//			assertTrue( author.getName().equals( "me") || author.getName().equals( "you"));  
//		}
//        assertEquals( "TestApp", app.getName() );
//	}
	
	@Test
	public void testXMLout() throws URISyntaxException {
		List<AuthorImpl> au = new ArrayList<AuthorImpl>();
		au.add(new AuthorImpl("au", null ));
		ApplicationByBuilder app = new ApplicationByBuilder( 
				"foo", 
				au,  
				new VersionImpl( 0,1,2,3 ),
				new LicenceImpl("MPL 1.1"),
				null,
				new ContactImpl( new URI("mailto:foo"), new URI("page")));
		
		app.validate();
		
		XStream xs = new XStream( new DomDriver());
		xs.alias("Application", ApplicationByBuilder.class);
		xs.alias("Author", AuthorImpl.class );
		xs.alias("Version", VersionImpl.class );
		xs.alias("Licence", LicenceImpl.class );
		xs.alias("Contact", ContactImpl.class );
		xs.alias( "URI", URI.class );

		
		System.out.println( xs.toXML( app ));
	}
	
}
