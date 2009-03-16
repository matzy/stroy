package org.openCage.stroy.mimetype;

import org.junit.Test;
import eu.medsea.mimeutil.MimeType;

import java.util.Collection;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MimeFacadeImplTest {


    private MimeFacade mimes = new MimeFacadeImpl();

    @Test
    public void testJar() {
        Collection<MimeType> types = mimes.getByExtension( "duda.jar" );

        MimeType typ = new MimeType( "application/java-archive" );

        assertEquals( typ, types.iterator().next() );
    }

    @Test
    public void testJavaSource() {
        Collection<MimeType> types = mimes.getByExtension( "duda.java" );

        MimeType typ = new MimeType( "text/x-java-source" );

        assertEquals( typ, types.iterator().next() );
    }

    @Test
    public void testJavaBash() {
        Collection<MimeType> types = mimes.getByExtension( "duda.bash" );

        MimeType typ = new MimeType( "text/x-java-source" );

        assertEquals( typ, types.iterator().next() );
    }

    @Test
    public void testIsText() {
        assertTrue( mimes.isText( Arrays.asList( new MimeType( "text/xml" ))));
        assertTrue( mimes.isText( Arrays.asList( new MimeType( "text/plain" ))));
        assertTrue( mimes.isText( Arrays.asList( new MimeType( "application/x-bash" ), new MimeType( "text/x-scriptbash" ))));
    }
}
