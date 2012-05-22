package org.openCage.io;

import org.junit.Test;
import org.openCage.lang.functions.FE1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.UndeclaredThrowableException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.openCage.io.Resource.tryWith;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 26.11.10
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class ResourceTest {

    @Test( expected = UndeclaredThrowableException.class )
    public void testNoFile() {
        tryWith( new FE1<Object, Resource>() { @Override public Object call(Resource resource) throws Exception {

                @SuppressWarnings({"UnusedAssignment"})
                InputStream is = resource.add( new FileInputStream( "no such file" ));
                fail( "wooot" );
                return null;
            }
        });
    }

    @Test
    public void testClosed2() {

        final InputStream is = getClass().getResourceAsStream( "ResourceTest.class" );
        assertNotNull(is);
        final InputStream is2 = getClass().getResourceAsStream( "Resource.class" );
        assertNotNull(is);

        tryWith( new FE1<Object, Resource>() { @Override public Object call(Resource resource) throws Exception {

                resource.add(is);
                resource.add(is2);
                return null;
            }
        });

        try {
            is.read();
            fail( "should be closed already" );
        } catch (IOException e) {
        }
        try {
            is2.read();
            fail( "should be closed already" );
        } catch (IOException e) {
        }
    }

    @Test
    public void testNull() {
        tryWith( new FE1<Object, Resource>() {
            @Override
            public Object call(Resource resource) throws Exception {
                // null is to be tested here
                //noinspection NullableProblems
                resource.add(null);
                return null;
            }
        });
    }


}
