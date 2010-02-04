package org.openCage.webgen.clazz;

import java.util.ArrayList;
import java.util.List;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class LinkCollection {
    private static final String ANT_CONTRIB = "ant-contrib";
    private static final String COMMONS_CLI = "commons-cli";
    private static final String COMMONS_IO = "commons-io";
    private static final String COMMONS_LANG = "commons-lang";
    private static final String COMMONS_VFS = "commons-vfs";
    private static final String DESIGNGRIDLAYOUT = "designgridlayout";
    private static final String GUICE = "guice";
    private static final String IDEA_ANNOTATIONS = "idea-annotations";
    private static final String JARBUNDLER = "jarbundler";
    private static final String JAVAGRAPHICS_PREFERENCEPANEL = "javagraphics-preferencepanel";
    private static final String JCIP_ANNOTATIONS = "jcip-annotations";
    private static final String JDIC = "jdic";
    private static final String JGOODIES_BINDING = "jgoodies-binding";
    private static final String JGOODIES_FORMS = "jgoodies-forms";
    private static final String JMOCK = "jmock";
    private static final String JSMOOTH = "jsmooth";
    private static final String JUNIT = "junit";
    private static final String MAC_WIDGETS = "mac-widgets";
    private static final String MD5 = "MD5";
    private static final String MUCHSOFT_SYS = "muchsoft-sys";
    private static final String MYDOGGY = "mydoggy";
    private static final String RANDOM_GUID = "RandomGUID";
    private static final String SWING_LAYOUT = "swing-layout";
    private static final String SWING_WORKER = "SwingWorker";
    private static final String TABLELAYOUT = "tablelayout";
    private static final String URLUTF8_ENCODER = "URLUTF8Encoder";
    private static final String XSTREAM = "xstream";
    private static final String DEPEND_APPLICATION = "depend.application";
    private static final String DEPEND_IO = "depend.io";
    private static final String DEPEND_FAUSTERIZE = "depend.fausterize";
    private static final String DEPEND_LOCALIZATION = "depend.localization";
    private static final String DEPEND_LANG = "depend.lang";
    private static final String DEPEND_PROPERTY = "depend.property";
    private static final String DEPEND_UI = "depend.ui";
    private static final String APACHE = "apache";
    private static final String DEV_JAVA = "dev.java";
    private static final String ARGS4J = "args4j";
    private static final String PROJECT_DEPENDENCIES_USING_ANT = "Project Dependencies Using Ant";
    private static final String CODE_GOOGLE = "code.google";
    private static final String SOURCEFORGE = "sourceforge";
    private static final String MOTPE = "March of the Pink Elephants";

    private List<Ref> refs = new ArrayList<Ref>();

    public LinkCollection() {
        fill();
    }

    public List<Ref> getRefs() {
        return refs;
    }


    private void fill() {
        lib(ANT_CONTRIB ).
                    description( "Ant task collection. Includes if-task" ).
                    address( "http://ant-contrib.sourceforge.net/", "sourceforge.net" ).
                    apache2().
                    provides( "ant-contrib-1.0b3" ).
                    version( "1.0b3" );

        lib(ARGS4J ).
                    description( "Argument parsing as it should be done (via annotations)" ).
                    address( "https://args4j.dev.java.net/", DEV_JAVA).
                    mit().
                    version( "2.0.10" ).
                    provides( "args4j-2.0.10.jar" );

        lib(COMMONS_CLI).
                    description( "Apache command line tools." ).
                    address( "http://commons.apache.org/cli", APACHE).
                    apache2().
                    version( "1.2" ).
                    provides( "commons-cli-1.0.jar" );

        lib(COMMONS_IO).
                    description( "Apache IO tools. e.g. tools to cleanly close resources." ).
                    address( "http://commons.apache.org/io", APACHE).
                    apache2().
                    version( "1.4" ).
                    provides( "commons-io-1.4.jar" );

        lib(COMMONS_LANG).
                    description( "Apache java language level tools. Random number helpers, system property tools, string tools." ).
                    address( "http://commons.apache.org/lang/", APACHE).
                    apache2().
                    version( "2.4" ).
                    provides("commons-lang-2.4.jar");

//        lib(COMMONS_VFS).
//                    description( "Apache java library for virtual file systems." ).
//                    address( "http://commons.apache.org/vfs/", APACHE).
//                    apache2().
//                    version( "1.0" ).
//                    provides("commons-lang-2.4.jar").
//                    runtime();

        lib(DESIGNGRIDLAYOUT).
                    description( "Swing layout manager based on the use of canonical grids for user interface design." ).
                    address( "https://designgridlayout.dev.java.net/", DEV_JAVA).
                    lgpl().
                    version( "0.9" ).
                    provides( "designgridlayout-0.9.jar" ).
                    depends(SWING_LAYOUT);

        lib(GUICE).
                    description( "Google dependency injection lib in pure java.").
                    //de("Google Dependecy Injection lib ohne XML").
                    address( "http://code.google.com/p/google-guice/", CODE_GOOGLE).
                    apache2().
                    version( "2.0" ).
                    provides( "guice-2.0.jar");

        lib(IDEA_ANNOTATIONS).
                    description( "Nullable annotations (bundled with Idea CE)" ).
                    address( "http://www.jetbrains.com/index.html", "jetbrains").
                    apache2().
                    provides( "annotations.jar");

        lib(JARBUNDLER).  // TODO
                    description( "Ant task to create OSX bundles." ).
                    address( "http://informagen.com/JarBundler/", "informagen" ).
                    apache2().
                    provides( "jarbundler-2.0.0.jar" ).
                    location( "build" ).
                    version( "2.0.0" );

        lib(JAVAGRAPHICS_PREFERENCEPANEL).
                    description( "A OSX style preference panel." ).
                    address( "https://javagraphics.dev.java.net/", DEV_JAVA).
                    bsd().
                    provides( "PreferencePanel.jar" );

        lib(JCIP_ANNOTATIONS).
                    description( "Concurrency Annotations. Compile time comments about concurrency intentions which Intellij understands." ).
                    address( "http://www.javaconcurrencyinpractice.com/", "javaconcurrencyinpractice" ).
                    provides( "jcip-annotations.jar");

        lib(JDIC).
                    description( "jdesctop library for e.g. opening files with their OS associated program" ).
                    address( "https://jdic.dev.java.net/releases.html", DEV_JAVA).
                    lgpl().
                    version( "0.9.1").
                    provides( "jdic.dll").
                    provides( "jdic.jar");

        lib(JGOODIES_BINDING).
                    description( "Twoway binding of domain objects and ui objects. Changes to the domain object are displayed by the ui object, changes to the ui object modify the domain object." ).
                    address( "http://www.jgoodies.com/downloads/libraries.html", "jgoodies").
                    bsd().
                    version( "2.0.6" ).
                    provides( "binding-2.0.6.jar" );

        lib(JGOODIES_FORMS).
                    description( "JGoodies Forms library," ).
                    address( "http://www.jgoodies.com/downloads/libraries.html", "jgoodies").
                    bsd().
                    version( "1.2.1" ).
                    provides( "forms-1.2.1.jar" );

//        lib(JMOCK).
//                    description( "A mocking library for java" ).
//                    address( "http://www.jmock.org/", "jmock.org").
//                    licence( "http://www.jmock.org/license.html", "jmock.com");

        other(JSMOOTH ).
                    description( "Win32 exe builder. (not in stroy google code)" ).
                    address( "http://jsmooth.sourceforge.net/", SOURCEFORGE).
                    gpl2().
                    version("0.9.9-7");

//        lib(JUNIT).
//                    description( "The Junit lib." ).
//                    address( "http://www.junit.org/", "junit.org").
//                    cpl().
//                    version( "4.4" );

        lib(MAC_WIDGETS).
                    description( "Library with OSX inspired swing components. e.g. a toolbar that blends in with the title bar, better buttons ..." ).
                    address( "http://code.google.com/p/macwidgets/", CODE_GOOGLE).
                    lgpl().
                    version( "0.9.5" ).
                    depends( JGOODIES_FORMS ).
                    provides( "mac_widgets-0.9.5.jar" );

        other(MD5).description( "Fast implementation of RSA's MD5 hash generator" ).
                    //de( "Efficiente und schnelle Implementation eines MD5 Generators" ).
                    //es( "Puesta en pr‡ctica r‡pida del generador del picadillo de MD5 de RSA." ).
                    address( "http://www.helsinki.fi/~sjpaavol/programs/md5", "helsinki.fi").
                    lgpl();

        lib(MUCHSOFT_SYS).
                    description( "Tool to help make java apps behave and look like native osx apps. e.g. Menus in menu bar, Preferences open on command-, ... " ).
                    //de("Eine Bibliothek mit der sich Java Programme wie richtige OSX Programme verhalten.").
                    address( "http://www.muchsoft.com/", "munchsoft" ).
                    licence( "http://www.muchsoft.com/java/docs/index.html", "Open, if unchanged" ).
                    provides("Sys.jar");

//        lib(MYDOGGY).
//                    description( "Java UI docking framework, IDEA like." ).
//                    de( "Eine Java Docking Library ala IDEA" ).
//                    address( "http://mydoggy.sourceforge.net/", SOURCEFORGE).
//                    lgpl().
//                    version( "1.3.1" ).
//                    runtime();

        other( RANDOM_GUID ).
                    description( "GUID generator").
                    //de( "Ein GUID Generator" ).
                    address( "http://www.javaexchange.com/", "javaexchange.com").
                    licence( "http://www.javaexchange.com/", "Open"  );

        lib(SWING_LAYOUT ).
                    description( "Extensions to Swing to create professional cross platform layouts (used by designgridlayout)").
                    address( "https://swing-layout.dev.java.net/", DEV_JAVA).
                    lgpl().
                    provides( "swing-layout-1.0.2.jar" );

        other(SWING_WORKER).
                    description( "Swingworker backported to java 1.5" ).
                    address( "https://SwingWorker.dev.java.net/", DEV_JAVA).
                    lgpl();

        lib(TABLELAYOUT).
                    description( "Swing table layout manager (used by mydoggy)" ).
                    //de( "Ein Swing tabelleb layout manager (von Mydoggy benutzt)" ).
                    address( "https://tablelayout.dev.java.net/", DEV_JAVA).
                    licence( "https://tablelayout.dev.java.net/files/documents/3495/59349/License.txt", "Clearthought" ).
                    provides( "TableLayout-20020517.jar" );

        other(URLUTF8_ENCODER ).
                    description( "Encodes strings url safe" ).
                    address( "http://www.w3.org/International/URLUTF8Encoder.java", "w3c").
                    licence( "http://www.w3.org/Consortium/Legal/2002/copyright-software-20021231", "W3C" );

        lib(XSTREAM).
                    description( "The java to XML serialization library." ).
                    address( "http://xstream.codehaus.org/", "codehaus" ).
                    bsd().
                    version("1.3.1").
                    provides( "xstream-1.3.1.jar" );

        module(DEPEND_APPLICATION).
                    description( "application description tool" ).
                    address( "http://code.google.com/p/stroy", CODE_GOOGLE).
                    mpl().
                    version("0.0.1").
                    depends(DEPEND_LANG).
                    depends(DEPEND_LOCALIZATION).
                    depends(DEPEND_IO).
                    depends(XSTREAM).
                    depends(JCIP_ANNOTATIONS).
                    depends(IDEA_ANNOTATIONS).
                    depends(COMMONS_IO);

        module(DEPEND_IO).
                    description( "io tools" ).
                    address( "http://code.google.com/p/stroy", CODE_GOOGLE).
                    mpl().
                    version("0.0.2").
                    depends(DEPEND_LANG).
                    depends(COMMONS_LANG).
                    depends(MUCHSOFT_SYS);

        module(DEPEND_FAUSTERIZE).
                    description( "encrypting texteditor" ).
                    address( "http://code.google.com/p/stroy", CODE_GOOGLE).
                    mpl().
                    version("0.7.0").
                    depends(DEPEND_LANG).
                    depends(MAC_WIDGETS).
                    depends(DESIGNGRIDLAYOUT).
                    depends(DEPEND_APPLICATION).
                    depends(COMMONS_IO).
                    depends(DEPEND_UI).
                    depends(ARGS4J).
                    build_depends( MOTPE );

        module(DEPEND_LOCALIZATION ).
                    description( "localization tools and dictionary" ).
                    address( "http://code.google.com/p/stroy", CODE_GOOGLE).
                    mpl().
                    version("0.0.1").
                    depends(DEPEND_PROPERTY);

        module(DEPEND_LANG).
                    description( "Java level utils" ).
                    address( "http://code.google.com/p/stroy", CODE_GOOGLE).
                    mpl().
                    version("0.0.4").
                    depends( JCIP_ANNOTATIONS ).
                    depends(GUICE);

        module(DEPEND_PROPERTY ).
                    description( "persistet properties" ).
                    address( "http://code.google.com/p/stroy", CODE_GOOGLE).
                    mpl().
                    version("0.0.2").
                    depends(COMMONS_IO).
                    depends(IDEA_ANNOTATIONS).
                    depends(XSTREAM).
                    depends(DEPEND_LANG);

        module(DEPEND_UI).
                    description( "ui tools" ).
                    address( "http://code.google.com/p/stroy", CODE_GOOGLE).
                    mpl().
                    version("0.0.2").
                    depends(DEPEND_LOCALIZATION).
                    depends(DEPEND_APPLICATION).
                    depends(DEPEND_IO).
                    depends(DESIGNGRIDLAYOUT).
                    depends(DEPEND_LANG).
                    depends(GUICE).
                    depends(JAVAGRAPHICS_PREFERENCEPANEL).
                    depends(JGOODIES_BINDING);

        other(MOTPE ).
                description( "March of the Pink Elephants: ant based build system to build all" ).
                address( "http://stroy.wikidot.com/motpe", "wikidot").
                depends( JARBUNDLER ).
                other_depends( PROJECT_DEPENDENCIES_USING_ANT ).
                other_depends( "AntDMG" );


        other(PROJECT_DEPENDENCIES_USING_ANT ).
                description( "Project Dependencies Using Ant" ).
                address( "http://www.exubero.com/ant/dependencies.html", "exubero.com" );

        other( "AntDMG" ).
                description( "Create osx DMGs from ant" ).
                address( "http://www.rkuntz.org/pmwiki.php?n=Code.AntDiskImage", "rkuntz.org" );




    }

    private Lib lib( String app ) {
        Lib ref = new Lib( app );
        refs.add( ref );
        return ref;
    }

    private Module module( String app ) {
        Module ref = new Module( app );
        refs.add( ref );
        return ref;
    }

    private Other other( String app ) {
        Other ref = new Other( app );
        refs.add( ref );
        return ref;
    }


}
