package org.openCage.notabug;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.ruleofthree.property.MultipleFilePropStore;
import org.openCage.ruleofthree.property.NamingScheme;
import org.openCage.ruleofthree.property.PropertyStore;

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
        binder.bind( PropertyStore.class ).to( MultipleFilePropStore.class).in( Singleton.class );

        binder.bind( File.class ).annotatedWith( Names.named("PropStoreFile")).toInstance( getRoot().toFile());
        binder.bind(BackgroundExecutor.class).to(BackgroundExecutorImpl.class);
        binder.bind(NamingScheme.class).to( IdToPath.class );


    }

    public static FSPath getRoot() {
        FSPath dir = FSPathBuilder.getCurrentDir();

        if ( dir.getFileName().endsWith("notabug") && dir.parent().getFileName().equals("meta")) {
            return dir;
        }

        throw new Error( "bad dir" );

        //return null;
    }

}
