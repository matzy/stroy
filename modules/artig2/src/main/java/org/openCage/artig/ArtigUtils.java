package org.openCage.artig;

import org.openCage.artig.stjx.Artifact;
import org.openCage.io.fspath.FSPath;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 1, 2010
 * Time: 11:46:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArtigUtils {

    private String getLibraryLocation(Artifact arti) {
        return arti.getGroupId().replace('.', '/')  + "/" + arti.getName() + "/" + arti.getVersion() + "/" + arti.getName() + "-" + arti.getVersion() + ".jar" ;
    }

    public static FSPath getLibraryLocation( FSPath base, Artifact arti) {

        return base.addPackage( arti.getGroupId() ).add( arti.getName(), arti.getVersion(), arti.getName() + "-" + arti.getVersion() + ".jar" );
    }
}
