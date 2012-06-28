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

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

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
