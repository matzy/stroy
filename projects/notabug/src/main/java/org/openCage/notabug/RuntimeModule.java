package org.openCage.notabug;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.ruleofthree.property.*;

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
        binder.bind(PropertyStoreRW.class ).to(SingleFileRW.class);


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
