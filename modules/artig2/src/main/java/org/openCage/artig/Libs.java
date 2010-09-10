package org.openCage.artig;

import org.openCage.artig.stjx.Artifact;
import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 1, 2010
 * Time: 2:08:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Libs {
    private Artig artig;

    public Libs( Artig artig ) {
        this.artig = artig;
    }

    public void copyToRepo() {
        for ( Artifact arti : artig.getProject().getExternals()) {
            FSPath lib =  ArtigUtils.getLibraryLocation( artig.getRootPath().add( "repo"), arti);
            FSPath mavenlocal =  ArtigUtils.getLibraryLocation( FSPathBuilder.getHome().add( ".m2", "repository"), arti);

            if ( !lib.toFile().exists()) {
                if ( mavenlocal.toFile().exists() ) {
                    try {
                        System.out.println("copy " + mavenlocal + " ->" + lib );
                        IOUtils.ensurePath( lib );
                        copy( mavenlocal.toFile(), lib.toFile() );
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }

    }

    public void libs() {
        for ( Artifact arti : artig.getProject().getExternals()) {
            FSPath lib =  ArtigUtils.getLibraryLocation( artig.getRootPath().add( "repo"), arti);
            FSPath mavenlocal =  ArtigUtils.getLibraryLocation( FSPathBuilder.getHome().add( ".m2", "repository"), arti);

            System.out.println( lib );
            System.out.println( "   " + lib.toFile().exists() );
            System.out.println( "   " + mavenlocal.toFile().exists() );


        }
    }

    public static void copy( File from, File to ) throws IOException {
        FileReader in = new FileReader(from);
        FileWriter out = new FileWriter(to);
        int c;

        while ((c = in.read()) != -1) {
            out.write(c);
        }

        in.close();
        out.close();
    }
}
