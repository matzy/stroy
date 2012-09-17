package org.openCage.notabug;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.openCage.kleinod.io.IOUtils;
import org.openCage.kleinod.io.fspath.FSPath;
import org.openCage.kleinod.io.fspath.FSPathBuilder;
import org.openCage.kleinod.thread.BackgroundExecutor;
import org.openCage.kleinod.thread.BackgroundExecutorImpl;
import org.openCage.ruleofthree.property.MultipleFileRW;
import org.openCage.ruleofthree.property.NamingScheme;
import org.openCage.ruleofthree.property.PropStoreImpl;
import org.openCage.ruleofthree.property.PropertyStore;
import org.openCage.ruleofthree.property.PropertyStoreRW;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/23/12
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RuntimeModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind( PropertyStore.class ).to( PropStoreImpl.class).in( Singleton.class );

        binder.bind( File.class ).annotatedWith( Names.named("PropStoreFile")).toInstance( getRoot().toFile());
        binder.bind(BackgroundExecutor.class).to(BackgroundExecutorImpl.class);
        binder.bind(NamingScheme.class).to( IdToPath.class );
        binder.bind(PropertyStoreRW.class ).to(MultipleFileRW.class);


    }

    public static FSPath getRoot() {
        FSPath dir = FSPathBuilder.getCurrentDir();

        while ( notRoot( dir ) ) {
            if ( dir.isRoot() ) {
                throw new IllegalArgumentException("not a project: " + FSPathBuilder.getCurrentDir());
            }
            dir = dir.parent();
        }

        dir = dir.add( "meta", "notabug");
        IOUtils.ensurePath(dir);

        return dir;
    }

    private static boolean notRoot(FSPath dir) {
        return !dir.add( "src", "main").toFile().exists();
    }

}
