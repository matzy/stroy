package org.openCage.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openCage.application.impl.GuiceWiring;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationBuilder;
import org.openCage.application.protocol.Author;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class MiniGuiceApp {

	@Test
	public void testBuilder() {
        Injector injector = Guice.createInjector( new GuiceWiring() );

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
}
