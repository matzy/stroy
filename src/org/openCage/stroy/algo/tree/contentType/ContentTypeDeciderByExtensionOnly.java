package org.openCage.stroy.algo.tree.contentType;

import org.openCage.util.lang.Lazy;
import org.openCage.util.io.FileUtils;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 8, 2009
 * Time: 10:18:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContentTypeDeciderByExtensionOnly implements ContentTypeDecider {

    private KnownTypes known = new KnownTypes();

    public ContentType decide( String name, Lazy<InputStream> is ) {

        String ext = FileUtils.getExtension( name );

        if ( ext.equals( "" )) {

        }

        ContentType ct = known.get(name);
        if ( ct != null ) {
            return ct;
        }

        return null; // new ContentType( name, "unknown");
    }
}
