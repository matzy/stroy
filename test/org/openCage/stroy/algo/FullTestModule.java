package org.openCage.stroy.algo;

import com.google.inject.AbstractModule;
import org.openCage.stroy.algo.tree.TreeFactory;
import org.openCage.stroy.algo.tree.TreeFactoryImpl;
import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.filesystem.FileSystem;
import org.openCage.stroy.algo.tree.filesystem.FSNoedGenerator;
import org.openCage.stroy.algo.tree.zip.ZipArchive;
import org.openCage.stroy.algo.tree.zip.ZipNoedGenerator;
import org.openCage.stroy.algo.tree.singleFile.SingleFile;
import org.openCage.stroy.algo.tree.singleFile.SingleFileGenerator;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.filter.IgnoreByLists;
import org.openCage.stroy.tree.zip.ZipFielFactory;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 30.11.2008
 * Time: 18:23:09
 * To change this template use File | Settings | File Templates.
 */
public class FullTestModule extends AbstractModule {
    protected void configure() {
        bind( TreeFactory.class ).
                to( TreeFactoryImpl.class );

        bind( NoedGenerator.class ).
                annotatedWith( SingleFile.class ).
                to( SingleFileGenerator.class );
        bind( NoedGenerator.class ).
                annotatedWith( ZipArchive.class ).
                to( ZipNoedGenerator.class );
        bind( NoedGenerator.class ).
                annotatedWith( FileSystem.class ).
                to( FSNoedGenerator.class );

        bind( Ignore.class).to( IgnoreByLists.class );
    }
}
