package org.openCage.jmidgard.main;

import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.jmidgard.core.Base;
import org.openCage.jmidgard.core.Executor;
import org.openCage.jmidgard.core.Task;
import org.openCage.jmidgard.tasks.CompileTask;
import org.openCage.jmidgard.tasks.JarTask;
import sun.security.x509.CertificatePolicyMap;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 11.07.11
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */
public class OCLangMain {

    public static void main(String[] args) {
        Base base = new Base();
        base.setRootDir(FSPathBuilder.getPath(new File(".")));

        verifyRoot( base );



        CompileTask comp = new CompileTask( "Compile" );
        comp.setConf(base, "lang" );

        Task jar = new JarTask( "Jar" ).depends( comp );

        new Executor().execute( jar );

    }

    public static void verifyRoot( Base base ) {
        if ( !base.getRootDir().add( "modules", "jmdg" ).toFile().exists()) {
            throw new IllegalStateException( "not at the root of a jmidgard project " + base.getRootDir() );
        }

        if ( !base.getRootDir().add( "modules", "lang" ).toFile().exists()) {
            throw new IllegalStateException( "this is not opencage-lang" + base.getRootDir() );
        }
    }

}
