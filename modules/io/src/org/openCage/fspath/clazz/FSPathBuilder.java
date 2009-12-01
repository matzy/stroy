package org.openCage.fspath.clazz;

import org.apache.commons.lang.SystemUtils;
import org.openCage.fspath.impl.FSPathUnix;
import org.openCage.fspath.protocol.FSPath;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 30, 2009
 * Time: 10:22:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSPathBuilder {

    public static FSPath getPath( String str ) {
        if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_UNIX ) {
            return new FSPathUnix(str);
        }


        return null;
    }


    public static FSPath getPath( File file ) {
        return getPath( file.getAbsolutePath() );
    }


    public static FSPath getHome() {
        return getPath( SystemUtils.getUserHome() );
    }
}
