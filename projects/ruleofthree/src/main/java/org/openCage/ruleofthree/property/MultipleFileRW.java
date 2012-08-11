package org.openCage.ruleofthree.property;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.io.IOUtils;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.VF0;
import org.openCage.lang.inc.ImmuList;
import org.openCage.ruleofthree.*;
import org.openCage.ruleofthree.jtothree.JToThree;
import org.openCage.ruleofthree.threetoxml.XmlToThree;
import org.openCage.ruleofthree.threetoxml.ThreeToXml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/6/12
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MultipleFileRW implements PropertyStoreRW {

    private F0<ThreeMap<Object>> propertiesProvider;
    private boolean dirty = false;
    private final File backingFile;
    private final NamingScheme namingScheme;
    private final XmlToThree xmlToThree = new XmlToThree();
    private final ThreeToXml threeToXml = new ThreeToXml();
    private final JToThree JToThree = new JToThree();
    private final BackgroundExecutor executor;
    private final ThreeMap<Object> properties = new ThreeHashMap<Object>();
    private final ThreeMap<Three> readableProps = new ThreeHashMap<Three>();

    @Inject
    public MultipleFileRW( BackgroundExecutor executor,
                           @Named("PropStoreFile") File backingFile,
                           NamingScheme namingScheme ) {
        this.backingFile = backingFile;
        this.namingScheme = namingScheme;
        this.executor = executor;

        createWriter();
    }

    @Override
    public void setDirty() {
        dirty = true;
    }

    @Override
    public ThreeMap<Three> getThreedProps() {
        return  read( readableProps, backingFile, new ImmuList<String>());
    }

    @Override
    public ThreeMap<Object> getProps() {
        return properties;
    }

    private ThreeMap<Three> read( ThreeMap<Three> readableProps, File start, ImmuList<String> names ) {

        start.mkdirs();

        for ( File child : start.listFiles() ) {
            // TODO more ?
            if ( child.getName().equals(".DS_Store")) {
                continue;
            }
            ImmuList<String> next = names.add( child.getName() );
            if ( child.isDirectory() ) {
                read(readableProps, child, next);
            } else {

                Three readable = xmlToThree.read(namingScheme.getPropName(), namingScheme.getFile(backingFile, next));
                readableProps.put( namingScheme.getKey(next), readable );
            }

        }

        return readableProps;
    }

    private void createWriter() {
        final MultipleFileRW that = this;
        executor.addPeriodicAndExitTask( new VF0() {
            @Override
            public void call() {
                if ( dirty ) {
                    synchronized ( that) {

                        for ( Map.Entry<ThreeKey,Object> prop : properties.entrySet() ) {
                            readableProps.put( prop.getKey(), JToThree.toThree(prop.getValue()));
                        }


                        FileWriter writer = null;
                        BufferedWriter out = null;

                        for ( ThreeKey key : readableProps.keySet() ) {
                            File target = namingScheme.getPath( backingFile, key.toString() );
                            target.getParentFile().mkdirs();

                            try {
                                writer           = new FileWriter(target);
                                out              = new BufferedWriter(writer);
                                String clazzName = properties.get(key).getClass().getSimpleName();

                                out.write( threeToXml.write(new ThreeKey(clazzName), readableProps.get(key)).toString());

                            } catch (IOException e) {
                                throw Unchecked.wrap(e);
                            } finally {
                                IOUtils.closeQuietly(out);
                                IOUtils.closeQuietly(writer);
                            }
                        }

                        dirty = false;
                    }
                }
            }
        });
    }



}
