package org.openCage.comphy;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import org.openCage.io.IOUtils;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.VF0;
import org.openCage.lang.inc.GHashMap;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.Str;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.openCage.lang.inc.Strng.S;

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
public class PersistantPropertyStore implements Observer, PropertyStore {

    private GMap<Str,Readable> readableProps;
    private GMap<Str,Property> props = new GHashMap<Str, Property>();
    private final File backingFile;
    private boolean isDirty;
    private final BackgroundExecutor executor;
    private final ReadableToXML readableToXML = new ReadableToXML();
    private final Deread deread = new ToAndFro();


    @Inject
    public PersistantPropertyStore(BackgroundExecutor executor, @Named(value = "PropStoreFile") File backingFile) {
        this.backingFile = backingFile;
        this.executor = executor;

        isDirty = false;

        read();
        createWriter(executor, backingFile, this);


    }

    private void read() {
        XMLtoReadable xmlReader = new XMLtoReadable();
        if ( !backingFile.exists() ) {
            return;
        }
        readableProps = xmlReader.read(backingFile.getAbsolutePath()).getMap();
    }

    private void createWriter(BackgroundExecutor executor, final File backingFile, final PersistantPropertyStore that) {
        executor.addPeriodicAndExitTask( new VF0() {
            @Override
            public void call() {
                if ( isDirty ) {
                    synchronized ( that) {
                        if( readableProps == null ) {
                            readableProps = new GHashMap<Str, Readable>();
                        }

                        for ( Map.Entry<Str,Property> prop : props.entrySet() ) {
                            readableProps.put( prop.getKey(), prop.getValue().toReadable());
                        }

                        FileWriter writer = null;
                        BufferedWriter out = null;
                        try {
                            // make sure the directory exists
                            backingFile.getParentFile().mkdirs();
                            writer = new FileWriter(backingFile);
                            out = new BufferedWriter(writer);
                            System.out.println( readableToXML.write(S("comphy"),readableProps).toString());
                            out.write(readableToXML.write(S("comphy"),readableProps).toString());
                        } catch (IOException e) {
                            throw Unchecked.wrap(e);
                        } finally {
                            IOUtils.closeQuietly(out);
                            IOUtils.closeQuietly(writer);
                        }
                        isDirty = false;
                    }
                }
            }
        });
    }

    public void add( Str key, Property prop ) {
        synchronized (this) {
            prop.getListenerControl().add(this);
            props.put( key, prop );
            isDirty = true;
        }
    }


    public void add( String key, Property prop ) {
        add( S(key), prop );
    }


    public <T extends Property> T get( String key, Class<T> clazz ) {
        return get( S( key ), clazz  );
    }

    @Override
    public <T extends Property> T get(Str key, TypeLiteral<T> typeLiteral) {
        T prop =  (T)props.getG( key );

        if ( prop != null ) {
            return prop;
        }

        if ( readableProps == null ) {
            return null;
        }

        Readable rdlbVal  = readableProps.getG(key);

        if ( rdlbVal == null ) {
            return null;
        }

        T ret = deread.get( typeLiteral, rdlbVal);
        if ( ret == null ) {
            deread.get( typeLiteral, rdlbVal);
            throw new Error("oopsy");
        }
        add( key, ret );
        //        ret.getListenerControl().add( this );

        return ret;
    }


    @Override
    public <T extends Property> T get( Str key, Class<T> clazz ) {

        synchronized ( this ) {
            T prop =  (T)props.getG( key );

            if ( prop != null ) {
                return prop;
            }

            if ( readableProps == null ) {
                return null;
            }

            Readable rdlbVal  = readableProps.getG(key);

            if ( rdlbVal == null ) {
                return null;
            }

            T ret = deread.get( clazz, rdlbVal);
            add( key, ret );
    //        ret.getListenerControl().add( this );

            return ret;
        }
    }

    @Override
    public void call() {
        System.out.println("a change!");
        isDirty = true;
    }

}
