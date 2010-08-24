package org.openCage.artig;

import org.junit.Test;
import org.openCage.artig.stjx.Deployed;

import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class TestResource {
    
    @Test
    public void testResourceLocation() {

        System.out.println( GetDeployed.class.getResource(".") );
        System.out.println( GetDeployed.class.getResource("/deployed.artig") );
        System.out.println( Artig.class.getResource("/deployed.artig") );

        InputStream is = new GetDeployed( Artig.class ).getIS();

        assertNotNull( is );
    }

       @Test
    public void std() {

        Deployed dpl = new GetDeployed( GetDeployed.class ).get();

        assertNotNull( dpl );

        assertEquals( "artig", dpl.getArtifact().getName());
    }
}
