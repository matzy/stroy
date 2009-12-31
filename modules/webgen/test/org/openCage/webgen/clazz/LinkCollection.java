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
    private List<Reference> refs = new ArrayList<Reference>();

    public LinkCollection() {
        fill();
    }

    public List<Reference> getRefs() {
        return refs;
    }


    private void fill() {
        ref("ant-contrib" ).
                    description( "Ant task collection. Includes if-task" ).
                    address( "http://ant-contrib.sourceforge.net/", "sourceforge.net" ).
                    apache2().
                    version( "1.0b3" ).
                    typ( "build" );

        ref("commons-cli" ).
                    description( "Apache command line tools." ).
                    address( "http://commons.apache.org/cli", "apache").
                    apache2().
                    version( "1.2" ).
                    typ( "runtime" );

        ref("commons-io" ).
                    description( "Apache IO tools. e.g. tools to cleanly close resources." ).
                    address( "http://commons.apache.org/io", "apache").
                    apache2().
                    version( "1.4" ).
                    libName( "commons-io-1.4.jar" ).
                    typ( "runtime" );

        ref("commons-lang" ).
                    description( "Apache java language level tools. Random number helpers, system property tools, string tools." ).
                    address( "http://commons.apache.org/lang/", "apache").
                    apache2().
                    version( "2.4" ).
                    libName("commons-lang-2.4.jar").
                    typ( "runtime" );

        ref("commons-vfs" ).
                    description( "Apache java library for virtual file systems." ).
                    address( "http://commons.apache.org/vfs/", "apache").
                    apache2().
                    version( "1.0" ).
                    typ( "runtime" );

        ref( "designgridlayout" ).
                    description( "Swing layout manager based on the use of canonical grids for user interface design." ).
                    address( "https://designgridlayout.dev.java.net/", "dev.java" ).
                    lgpl().
                    version( "0.9" ).
                    libName( "designgridlayout-0.9.jar" ).
                    depends( "swing-layout" ).
                    typ( "runtime" );

        ref("guice" ).
                    description( "Google dependency injection lib in pure java.").
                    de("Google Dependecy Injection lib ohne XML").
                    address( "http://code.google.com/p/google-guice/", "code.google" ).
                    apache2().
                    version( "2.0" ).
                    libName( "guice-2.0.jar").
                    typ( "runtime" );

        ref("idea-annotations" ).
                    description( "Nullable annotations (bundled with Idea CE)" ).
                    address( "http://www.jetbrains.com/index.html", "jetbrains").
                    apache2().
                    libName( "annotations.jar").
                    typ( "runtime" );

        ref("jarbundler" ).
                    description( "Ant task to create OSX bundles." ).
                    address( "http://informagen.com/JarBundler/", "informagen" ).
                    apache2().
                    version( "2.0.0" ).
                    typ( "build" );

        ref("javagraphics-preferencepanel" ).
                    description( "A OSX style preference panel." ).
                    address( "https://javagraphics.dev.java.net/", "dev.java" ).
                    bsd().
                    typ( "runtime" );

        ref("jcip-annotations" ).
                    description( "Concurrency Annotations. Compile time comments about concurrency intentions which Intellij understands." ).
                    address( "http://www.javaconcurrencyinpractice.com/", "javaconcurrencyinpractice" ).
                    libName( "jcip-annotations.jar").
                    typ( "runtime" );

        ref("jdic" ).
                    description( "jdesctop library for e.g. opening files with their OS associated program" ).
                    address( "https://jdic.dev.java.net/releases.html", "dev.java").
                    lgpl().
                    version( "0.9.1").
                    typ( "runtime" );

        ref("jgoodies-binding" ).
                    description( "Twoway binding of domain objects and ui objects. Changes to the domain object are displayed by the ui object, changes to the ui object modify the domain object." ).
                    address( "http://www.jgoodies.com/downloads/libraries.html", "jgoodies").
                    bsd().
                    version( "2.0.6" ).
                    typ( "runtime" );

        ref("jgoodies-forms" ).
                    description( "JGoodies Forms library," ).
                    address( "http://www.jgoodies.com/downloads/libraries.html", "jgoodies").
                    bsd().
                    version( "1.2.1" ).
                    typ( "runtime" );

        ref("jmock" ).
                    description( "A mocking library for java" ).
                    address( "http://www.jmock.org/", "jmock.org").
                    licence( "http://www.jmock.org/license.html", "jmock" ).
                    typ( "test" );

        ref("jsmooth" ).
                    description( "Win32 exe builder. (not in stroy google code)" ).
                    address( "http://jsmooth.sourceforge.net/", "sourceforge").
                    gpl2().
                    version("0.9.9-7").
                    typ( "build" );

        ref("junit" ).
                    description( "The Junit lib." ).
                    address( "http://www.junit.org/", "junit.org").
                    cpl().
                    version( "4.4" ).
                    typ( "test" );

        ref("mac-widgets" ).
                    description( "Library with OSX inspired swing components. e.g. a toolbar that blends in with the title bar, better buttons ..." ).
                    address( "http://code.google.com/p/macwidgets/", "code.google").
                    lgpl().
                    version( "0.9.5" ).
                    libName( "mac_widgets-0.9.5.jar" ).
                    typ( "runtime" );

        ref("MD5" ).description( "Fast implementation of RSA's MD5 hash generator" ).
                    de( "Efficiente und schnelle Implementation eines MD5 Generators" ).
                    es( "Puesta en pr‡ctica r‡pida del generador del picadillo de MD5 de RSA." ).
                    address( "http://www.helsinki.fi/~sjpaavol/programs/md5", "helsinki.fi").
                    lgpl().
                    typ( "runtime" );

        ref( "muchsoft-sys" ).
                    description( "Tool to help make java apps behave and look like native osx apps. e.g. Menus in menu bar, Preferences open on command-, ... " ).
                    de("Eine Bibliothek mit der sich Java Programme wie richtige OSX Programme verhalten.").
                    address( "http://www.muchsoft.com/", "munchsoft" ).
                    licence( "http://www.muchsoft.com/java/docs/index.html", "Open, if unchanged" ).
                    libName("Sys.jar").
                    typ( "runtime" );

        ref("mydoggy" ).
                    description( "Java UI docking framework, IDEA like." ).
                    de( "Eine Java Docking Library ala IDEA" ).
                    address( "http://mydoggy.sourceforge.net/", "sourceforge" ).
                    lgpl().
                    version( "1.3.1" ).
                    typ( "runtime" );

        ref("RandomGUID" ).
                    description( "GUID generator").
                    de( "Ein GUID Generator" ).
                    address( "http://www.javaexchange.com/", "javaexchange.com").
                    licence( "http://www.javaexchange.com/", "Open"  ).
                    typ( "runtime" );

        ref("swing-layout" ).
                    description( "Extensions to Swing to create professional cross platform layouts (used by designgridlayout)").
                    address( "https://swing-layout.dev.java.net/", "dev.java" ).
                    lgpl().
                    libName( "swing-layout-1.0.2.jar" ).
                    typ( "runtime" );

        ref("SwingWorker" ).
                    description( "Swingworker backported to java 1.5" ).
                    address( "https://SwingWorker.dev.java.net/", "dev.java").
                    lgpl().
                    typ( "runtime" );

        ref( "tablelayout" ).
                    description( "Swing table layout manager (used by mydoggy)" ).
                    de( "Ein Swing tabelleb layout manager (von Mydoggy benutzt)" ).
                    address( "https://tablelayout.dev.java.net/", "dev.java").
                    licence( "https://tablelayout.dev.java.net/files/documents/3495/59349/License.txt", "Clearthought" ).
                    typ( "runtime" );

        ref("URLUTF8Encoder" ).
                    description( "Encodes strings url safe" ).
                    address( "http://www.w3.org/International/URLUTF8Encoder.java", "w3c").
                    licence( "http://www.w3.org/Consortium/Legal/2002/copyright-software-20021231", "W3C" ).
                    typ( "runtime" );

        ref( "xstream" ).
                    description( "The java to XML serialization library." ).
                    address( "http://xstream.codehaus.org/", "codehaus" ).
                    bsd().
                    version("1.3.1").
                    libName( "xstream-1.3.1" ).
                    typ( "runtime" );

        ref( "depend.application" ).
                    description( "application description tool" ).
                    address( "http://code.google.com/p/stroy", "code.google" ).
                    mpl().
                    version("0.0.1").
                    depends( "depend.lang" ).
                    depends( "depend.localization" ).
                    depends( "depend.io" ).
                    depends( "xstream" ).
                    depends( "jcip-annotations" ).
                    depends( "idea-annotations" ).
                    depends( "commons-io" ).
                    libName( "opencage-io??.jar" ).
                    typ( "internal" );

        ref( "depend.io" ).
                    description( "io tools" ).
                    address( "http://code.google.com/p/stroy", "code.google" ).
                    mpl().
                    version("0.0.1").
                    depends( "depend.lang" ).
                    depends( "muchsoft-sys" ).
                    libName( "opencage-io??.jar" ).
                    typ( "internal" );

        ref( "depend.localization" ).
                    description( "localization tools and dictionary" ).
                    address( "http://code.google.com/p/stroy", "code.google" ).
                    mpl().
                    version("0.0.1").
                    depends( "depend.property" ).
                    libName( "opencage-localization??.jar" ).
                    typ( "internal" );

        ref( "depend.lang" ).
                    description( "Java level utils" ).
                    address( "http://code.google.com/p/stroy", "code.google" ).
                    mpl().
                    version("0.0.3").
                    depends( "guice" ).
                    libName( "opencage-lang??.jar" ).
                    typ( "internal" );

        ref( "depend.property" ).
                    description( "persistet properties" ).
                    address( "http://code.google.com/p/stroy", "code.google" ).
                    mpl().
                    version("0.0.1").
                    depends( "commons-io" ).
                    depends( "idea-annotations" ).
                    depends( "xstream" ).
                    depends( "depend.lang" ).
                    libName( "opencage-property??.jar" ).
                    typ( "internal" );

        ref( "depend.ui" ).
                    description( "ui tools" ).
                    address( "http://code.google.com/p/stroy", "code.google" ).
                    mpl().
                    version("0.0.1").
                    depends( "depend.localization" ).
                    depends( "depend.application" ).
                    depends( "depend.io" ).
                    depends( "designgridlayout" ).
                    depends( "depend.lang" ).
                    depends( "guice" ).
                    depends( "javagraphics-preferencepanel" ).
                    depends( "jgoodies-binding" ).
                    libName( "opencage-ui??.jar" ).
                    typ( "internal" );

    }

    private Reference ref( String app ) {
        Reference ref = new Reference( app );
        refs.add( ref );
        return ref;
    }


}
