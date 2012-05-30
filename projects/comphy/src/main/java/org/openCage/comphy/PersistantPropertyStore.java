package org.openCage.comphy;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.io.IOUtils;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.VF0;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.openCage.comphy.DereaderUtil.deread;

public class PersistantPropertyStore implements Observer, PropertyStore {

    private RMap readableProps;
    private Map<Key,Property> props = new HashMap<Key, Property>();
    private final File backingFile;
    private boolean isDirty;
    private final BackgroundExecutor executor;
    private final ReadableToXML readableToXML = new ReadableToXML();


    @Inject
    public PersistantPropertyStore( BackgroundExecutor executor, @Named( value = "PropStoreFile") File backingFile ) {
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
        readableProps = (RMap)xmlReader.read(backingFile.getAbsolutePath());
    }

    private void createWriter(BackgroundExecutor executor, final File backingFile, final PersistantPropertyStore that) {
        executor.addPeriodicAndExitTask( new VF0() {
            @Override
            public void call() {
                if ( isDirty ) {
                    synchronized ( that) {
                        if( readableProps == null ) {
                            readableProps = new RMap();
                        }

                        for ( Map.Entry<Key,Property> prop : props.entrySet() ) {
                            readableProps.put( prop.getKey(), prop.getValue().toReadable());
                        }

                        FileWriter writer = null;
                        BufferedWriter out = null;
                        try {
                            // make sure the directory exists
                            backingFile.getParentFile().mkdirs();
                            writer = new FileWriter(backingFile);
                            out = new BufferedWriter(writer);
                            System.out.println( readableToXML.write("comphy",readableProps).toString());
                            out.write(readableToXML.write("comphy",readableProps).toString());
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

    public void add( Key key, Property prop ) {
        prop.getListenerControl().add(this);
//        readableProps.put(key, prop.toReadable());
        props.put( key, prop );
        //call(null);
        isDirty = true;
    }

    public void add( String key, Property prop ) {
        add( new Key(key), prop );
    }


    public <T extends Property> T get( String key, Dereadalizer<T> deread ) {
        return get( new Key( key ), deread );
    }

    public <T extends Property> T get( Key key, Dereadalizer<T> dereader ) {

        T prop =  (T)props.get( key );

        if ( prop != null ) {
            return prop;
        }

        if ( readableProps == null ) {
            return null;
        }

        Readable rdlbVal  = readableProps.get(key);

        if ( rdlbVal == null ) {
            return null;
        }

        T ret = deread(dereader, rdlbVal);
        add( key, ret );
//        ret.getListenerControl().add( this );

        return ret;
    }

    @Override
    public void call() {
        System.out.println("a change!");
//        show();
        isDirty = true;
    }

}
