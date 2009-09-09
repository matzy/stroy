package org.openCage.utils.persistence;

import org.openCage.util.io.FileUtils;
import org.openCage.util.logging.Log;
import org.openCage.util.lang.V1;
import static org.openCage.utils.io.with.WithIO.withReader;
import static org.openCage.utils.io.with.WithIO.withWriter;
import org.openCage.utils.io.with.ReaderFunctor;
import org.openCage.utils.io.with.WriterFunctor;
import org.openCage.utils.func.F1;
import org.openCage.utils.func.F0;
import org.openCage.utils.lang.BackgroundSaver;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.Writer;
import java.io.Reader;
import java.io.IOException;

import com.google.inject.Inject;


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

    private final BackgroundSaver backgroundSaver;

    private final XStreamT<T> xstreamt = new XStreamT<T>();

    @Inject
    public PersistenceImpl( final BackgroundSaver backgroundSaver ) {
        this.backgroundSaver = backgroundSaver;
    }


    public T load( T init, @NotNull final String name ) {

        String path = getPathEnsure(name);

        T prefs = init;

        if ( new File( path ).exists() ) {
            try {
                prefs = withReader( path, new ReaderFunctor<T>() {
                    public T c(Reader reader) throws IOException {
                        return xstreamt.fromXML( reader );
                    }
                });
            } catch ( Exception exp ) {
                Log.warning( "failed to read existing preference file. Using default." );
                prefs = init;
            }
        }

        final T prefsFinal = prefs;
        backgroundSaver.addTask( new F0<Void>() {
            public Void c() {
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

        withWriter( path, new WriterFunctor() {
            public void c( Writer writer ) {
                xstreamt.toXML( prefs, writer );
            }
        } );

        prefs.setClean();
    }
}

