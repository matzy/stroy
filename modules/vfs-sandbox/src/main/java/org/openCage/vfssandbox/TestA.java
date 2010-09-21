package org.openCage.vfssandbox;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 21, 2010
 * Time: 3:40:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestA {


    public static void main(String[] args) {
        try {
            FileSystemManager fsManager = VFS.getManager();
            FileObject jarFile = fsManager.resolveFile( "jar:/Users/stephan/Documents/prs/stroy-10/repo/commons-vfs/commons-vfs/1.0/commons-vfs-1.0.jar" );

            // List the children of the Jar file
            FileObject[] children = jarFile.getChildren();
            System.out.println( "Children of " + jarFile.getName().getURI() );
            for ( int i = 0; i < children.length; i++ )
            {
                System.out.println( children[ i ].getName().getBaseName() );
            }

        } catch (FileSystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        FileObject jarFile = fsManager.resolveFile( "jar:lib/aJarFile.jar" );
    }
}
