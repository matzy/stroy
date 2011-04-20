package org.openCage.property;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.openCage.io.SingletonApp;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.FV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
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

            background.addPeriodicAndExitTask( new FV() {
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
                                IOUtils.closeQuietly( writer );
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
