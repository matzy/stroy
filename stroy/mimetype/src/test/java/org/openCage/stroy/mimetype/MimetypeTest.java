package org.openCage.stroy.mimetype;

import org.junit.Test;
import eu.medsea.mimeutil.MimeUtil;

import java.util.Collection;

public class MimetypeTest {

    @Test
    public void testNoFile() {
        System.out.println( MimeUtil.getMimeTypes( "doo.txt" ));
    }

    @Test
    public void testJar() {
        Collection coll = MimeUtil.getMimeTypes( "foo.jar" );

        int i = 0;
    }
}
