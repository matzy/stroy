package org.openCage.jmidgard.tasks;

import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.jmidgard.core.*;
import org.openCage.jmidgard.core.Compiler;

import javax.persistence.criteria.Path;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 04.07.11
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public class CompileTask extends BaseTask {

    // TODO relpath
    private List<String> src = new ArrayList<String>();
    private String trgt;

    public CompileTask(String name, Set<Task> prereqs) {
        super(name, prereqs);
        init();
    }

    public CompileTask(String name, Task... prereqs) {
        super(name, prereqs);
        init();
    }

    private void init() {
        src.add( "src\\main\\java" );
        trgt = "target\\classes";
    }

    @Override
    public boolean needsToRun() {
        // TODO really check
        return true;
    }

    @Override
    public boolean run() {
        System.out.println( "   root: " + getBase().getRootDir());
        System.out.println( "   source paths: ");
        for ( String s : src ) {
            System.out.println( "      " + s );
        }
        System.out.println( "   target " + trgt );

        // TODO
        FSPath target = FSPathBuilder.getPath(getBase().getRootDir().toString() + "\\modules\\"+ getModuleName() +"\\" + trgt);
        IOUtils.ensurePath( target.add( "foo.duh" ));

        Compiler cc = new Compiler().targetDir( target );
        for ( String s : src ) {
            FSPath srcRoot = FSPathBuilder.getPath(getBase().getRootDir().toString() + "\\modules\\"+ getModuleName() +"\\" + s);

            addSrcFile( cc, srcRoot.toFile() );
        }

        return cc.compile();
    }

    private void addSrcFile(Compiler cc, File file) {
        System.out.println("      looking at " + file.getAbsolutePath() );
        if ( file.isFile() ) {
            if ( file.toString().endsWith(".java")) {
                cc.addSource( FSPathBuilder.getPath( file ));
            }
            return;
        }

        for ( File child : file.listFiles() ) {
            addSrcFile( cc, child );
        }
    }

    @Override
    public void clean() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public CompileTask setSourcePaths( List<String> src ) {
        this.src = src;
        return this;
    }

    public CompileTask setTarget( String tgt ) {
        this.trgt = tgt;
        return this;
    }


}
