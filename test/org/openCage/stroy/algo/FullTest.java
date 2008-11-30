package org.openCage.stroy.algo;

import junit.framework.TestCase;

import java.io.File;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.TreeFactory;
import org.openCage.stroy.filter.NullIgnore;
import com.sun.tools.doclets.internal.toolkit.builders.AbstractBuilder;
import com.google.inject.Injector;
import com.google.inject.Guice;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 29.11.2008
 * Time: 08:37:55
 * To change this template use File | Settings | File Templates.
 */
public class FullTest extends TestCase {


    public void testZipAndFS() {

        String path = getClass().getResource( ".").getPath();
        System.out.println( path );

        String pathZip = path.substring( 0, path.length() - "classes/stroy/test/stroy/org/openCage/stroy/algo/".length() )
                + "stroy/test-resources/testA/stroy.zip";
        String pathFs = path.substring( 0, path.length() - "classes/stroy/test/stroy/org/openCage/stroy/algo/".length() )
                + "stroy/test-resources/testA/stroy";

        System.out.println( pathZip );
        assertTrue(  new File(pathZip).exists());

        Injector ij = Guice.createInjector( new FullTestModule() );

        TreeFactory tf = ij.getInstance( TreeFactory.class );

        Noed zipRoot = tf.create( pathZip, false ).build( pathZip );
        assertNotNull( zipRoot );

        Noed fsRoot = tf.create( pathFs, false ).build( pathFs );
        assertNotNull( fsRoot );



    }



}
