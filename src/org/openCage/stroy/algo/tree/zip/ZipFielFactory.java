package org.openCage.stroy.tree.zip;

import org.openCage.stroy.algo.tree.zip.ZipFiel;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHashGen;

import java.util.zip.ZipEntry;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 23.11.2008
 * Time: 15:15:26
 * To change this template use File | Settings | File Templates.
 */
public interface ZipFielFactory {

    public ZipFiel create( String path, ZipEntry entry, String type, FuzzyHashGen<InputStream> fg );

}
