package org.openCage.stroy.algo.tree.contentType;

import org.openCage.util.lang.Lazy;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 8, 2009
 * Time: 10:10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ContentTypeDecider {

    public ContentType decide( String name, Lazy<InputStream> is );
}
