package org.openCage.lindwurm.xml;

import org.junit.Test;
import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.Ignore;
import org.openCage.lindwurm.LindenNode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.Assert.assertNotNull;
import static org.openCage.kleinod.io.IOUtils.closeQuietly;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/22/12
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class XmlLindwumBuilderTest {

    @Test
    public void simple() {
        LindenNode root = new XmlLindwurmBuilder().build(Null.of(Ignore.class), getClass().getResourceAsStream("/org/openCage/lindwurm/xml/test1.xml"));

        assertNotNull( root );

        System.out.println(root);

        //new ByteArrayInputStream("".getBytes());
    }

    @Test
    public void simpleString() {

        InputStream is = null;
        try {
            is = new ByteArrayInputStream( "<a><b>woo</b></a>".getBytes() );

            LindenNode root = new XmlLindwurmBuilder().build(
                    Null.of(Ignore.class),
                    is );

            assertNotNull( root );

            System.out.println(root);
        } finally {
            closeQuietly(is);
        }

        //new ByteArrayInputStream("".getBytes());
    }
}
