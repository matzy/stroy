package org.openCage.jmidgard;

import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.jmidgard.core.Base;
import org.openCage.jmidgard.core.Executor;
import org.openCage.jmidgard.tasks.CompileTask;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 04.07.11
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class JTestMain {


    public static void main(String[] args) {

        Base base = new Base();
        base.setRootDir(FSPathBuilder.getPath(new File(".")));

        verifyRoot( base );



        CompileTask comp = new CompileTask( "Compile" );
        comp.setConf(base, "jtest" );

        new Executor().execute( comp );



    }

    public static void verifyRoot( Base base ) {
        if ( !base.getRootDir().add( "modules", "jmdg" ).toFile().exists()) {
            throw new IllegalStateException( "not at the root of a jmidgard project " + base.getRootDir() );
        }
    }
}
