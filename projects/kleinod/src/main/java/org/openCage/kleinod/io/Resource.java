package org.openCage.kleinod.io;

import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.VF1;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 **** END LICENSE BLOCK *****/

public class Resource {

    /**
     * tryWith remembers resources used inside the function object func and closed them after func returned.
     * <code>
     * Integer ii = tryWith( new FE1<Integer, Resource> (){
     *     public Integer call( Resource res ) {
     *          InputStream is = res.add( new FileInputStream("doo"));
     *          ...
     *     }
     * }
     * </code>
     * @param func A function throwing any exception
     * @param <T>  The return type
     * @return The result of the fuction
     */
    public static <T> T tryWith( F1<T, Resource> func ) {
        Resource resource = new Resource();

        try {
            return func.call( resource );
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        } finally {
            resource.unwind();
        }
    }

    public static void tryWith( VF1<Resource> func ) {
        Resource resource = new Resource();

        try {
            func.call( resource );
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        } finally {
            resource.unwind();
        }
    }


    private final List<Closeable> resources = new ArrayList<Closeable>();

    public <T extends Closeable> T add( T t) {
        resources.add( t );
        return t;
    }

    public void unwind() {
        for ( Closeable cl : resources ) {
            try {
                if ( cl != null ) {
                    cl.close();
                }
            } catch (IOException e) {
                // silent
            }
        }
    }
}
