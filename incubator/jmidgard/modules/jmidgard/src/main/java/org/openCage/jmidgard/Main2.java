package org.openCage.jmidgard;

import sun.awt.windows.ThemeReader;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 16.06.11
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class Main2 {

    public static void main(String[] args) {
        try {
            new Main2().hm();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void hm() throws IOException {
        JavaCompiler                        compiler    = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager             fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        String loc = "C:\\Users\\spf\\Documents\\prs\\new\\stroy-again\\incubator\\jmidgard\\";

        Iterable<? extends JavaFileObject> compilationUnits = fileManager
                .getJavaFileObjectsFromStrings(Arrays.asList( loc + "Foo.java"));

        //
        List<String> optionList = new ArrayList<String>();
//// set compiler's classpath to be same as the runtime's
//optionList.addAll(Arrays.asList("-classpath",System.getProperty("java.class.path")));
        String target = "C:\\tmp\\jmidg\\target";
    optionList.add("-d"); optionList.add( target );

//
//// any other options you want
//optionList.addAll(Arrays.asList(options));
//
//JavaCompiler.CompilationTask task = compiler.getTask(out,jfm,diagnostics,optionList,null,jfos);
        //

        JavaCompiler.CompilationTask task = compiler.getTask( null, fileManager, diagnostics, optionList, null, compilationUnits);
        boolean success = task.call();

        System.out.println("Success: " + success);
        if (!success ) {
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                System.out.println( diagnostic.getMessage(Locale.getDefault()));
                System.out.println(diagnostic.getLineNumber());
                System.out.println(diagnostic.getSource());
//                System.out.format("Error on line %d in %d%n",
//                                 diagnostic.getLineNumber(),
//                                 diagnostic.getSource().toString());
            }
        } else {
            try {

        // Load and instantiate compiled class.
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(target).toURI().toURL()});
                Class<?> cls = Class.forName("Foo", true, classLoader);
//                Class<?> cls = Class.forName("org.openCage.jmidgard.Foo", true, classLoader);
        Conf instance = (Conf)cls.newInstance(); // Should print "hello".
        System.out.println(instance + instance.getFoo() ); // Should print "test.Test@hashcode".                   Object ff = Class.forName( "Foo").getConstructor().newInstance();
                int i = 0;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        fileManager.close();
    }
}
