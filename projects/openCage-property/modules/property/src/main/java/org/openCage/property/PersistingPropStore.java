package org.openCage.property;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.jetbrains.annotations.NotNull;
import org.openCage.io.IOUtils;
import org.openCage.io.SingletonApp;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.VF0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

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

public class PersistingPropStore implements PropStore {

    private final Map<String,Property> store;
    private final XStream              xs      = new XStream( new DomDriver());
    private boolean                    isDirty = false;

    private static Map<File, PersistingPropStore> instances = new HashMap<File, PersistingPropStore>();

    public PersistingPropStore( @NotNull BackgroundExecutor background, @NotNull final File backing ) {
        this( background, backing, null, null );
    }

    public PersistingPropStore( @NotNull BackgroundExecutor background, @NotNull final File backing, Map<String, Class> aliases, SingletonApp singletonApp ) {

        if ( instances.containsKey( backing )) {
            throw new IllegalStateException( "only one PersistentPropstore per file" );
        }


        if ( backing != null && (singletonApp == null || singletonApp.isMaster()) ) {

            if ( background == null ) {
                throw new IllegalArgumentException( "background executor must be set when persisting" );
            }

            // TODO UGHLY
            xs.alias( "Property", PersistentProp.class );

            if ( aliases != null ) {
                for ( Map.Entry<String, Class> alias : aliases.entrySet() ) {
                    xs.alias( alias.getKey(), alias.getValue());
                }
            }

            final PersistingPropStore propStore = this;

            background.addPeriodicAndExitTask( new VF0() {
                @Override
                public void call() {
                    if ( isDirty ) {
                        synchronized ( propStore ) {
                            FileWriter writer = null;
                            try {
                                // make sure the directory exists
                                backing.getParentFile().mkdirs();
                                writer = new FileWriter( backing );
                                xs.toXML( store, writer );
                            } catch (IOException e) {
                                throw Unchecked.wrap( e );
                            } finally {
                                IOUtils.closeQuietly(writer);
                            }
                            isDirty = false;
                        }
                    }
                }
            });

            store = read( backing, xs );

        } else {
            store = new HashMap<String, Property>();
        }
    }

    @Override
    public Property get( @NotNull String key) {
        return store.get(key);
    }

    @Override
    public synchronized void setProperty( @NotNull String key, @NotNull Property prop) {
        store.put( key, prop );
        isDirty = true;
    }

    @Override
    public void setDirty() {
        isDirty = true;
    }


    private static Map<String, Property> read( final File path, XStream xs ) {
        Reader reader = null;
        try
        {
            reader = new FileReader( path );
            return (Map<String, Property>) xs.fromXML( reader );
        } catch (FileNotFoundException e) {
            return new HashMap<String, Property>();
        } catch (IOException e) {
            return new HashMap<String, Property>();
        } finally {
            IOUtils.closeQuietly( reader );
        }
    }



}
