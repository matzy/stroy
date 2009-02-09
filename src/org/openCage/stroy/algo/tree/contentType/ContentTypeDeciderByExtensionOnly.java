package org.openCage.stroy.algo.tree.contentType;

import org.openCage.util.lang.Lazy;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 8, 2009
 * Time: 10:18:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContentTypeDeciderByExtensionOnly implements ContentTypeDecider {


    public ContentType decide( String extension, Lazy<InputStream> is ) {

        if ( extension.compareToIgnoreCase( "java" ) == 0 ) {
            return new ContentType( "","");
        }


        return new ContentType( extension, "unknown");
    }
}
