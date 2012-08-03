package org.openCage.ruleofthree.property;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import org.openCage.io.IOUtils;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.VF0;
import org.openCage.lang.inc.GHashMap;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.ImmuList;
import org.openCage.lang.listeners.Observer;
import org.openCage.ruleofthree.*;
import org.openCage.ruleofthree.jtothree.JfromThree;
import org.openCage.ruleofthree.threetoxml.ThreeFromXml;
import org.openCage.ruleofthree.threetoxml.ThreeToXml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class MultipleFilePropStore implements Observer, PropertyStore {

    private ThreeMap<Three> readableProps;
    private ThreeMap<Property> props = new ThreeHashMap<Property>();
    private final File backingFile;
    private boolean isDirty;
    private final BackgroundExecutor executor;
    private final ThreeToXml readableToXML = new ThreeToXml();
    private NamingScheme namingScheme;
    private JfromThree jFromThree = new JfromThree();
    private ThreeFromXml threeFromXml = new ThreeFromXml();


    @Inject
    public MultipleFilePropStore(BackgroundExecutor executor, @Named(value = "PropStoreFile") File backingFile, NamingScheme namingScheme) {
        this.backingFile = backingFile;
        this.executor = executor;
        this.namingScheme = namingScheme;

        isDirty = false;

        read();
        createWriter(executor, backingFile, this);


    }

    public void foo( File start, ImmuList<String> names ) {

        //System.out.println(names);

        start.mkdirs();

        for ( File child : start.listFiles() ) {
            if ( child.getName().equals(".DS_Store")) {
                continue;
            }
            ImmuList<String> next = names.add( child.getName() );
            if ( child.isDirectory() ) {
                foo( child, next );
            } else {

                Three readable = threeFromXml.read(namingScheme.getPropName(), namingScheme.getFile(backingFile, next));
                readableProps.put( namingScheme.getKey(next), readable );
            }

        }
    }

    private void read() {

        readableProps = new ThreeHashMap<Three>();
        foo( backingFile, new ImmuList<String>());
    }

    private void createWriter(BackgroundExecutor executor, final File backingFile, final MultipleFilePropStore that) {
        executor.addPeriodicAndExitTask( new VF0() {
            @Override
            public void call() {
                if ( isDirty ) {
                    synchronized ( that) {
                        if( readableProps == null ) {
                            readableProps = new ThreeHashMap<Three>();
                        }

                        for ( Map.Entry<ThreeKey,Property> prop : props.entrySet() ) {
                            readableProps.put( prop.getKey(), prop.getValue().toThree());
                        }


                        FileWriter writer = null;
                        BufferedWriter out = null;

                        for ( ThreeKey key : readableProps.keySet() ) {
                            File target = namingScheme.getPath( backingFile, key.toString() );
                            target.getParentFile().mkdirs();

                            try {
                                writer = new FileWriter(target);
                                out = new BufferedWriter(writer);

                                String clazzName = props.get(key).getClass().getSimpleName();
                                out.write(readableToXML.write(new ThreeKey(clazzName), readableProps.get(key)).toString());
                            } catch (IOException e) {
                                throw Unchecked.wrap(e);
                            } finally {
                                IOUtils.closeQuietly(out);
                                IOUtils.closeQuietly(writer);
                            }
                        }

                        isDirty = false;
                    }
                }
            }
        });
    }

    public void add( ThreeKey key, Property prop ) {
        synchronized (this) {
            prop.getListenerControl().add(this);
            props.put( key, prop );
            isDirty = true;
        }
    }


    public void add( String key, Property prop ) {
        add( new ThreeKey(key), prop );
    }


    public <T extends Property> T get( String key, Class<T> clazz ) {
        return get( new ThreeKey( key ), clazz  );
    }

    @Override
    public <T extends Property> T get( ThreeKey key, TypeLiteral<T> typeLiteral) {
        synchronized ( this ) {
            T prop =  (T)props.get(key);

            if ( prop != null ) {
                return prop;
            }

            if ( readableProps == null ) {
                return null;
            }

            Three rdlbVal  = readableProps.get(key);

            if ( rdlbVal == null ) {
                return null;
            }

            T ret = jFromThree.get( typeLiteral, rdlbVal);
            if ( ret == null ) {
                throw new Error("oopsy");
            }
            add( key, ret );
            //        ret.getListenerControl().add( this );

            return ret;
        }
    }

    @Override
    public <T extends Property> List<T> getAll(Class<T> clazz) {

        List<T> ret = new ArrayList<T>();

        for ( ThreeKey key : readableProps.keySet() ) {
            if ( !props.containsKey(key)) {
                get( key, clazz );
            }
            ret.add((T) props.get(key));
        }
        return ret;

    }


    @Override
    public <T extends Property> T get( ThreeKey key, Class<T> clazz ) {

        synchronized ( this ) {
            T prop =  (T)props.get( key );

            if ( prop != null ) {
                return prop;
            }

            if ( readableProps == null ) {
                return null;
            }

            Three rdlbVal  = readableProps.get(key);

            if ( rdlbVal == null ) {
                return null;
            }

            T ret = jFromThree.get( clazz, rdlbVal);
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
