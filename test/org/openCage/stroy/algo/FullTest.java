package org.openCage.stroy.algo;

import junit.framework.TestCase;

import java.io.File;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.TreeFactory;
import org.openCage.stroy.algo.matching.TreeTaskFactory;
import org.openCage.stroy.algo.matching.TreeTask;
import org.openCage.stroy.algo.matching.Tasks;
import org.openCage.stroy.algo.matching.strategies.Strategy;
import org.openCage.stroy.algo.matching.strategies.TreeStrategy;
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

        Injector        ij  = Guice.createInjector( new FullTestModule() );
        TreeFactory     tf  = ij.getInstance( TreeFactory.class );
        TreeTaskFactory ttf = ij.getInstance( TreeTaskFactory.class );
        TreeStrategy    st  = ij.getInstance( TreeStrategy.class );

        Noed zipRoot = tf.create( pathZip, false ).build( pathZip );
        assertNotNull( zipRoot );

        Noed fsRoot = tf.create( pathFs, false ).build( pathFs );
        assertNotNull( fsRoot );

        TreeTask tt = ttf.create( zipRoot, fsRoot );
        assertNotNull( tt );

        int leftUnmatched = tt.getLeft( Tasks.isUnmatched ).size();

        st.match( tt );
        int leftUnmatchedAfter = tt.getLeft( Tasks.isUnmatched ).size();

        assertTrue( leftUnmatched > leftUnmatchedAfter );

    }



}
