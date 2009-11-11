package org.openCage.utils.persistence;

import org.openCage.util.io.FileUtils;
import org.openCage.withResource.protocol.BackgroundSaver;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.Writer;
import java.io.Reader;

import com.google.inject.Inject;
import java.util.logging.Logger;
import org.openCage.lang.protocol.FE0;
import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.protocol.With;


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
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class PersistenceImpl<T extends Persistable> implements Persistence<T> {

    private static final Logger LOG = Logger.getLogger( PersistenceImpl.class.getName());

    private final BackgroundSaver backgroundSaver;
    private final With with;

    private final XStreamT<T> xstreamt = new XStreamT<T>();

    @Inject
    public PersistenceImpl( final BackgroundSaver backgroundSaver, final With with ) {
        this.backgroundSaver = backgroundSaver;
        this.with = with;
    }


    public T load( T init, @NotNull final String name ) {

        String path = getPathEnsure(name);

        T prefs = init;

        if ( new File( path ).exists() ) {
            try {
                prefs = with.withReader(new File(path), new FE1<T, Reader>() {

                    public T call(Reader reader) throws Exception {
                        return xstreamt.fromXML( reader );
                    }
                });
            } catch( Error exp ) {
                LOG.warning( "failed to read existing preference file. Using default." );
                prefs = init;

            }
        }

        final T prefsFinal = prefs;
        backgroundSaver.addTask( new FE0<Void>() {
            public Void call() {
                save( prefsFinal, name );
                return null;
            }
        } );

        return prefs;
    }


    // TODO factor out path creation and avoid NLS
    private String getPathEnsure(String name) {
        String path = FileUtils.getHomeDir() + "/." + name + "/preferences.xml";

        // ensure parent
        new File( path ).getParentFile().mkdirs();

        return path;

    }

    public synchronized void save( final T prefs, @NotNull final String name ) {

        if ( prefs == null || !prefs.isDirty() ) {
            return;
        }

        String path = getPathEnsure( name );

        with.withWriter( new File(path), new FE1<Void, Writer>() {

            public Void call( Writer writer) throws Exception {
                xstreamt.toXML( prefs, writer );
                return null;
            }
        } );

        prefs.setClean();
    }
}

