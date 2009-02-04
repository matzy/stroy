package org.openCage.utils.io.with;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.openCage.utils.lang.unchecked.IOExceptionUC;
import org.openCage.utils.lang.unchecked.FileNotFoundExceptionUC;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;

public class WithIOTestExceptions {

    private Mockery context = new Mockery() {{
        setImposteriser( ClassImposteriser.INSTANCE );
    }};

    @Test
    public void testFileNotFound() {

        final File file = context.mock( File.class );

        context.checking( new Expectations() {{
            oneOf( file ).getPath();
        }});

        try {
            WithIO.withIS( file, new InputStreamFunctor<Object>() {
                public Object c(InputStream is) throws IOException {
                    return null;
                }
            });

            fail( "should throw" );

        } catch ( FileNotFoundExceptionUC exp ) {
            // expected
        }

    }


    /**
     * tests that even after an exception on read the stream is closed
     */
    @Test
    public void testISReadError() {

        final InputStream is = context.mock( InputStream.class );

        context.checking( new Expectations() {{
            try {
                oneOf( is ).read(); will( throwException( new IOExceptionUC( new IOException("foo"))));
                oneOf( is ).close();
            } catch (IOException e) {
                fail( "huh" );
            }
        }});

        try {
            WithIO.withIS( is, new InputStreamFunctor<Object>() {
                public Object c(InputStream is) throws IOException {
                    is.read();
                    return null;
                }
            });

            fail( "should throw" );

        } catch ( IOExceptionUC exp ) {
            // expected
        }

    }
}
