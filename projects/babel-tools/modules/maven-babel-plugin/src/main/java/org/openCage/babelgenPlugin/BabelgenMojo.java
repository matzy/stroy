package org.openCage.babelgenPlugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.openCage.babelgen.BabelGen;
import org.openCage.io.IOUtils;
import org.openCage.io.Resource;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.FE1;
import org.openCage.lang.structure.T2;

import java.io.*;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.PropertyResourceBundle;

/**
 * @goal babel
 * @phase generate-sources
 *
 * the goal of this plugin
 * the phase in the maven lifecycle where this plugin runs
 */
public class BabelgenMojo extends AbstractMojo {


    /**
     * The directory where the properties files (<code>*.properties</code>) are located.
     *
     * @parameter expression="${sourceDirectory}" default-value="${basedir}/src/main/resources"
     */
    private File sourceDirectory;

    /**
     * A set of Ant-like inclusion patterns used to select files from the source directory for processing. By default,
     * the patterns <code>**&#47;*.jj</code> and <code>**&#47;*.JJ</code> are used to select grammar files.
     *
     * @parameter
     */
    private String[] includes;

    /** @parameter default-value="${project}" */
    private org.apache.maven.project.MavenProject mavenProject;



    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {

        //this.getPluginContext().
        getLog().info( (String)(mavenProject.getBuild().getResources().get(0).getDirectory()));

        final CollectProps cp = new CollectProps( FSPathBuilder.getPath( mavenProject.getBuild().getResources().get(0).getDirectory()));

        getLog().info( "hw + " + mavenProject.getBasedir());

        System.out.println( "target = " + mavenProject.getBuild().getDirectory());

        getLog().info( "hw + " + sourceDirectory.getAbsolutePath() );

        // find .properties

        FSPath res = FSPathBuilder.getPath( sourceDirectory );

        final FSPath gensrcroot = FSPathBuilder.getPath(mavenProject.getBasedir()).add("target","generated-sources", "babelgen");

        mavenProject.addCompileSourceRoot( gensrcroot.toString() );

        for ( final FSPath prop : cp.getProps().keySet() ) {
            getLog().info( prop.toString() );
            final String pkg = cp.getProps().get(prop);
            final String className = propToClass( prop.getFileName() );
            try {
                final BabelGen bg = new BabelGen( getBundelName( prop, pkg ),
                                                  new PropertyResourceBundle(
                                                    new FileInputStream( prop.toString() )));

                final FSPath gensrc = FSPathBuilder.getPath(mavenProject.getBasedir()).add("target", "generated-sources", "babelgen").addPackage(pkg).add(className + ".java");
                IOUtils.ensurePath( gensrc );

//            getLog().info(bg.generate("foo", "du"));

                Resource.tryWith( new FE1<Void, Resource>() {
                    @Override
                    public Void call(Resource resource) throws Exception {

                        FileWriter os = resource.add(new FileWriter(gensrc.toFile()));

                        os.write( bg.generate( pkg, className));

                        return null;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        } catch ( UndeclaredThrowableException ex ) {
            getLog().error(ex);
            for ( StackTraceElement el : ex.getStackTrace()) {
                getLog().error( el.toString() );
            }
        }



    }

    public static String propToClass( String name ) {
        return Strings.toFirstUpper( name.substring(0, name.length() - "_en.properties".length())) + "Bundle";
    }

    public static String getBundelName( FSPath path, String pkg ) {
        String name = path.getFileName();
        return pkg + "." + name.substring(0, name.length() - "_en.properties".length());

    }


}