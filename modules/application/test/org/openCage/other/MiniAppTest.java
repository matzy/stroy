package org.openCage.other;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openCage.application.impl.pojos.ApplicationImpl;
import org.openCage.application.impl.pojos.AuthorImpl;
import org.openCage.application.protocol.Application;


public class MiniAppTest {
	
	
	@Test
	public void testSimple() {
		Application app = new ApplicationImpl( "TestApp" ).with( new AuthorImpl("me", null));
		
		assertEquals( "TestApp", app.getName() );
		assertEquals( 1, app.getAuthors().size());
		assertEquals("me", app.getAuthors().iterator().next().getName() );
		
	}
	
	
}
