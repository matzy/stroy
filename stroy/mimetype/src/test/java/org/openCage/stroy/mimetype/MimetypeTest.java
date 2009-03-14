package org.openCage.stroy.mimetype;

import org.junit.Test;
import eu.medsea.mimeutil.MimeUtil;

public class MimetypeTest {

    @Test
    public void testNoFile() {
        System.out.println( MimeUtil.getMimeTypes( "doo.txt" ));
    }
}
