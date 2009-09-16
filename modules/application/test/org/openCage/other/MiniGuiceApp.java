package org.openCage.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationBuilder;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.application.protocol.Author;
import org.openCage.application.wiring.ApplicationWiring;

import com.google.inject.Guice;
import com.google.inject.Injector;


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
	
	@Test
	public void testXML() {
        String path = getClass().getResource(".").getPath();
        String path2 = getClass().getResource("Testing.xml").getPath();
        
        boolean ex = new File( path2 ).exists();
        

        Injector injector = Guice.createInjector( new Wiring() );
		

        ApplicationFromConfig appConf = injector.getInstance(ApplicationFromConfig.class );
        

        Application app = appConf.get( new File("/Users/stephan/Documents/stroy-ng/modules/application/test/org/openCage/other/TestApp.xml"));
        assertEquals( 2, app.getAuthors().size());
		
		for ( Author author : app.getAuthors() ) {
			assertTrue( author.getName().equals( "me") || author.getName().equals( "you"));  
		}
        assertEquals( "TestApp", app.getName() );
	}
	
}
