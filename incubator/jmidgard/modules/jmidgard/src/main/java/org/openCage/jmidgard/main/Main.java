package org.openCage.jmidgard.main;

import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 6/26/11
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println( new File(".").getAbsolutePath());

        System.out.println(FSPathBuilder.getPath(new File(".")).getFileName());

        FSPath current = FSPathBuilder.getPath(new File("."));
        String name = current.getFileName();

        if ( !current.add( "modules" ).toFile().exists() ) {
            IOUtils.ensurePath( current.add( "modules", name, "src", "main", "java", "org", "openCage", name, "foo.java" ));
            IOUtils.ensurePath( current.add( "modules", name, "src", "test", "java", "org", "openCage", name, "fooTest.java" ));
            IOUtils.ensurePath( current.add( "modules", "parent", "pom.xml" ));


//            current.add( "modules", name ).toFile().mkdir();
//            current.add( "modules", parent ).toFile().mkdir();

        }
    }
}
