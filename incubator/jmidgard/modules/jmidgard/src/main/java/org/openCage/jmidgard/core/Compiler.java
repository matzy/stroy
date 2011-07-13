package org.openCage.jmidgard.core;

import org.openCage.io.fspath.FSPath;

import javax.tools.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 7/2/11
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class Compiler {

    private static final Log log = Log.getLogger( Compiler.class.getName() );
    private FSPath target;
    private List<String> sources = new ArrayList<String>();

    /**
     * The root dir for the compiled class files
     * the full path is this dir + path from package declaration in the source files
     * @param trgt A directory
     * @return The compile class (fluent style)
     */
    public Compiler targetDir( FSPath trgt ) {
        this.target = trgt;
        return this;
    }

    public Compiler addSource(FSPath src) {
        this.sources.add( src.toString() );
        return this;
    }

    public boolean compile() {
        JavaCompiler                        compiler    = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager             fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        Iterable<? extends JavaFileObject>  compilationUnits = fileManager
                .getJavaFileObjectsFromStrings( sources );

        List<String> options = new ArrayList<String>();

        if ( target != null ) {
            options.add( "-d" );
            options.add( target.toString());
        }


        JavaCompiler.CompilationTask task = compiler.getTask( null, fileManager, diagnostics, options, null, compilationUnits);
        boolean success = task.call();

        if ( success ) {
            log.two.info( "Success: " + success );
        }
        if (!success ) {
            log.two.severe( "Success: " + success );

            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                System.out.println( diagnostic.getMessage(Locale.getDefault()));
                System.out.println(diagnostic.getLineNumber());
                System.out.println(diagnostic.getSource());
            }
        }

        return success;

    }
}
