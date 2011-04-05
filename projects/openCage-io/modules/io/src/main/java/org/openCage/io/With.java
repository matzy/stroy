package org.openCage.io;

import java.io.*;

import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.F1;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.FE1;

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
***** END LICENSE BLOCK *****/


public class With {

    public <T, C extends Closeable> T with( C stream, F1<T,C> func ) {
        try {
            if ( stream != null ) {
                return func.call( stream );
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        } finally {
            if ( stream != null ) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
    }

//    public static <T, C extends Closeable> T with( FE1<T,Ref<C>> func ) {
//        Ref<C> ref = new Ref<C>(null);
//
//        try {
//            return func.call( ref );
//        } catch (Exception e) {
//            throw new UndeclaredThrowableException(e);
//        } finally {
//            IOUtils.closeQuietly( ref.get());
//        }
//    }


    public static <T> T with( FE1<T, Resource> func ) {
        Resource resource = new Resource();

        try {
            return func.call( resource );
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        } finally {
            resource.unwind();
        }
    }

    public <T,C extends Closeable> T with( F0<C> getStream, F1<T,C> func ) {
        C stream = null;

        try {
            stream = getStream.call();
            return func.call( stream );
        } catch (Exception e) {
            throw new UndeclaredThrowableException( e );
        } finally {
            IOUtils.closeQuietly( stream );
        }

    }

    public <T> T withInputStream(File file, F1<T, InputStream> reader) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withInputStream( URI file, F1<T, InputStream> reader) {
        InputStream is = null;
        try {

            is = new BufferedInputStream( file.toURL().openStream());
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withReader(File file, F1<T, Reader> reader) {
        Reader is = null;
        try {
            is = new FileReader( file );
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withWriter(File file, F1<T, Writer> writer) {
        Writer is = null;
        try {
            is = new FileWriter( file );
            return writer.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

//    public FileLineIterable getLineIteratorCloseInFinal(File file) {
//        return new IterableFile( file );
//    }

    public void close( InputStream is ) {
        if ( is != null ) {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(With.class.getName()).log(Level.SEVERE, null, ex);
            }

            is = null;
        }
    }



}
