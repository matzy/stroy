package org.openCage.jmidgard.core;

import org.openCage.io.Resource;
import org.openCage.io.With;
import org.openCage.io.fspath.FSPath;
import org.openCage.lang.functions.FE1;

import java.io.*;
import java.util.jar.*;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 12.07.11
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class JarBuilder {

    private static final Log log = Log.getLogger(Compiler.class.getName());

    //private List<FSPath>


    public JarBuilder setMain(String className) {
        return this;
    }

    public JarBuilder addClassesRoot(FSPath path) {
        return this;
    }

    public JarBuilder addResourcesRoot(FSPath path) {
        return this;
    }

    public JarBuilder setTargetDir(FSPath path) {
        return this;
    }

    public boolean build() {

        With.with(new FE1<Object, Resource>() {
            @Override
            public Object call(Resource resource) throws Exception {
                Manifest manifest = new Manifest();
                manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
                JarOutputStream target = resource.add(new JarOutputStream(new FileOutputStream("output.jar"), manifest));
                //add(new File("inputDirectory"), target);



                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        return true;
    }


    private void add( File source, JarOutputStream target) throws IOException {
        BufferedInputStream in = null;
        try {
            if (source.isDirectory()) {
                String name = source.getPath().replace("\\", "/");
                if (!name.isEmpty()) {
                    if (!name.endsWith("/"))
                        name += "/";
                    JarEntry entry = new JarEntry(name);
                    entry.setTime(source.lastModified());
                    target.putNextEntry(entry);
                    target.closeEntry();
                }
                for (File nestedFile : source.listFiles())
                    add(nestedFile, target);
                return;
            }

            JarEntry entry = new JarEntry(source.getPath().replace("\\", "/"));
            entry.setTime(source.lastModified());
            target.putNextEntry(entry);
            in = new BufferedInputStream(new FileInputStream(source));

            byte[] buffer = new byte[1024];
            while (true) {
                int count = in.read(buffer);
                if (count == -1)
                    break;
                target.write(buffer, 0, count);
            }
            target.closeEntry();
        } finally {
            if (in != null)
                in.close();
        }
    }

}
