package org.openCage.lindwurm;

import junit.framework.Assert;
import org.junit.Test;
import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.xml.XmlLindwurmBuilder;
import sun.net.idn.StringPrep;

import javax.swing.tree.TreePath;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.openCage.kleinod.io.IOUtils.closeQuietly;
import static org.openCage.lindwurm.TreeNodes.getTreePath;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/14/12
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreePathTest {

    @Test
    public void testGetTreePath() {
        assertEquals( new TreePath(new String[] {"1","2", "3"}), getTreePath(Arrays.asList("1","2","3")));
    }

    @Test
    public void testGetTreePathFromNode() {
        InputStream is = null;
        try {
            is = new ByteArrayInputStream( "<a><b>woo</b></a>".getBytes() );

            LindenNode root = new XmlLindwurmBuilder().build(
                    Null.of(Ignore.class),
                    is );

            assertNotNull(root);

            TreePath tp = getTreePath( root.dir().getChildren().iterator().next());
            System.out.println(tp);

            Assert.assertEquals( new TreePath( new String[]{"a","b"}), tp);
        } finally {
            closeQuietly(is);
        }

    }
}
