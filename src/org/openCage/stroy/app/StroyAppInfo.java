package org.openCage.stroy.app;

import org.openCage.util.app.AppInfo;
import org.openCage.util.app.Reference;
import org.openCage.util.app.Change;
import org.openCage.util.app.Version2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

@SuppressWarnings({"HardCodedStringLiteral"})
public class StroyAppInfo implements AppInfo {

    private final Version2        version         = new Version2( 0, 12, 0, 118 );
    private final List<Reference> refs            = new ArrayList<Reference>();
    private final List<Reference> buildRefs       = new ArrayList<Reference>();
    private final List<Change>    changes         = new ArrayList<Change>();
    private String                defaultVersion;


    public StroyAppInfo() {

        init();

        ref("MD5" ).description( "Fast implementation of RSA's MD5 hash generator" ).
                    de( "Efficiente und schnelle Implementation eines MD5 Gernerators" ).
                    es( "Puesta en pr‡ctica r‡pida del generador del picadillo de MD5 de RSA." ).
                    address( "http://www.helsinki.fi/~sjpaavol/programs/md5", "helsinki.fi").
                    lgpl().
                    typ( "runtime" );
        ref("guice" ).
                    description( "google dependency injection lib. no xml").
                    de("Google Dependecy Injection lib ohne XML").
                    address( "http://code.google.com/p/google-guice/", "code.google" ).
                    apache2().
                    version( "1.0" ).
                    typ( "runtime" );

        ref("RandomGUID" ).
                    description( "GUID generator").
                    de( "Ein GUID Generator" ).
                    address( "http://www.javaexchange.com/", "javaexchange.com").
                    licence( "http://www.javaexchange.com/", "Open"  ).
                    typ( "runtime" );
                
        ref( "Sys" ).
                    description( "Tool to help make java apps behave and look like native osx apps" ).
                    de("Eine lib mit der sich Java Programme wie richtige OSX Programme verhalten.").
                    address( "http://www.muchsoft.com/", "munchsoft" ).
                    licence( "http://www.muchsoft.com/java/docs/index.html", "Open if unchanged" ).
                    typ( "runtime" );
        
        ref("mydoggy" ).
                    description( "Java UI docking framework, IDEA like." ).
                    de( "Eine Java Docking Library ala IDEA" ).
                    address( "http://mydoggy.sourceforge.net/", "sourceforge" ).
                    lgpl().
                    version( "1.3.1" ).
                    typ( "runtime" );

        ref( "tablelayout" ).
                    description( "Swing table layout manager (used by mydoggy)" ).
                    de( "Ein Swing tabelleb layout manager (von Mydoggy benutzt)" ).
                    address( "https://tablelayout.dev.java.net/", "dev.java").
                    licence( "https://tablelayout.dev.java.net/files/documents/3495/59349/License.txt", "Clearthought" ).
                    typ( "runtime" );

        ref( "DesigngridLayout" ).
                    description( "Swing layout manager with user oriented layout strategy." ).
                    address( "https://designgridlayout.dev.java.net/", "dev.java" ).
                    lgpl().
                    version( "0.2" ).
                    typ( "runtime" );

        ref("swing-layout" ).
                    description( "Extensions to Swing to create professional cross platform layout (used by designgridlayout)").
                    address( "https://swing-layout.dev.java.net/", "dev.java" ).
                    lgpl().
                    typ( "runtime" );

        ref( "xstream" ).
                    description( "The java to XML serialization library" ).
                    address( "http://xstream.codehaus.org/", "codehaus" ).
                    bsd().
                    typ( "runtime" );

        ref("SwingWorker" ).
                    description( "Swingworker backported swingworker" ).
                    address( "https://SwingWorker.dev.java.net/", "dev.java").
                    lgpl().
                    version( "Java 5 Backport" ).
                    typ( "runtime" );

        ref("URLUTF8Encoder" ).
                    description( "encodes strings url safe" ).
                    address( "http://www.w3.org/International/URLUTF8Encoder.java", "w3c").
                    licence( "http://www.w3.org/Consortium/Legal/2002/copyright-software-20021231", "W3C" ).
                    typ( "runtime" );

        ref("jdic" ).
                    description( "jdesctop library for e.g. opening files with their OS associated program" ).
                    address( "https://jdic.dev.java.net/releases.html", "dev.java").
                    lgpl().
                    version( "0.9.1").
                    typ( "runtime" );

        ref("jarbundler" ).
                    description( "Ant task to create OSX bundles." ).
                    address( "http://informagen.com/JarBundler/", "informagen" ).
                    apache2().
                    version( "2.0.0" ).                
                    typ( "build" );

        ref("jsmooth" ).
                    description( "win32 exe builder. (Not in Google Code. Needs to be installed from the jsmooth webpage)" ).
                    address( "http://jsmooth.sourceforge.net/", "sourceforge").
                    gpl2().
                    version("0.9.9-7").
                    typ( "build" );

        ref("jmock" ).
                    description( "A mocking library for java" ).
                    address( "http://www.jmock.org/", "jmock.org").
                    licence( "http://www.jmock.org/license.html", "jmock" ).
                    typ( "test" );

        ref("junit" ).
                    description( "The Junit lib" ).
                    address( "http://www.junit.org/", "junit.org").
                    cpl().
                    version( "4.4" ).
                    typ( "test" );

        // JDIC


//        buildRefs.add( new Reference("java 5" ).
//                        description( "the language not the isle").
//                    address( "http://java.sun.com/"));
//        buildRefs.add( new Reference("intellij Idea 6" ).
//                    description( "nice java ide" ).
//                    address( "http://www.jetbrains.com/idea/"));
//        buildRefs.add( new Reference("junit 3.8" ).
//                    description( "unit testing framework").
//                    address( "http://www.junit.org/index.htm"));
//        buildRefs.add( new Reference("ant" ).
//                    description( "build system" ).
//                    address( "http://ant.apache.org/"));
//        buildRefs.add( new Reference("osx" ).
//                    description( "os" ).
//                    address( "http://www.apple.com"));

//        refs.add( new Reference("commons-cli" ).
//                    description( "apache command line api" ).
//                    address( "http://commons.apache.org/cli/").
//                    apache2());
//        refs.change( new Reference("quaqua", "java osx laf and tools", "http://www.randelshofer.ch/quaqua/download.html"));
    }


    public String getProgName() {
        return "Stroy";
    }

    public Collection<String> getAuthors() {
        return Collections.singleton( "Stephan Pfab");
    }

    public String getContactEmail() {
        return "openCage@gmail.com";
    }

    public String getWebpage() {
        return null;
    }

    public Collection<Reference> getReferences() {
        return refs;
    }

    public Collection<Reference> getBuildRefs() {
        return buildRefs;
    }

    public Version2 getVersion() {
        return version;
    }

    public String getCopyright() {
        return "Stephan Pfab, all rights reserved";
    }

    public String getDescription() {
        return "Dirdiff done right: Matches files even if content changed, renamed and moved";
    }

    public String getChangeLog() {
        return "TODO";
    }

    public String toString() {
        return getChangeLog();
    }

    public List<Change> getChanges() {
        return changes;
    }

    private Change change( int num, String typ, String title ) {
        Change ch = new Change( num, typ, title );
        if ( defaultVersion != null ) {
            ch.implemented( defaultVersion );
        }
        changes.add( ch );
        return ch;
    }

    private Reference ref( String app ) {
        Reference ref = new Reference( app );
        refs.add( ref );
        return ref;
    }


    private void init() {
        //change(  6, "f", "show selection in skybar" ).tag( "visualization" );
        //change(209, "F", "show current window in skybar" ).tag("ui").from("Mike").tag( "visualization" );
        //change(  7, "f", "keyboard navigation" ).tag("ui").tag("keys");
        change( 17, "f", "generate fuzzyhash file for bugreport" ).tag("test").tag("algo");
        //change( 39, "f", "total diff for 3 way" ).tag( "3way" );
        change( 51, "f", "std logfile" ).tag("feedback");
        change( 60, "f", "test database" ).tag("test");
        change( 64, "f", "measure balance between content and location" ).tag("test").tag("algo");
        change( 71, "d", "generate mf in ant (jar list just once)" ).tag("build");
        change( 18, "f", "create gui for bugreport" );
        change( 20, "f", "improve ruby match" ).tag("algo");
        change( 33, "d", "make button in refs work iff mailto: or http" );
        change( 46, "f", "buttons for go to next/prev change" ).tag("ui");
        change( 53, "d", "fix external 3 way diff" ).tag("3way");
        change( 56, "f", "error dialog" ).from( "Werner" ).tag("ui").tag("feedback");
        change( 76, "f", "improve progress reports, numbers" );
        change( 85, "f", "finish filter by path regex" );
        change( 93, "f", "file mode for dirs selection" );
        change( 94, "d", "finish filter for paths (also when reading in)" );
        change( 101, "f", "change methods to break match buttons" );
        change( 102, "f", "change methods to merging button" ).tag("merge");
        change( 104, "f", "special handling for more extensions" );
        change( 106, "f", "make summery dynamic" ).tag("feedback");
        change( 107, "f", "regex search for dir/file" ).tag("ui");
        change( 109, "f", "improve details" );
        change( 112, "f", "about: change image" );
        change( 114, "f", "more view concept" );
        change( 115, "f", "robust: unreadable files dirs: test plus dirs" );
        change( 120, "F", "store computes hashes per path" );
        change( 147, "d", "skyview click does not always jump to the right file" );
        change( 153, "F", "tooltip with detailed info" );
        change( 155, "F", "handle osx forcs, folders as files" ).tag("OSX");
        change( 157, "F", "color prefs" ).tag("ui");
        change( 160, "d", "large text file fuzzyHash needs work" ).tag("large");
        change( 163, "d", "help lib is not univeral" ).tag("build");
        change( 166, "F", "change standard editors text, pic ..." );
        change( 171, "F", "diff modes: e.g. paths only to find moves and onlys" );
        change( 177, "d", "different popup per type, e.g. dir (but osx bundles)" );
        change( 180, "d", "show in finder" ).tag("ui");
        change( 181, "d", "open in terminal" ).tag("ui");
        change( 182, "d", "win32: exec: cmd or arg with spaces does not work" ).
                from( "Chuan" ).
                tag("windows").
                comment("use JDIC. It has open /edit for windows. not so shure about OSX. Its state is also not quite clear").
                solve( "Thomas" );
        change( 183, "F", "a view to select match based on a threshhold" ).from( "Chuan" );

        change( 187, "F", "Ignore CR/LF difference" ).from( "Chuan" ).
                comment( "I won't go so far as to automatically ignore EOL differences. For once it is not easy for the algorithm " +
                        "and also conceptionally I see such files as changed. But thinning is a great idea." ).tag("thinning");
        change( 189, "f", "Grouping tree items by type first, instead of by name" ).from( "Chuan" );
        change( 190, "F", "Filter out identical items from the view" ).from( "Chuan" ).tag("thinning");
        change( 192, "d", "Shortcut key to diff: ctrl + click" ).from( "Chuan" );
//7.       Full string compare for identical files, so the user could confident to skip them
        // 197 again
        change( 193, "F", "Send a directory to diff" ).from( "Chuan" );
        change( 194, "-", "When click an item which is only present in one tree, it jumps to a parent folder in other tree, which is a bit confusing").from( "Chuan" );
//
        change( 195, "-", "\"Compare Again\" button").from( "Chuan" );
//        change( 196, )        //11.   Show tool tips when hovering over a tree item
        // shuold work already//
        change( 196, "f", "Double click to open a file").from( "Chuan" );
        change( 197, "F", "Select multiple tree items and send to diff once").from( "Chuan" );
        change( 198, "F", "Continuous diff mode: instance send to diff when a different item is clicked" ).from( "Chuan" );
        change( 199, "d", "Observe file system change and update view instantly" ).from( "Chuan").
                tag( "merge" );
        change( 200, "w", "Use gradually color change to show the degree of difference").from( "Chuan");
        change( 201, "F", "File Explorer integration: select / copy" ).from( "Chuan").
                tag( "merge" );
        change( 202, "F", "It tries to match files whose paths are greatly different" ).from( "Chuan").
                tag( "algo" );
        change( 204, "F", "redesign file types, standard filetype lib based on 'unix file'" );
        change( 205, "F", "redesign file types prefs to show file type default prefs and from prefs" ).ref(204);
        change( 207, "F", "non persistet filter out" ).tag( "thinning" ).tag( "ui" ).tag("algo").comment( "so you can remove stuff you checked already" );
        change( 210, "F", "show change state in extra skybar" ).tag( "merge" ).tag( "ui" ).tag("algo").
                comment( "it is orthogonal information" );
        change( 211, "F", "scm integration" ).tag( "scm integration" ).tag("merge").comment( "get the files from an scm instead of the disk. Get only changed files. Work with them" );
        change( 217, "d", "removing ignore has no immediate effect" ).tag("thinning");
        change( 219, "d", "web page localization" ).ref(218).tag("localization").tag("webpage");
        change( 220, "F", "help localization" ).ref(218).tag("localization");
        change( 221, "d", "make locale setable" ).tag("localization");
        change( 224, "F", "use webstart" ).from( "Thomas" );
        change( 234, "D", "silent error on open and diff" ).tag("feedback" ).tag("ui");
        change( 237, "f", "folders should show changes of their children transitive" ).tag("feedback" ).tag("ui");

        defaultVersion = "0.10.201";
        change( 246, "d", "default settings for linux is invalid");


        defaultVersion = "0.10.200";
        change( 244, "d", "even less confusing preference dialog, i.e. spelling and consistency");
        change( 245, "d", "select tree roots on startup");

        defaultVersion = "0.10.199";
        change( 238, "F", "licence switched to pure MPL1.1, GPL2 stuff not delivered anymore").tag( "licence").ref(239);
        change( 235, "d", "preference file type confusing" ).tag("feedback" ).tag("ui").from( "Miguel" );
        change( 236, "d", "preference external open confusing" ).tag("feedback" ).tag("ui").from( "Miguel" );
        change( 239, "f", "started google.code page for source");
        change( 240, "f", "update ui while update runs").tag("feedback").tag("ui").tag("large").ref(139);
        change( 241, "d", "progress dialog is hiding title").solve( "Mike" );
        change( 242, "F", "default progs for linux").
                comment( "this is really workaround based on xterm" ).tag( "linux" );
        change( 243, "d", "help fall back").
                comment( "if we can't find the help pages just go to the webpage." +
                        "Unix has no notion of started in dir. Should be classpath based" ).tag( "linux" );



        defaultVersion = "0.9.198";
        change( 229, "f", "localization for Spanish and German improved" ).solve( "Miguel" ).tag( "localization" );
        change( 172, "d", "change change value (don't missuse quality)" ).tag( "algo" );
        change( 191, "f", "Folder color should show child difference" ).from( "Chuan" ).ref(230).comment( "only moves and renames of direct children for now" ).ref(237);
        change( 230, "F", "use icons instead of color to show difference" ).ref(191);
        change( 231, "f", "designed icons for change display" ).ref(230);
        change( 232, "F", "Simple name Match first, diff calculation later").ref(139).tag( "large" ).tag( "perceived" ).tag("performance").tag("algo");
        change( 233, "F", "introduced 'matched, diff unknown' state").ref(232).tag( "large" ).tag( "perceived" ).tag("performance").tag("algo");

        defaultVersion = "0.9.197";
        change( 225, "d", "dir selection: cursor always jumps to the end " ).from( "Thomas F" );
        change( 226, "d", "jar file does not work beyond first dialog; app does" ).tag( "linux" ).comment("swing-worker missing in manifest");
        change( 227, "d", "forum: no anonymous posting allowed" ).tag( "feedback" );
        change( 228, "d", "diff file order depends on which tree is used" );

        defaultVersion = "0.9.196";
        change( 222, "d", "refactor control flow" ).tag("refactor");
        change( 139, "F", "show contents before match (good for big dirs)" ).from( "Mike" ).tag("large");
        change( 223, "d", "event and other threads" ).solve( "Mike" ).ref(139);

        defaultVersion = "0.9.195";
        change( 214, "f", "forum on wikidot" ).tag( "feedback" ).
                comment( "a public forum might be better than just email as feedback" ).tag("webpage").
                from( "Jacek" );
        change( 188, "f", "Synchronized collapsing tree items" ).from( "Chuan" );
        change( 216, "d", "ignore popup throws null pointer exception" ).comment( "aargh refactoring without test. Don't do it again" );
        change( 218, "F", "localization" ).tag("localization").comment( "German and Spanish first." +
                 "Started, spanish localization is probably very poor.");


        defaultVersion = "0.9.194";
        change( 184, "d", "more help files" ).tag( "feedback" );
        change( 185, "d", "pics in help files" ).tag("feedback");
        change( 186, "d", "web page in about and help" ).tag("feedback").tag("webpage");
        change( 203, "F", "change tracking, change web generation" ).tag("webpage").
                comment( "redesigned change tracking. It's now list of change objects with methods to generate nice web pages"
                         + "for Wikidot and text. There are tags and froms and change referals."
                         + "And it's kept in the source tree. Still in one package :-).");
        change( 208, "d", "skview logs errors when used on tiny dirs" ).tag( "ui" ).
                comment( "refactored BlockedBlocks, tested it. The problem was assuming that the strips are always bigger than height/num-of-nodes" );
        change( 206, "d", "screenshot on webpage" ).tag( "webpage" );
        change( 212, "d", "reference list redesign via generation and web page" ).tag( "feedback" ).tag("webpage").ref(203);
        change( 213, "d", "first published stroy" ).comment( "on freshmeat" );
        change( 215, "f", "wikidot is webpage for stroy" ).
                tag("webpage").
                from("Michael").
                comment( "the dual licencing prevented me from using code.google. Michael suggested Wikidot which works great so far." );


        defaultVersion = "0.8.193";
        change( 180, "d", "win32: normalize not working (wrong pattern)" ).tag("windows");
        change( 181, "d", "diff offered for unmatched files" );
        change( 90, "d", "improve menues (e.g. dialog are not modal and don't show menu)" ).tag("ui");


        defaultVersion = "0.8.192";
        change( 175, "d", "javadoc via ant" ).tag( "build" );
        change( 176, "d", "change standard editors text (>166)" );
        change( 174, "F", "change open with text editor (needs 166)" );
        change( 140, "d", "handle default diff for all platforms (no linux yet)" );
        change( 178, "F", "allow open of resource bundles, e.g. foo.pages (just open)" );
        change( 179, "d", "different popups for different file types" );
        change( 58, "f", "external diff for win" );
        change( 86, "f", "preset for external diff prog (i.e. make it configurable)" );

        defaultVersion = "0.8.191";
        change( 124, "F", "adapt to win" );
        change( 173, "d", "generate exe (jsmooth)" ).ref(124).tag("build").tag("windows");
        change( 144, "d", "win help" ).ref(124).tag("windows");


        defaultVersion = "0.8.190";
        change( 170, "d", "introduced Lazy class and refactored some Once usages" );
        change( 44, "f", "create osx help (duplicate)" ).ref(57).tag("OSX");
        change( 57, "F", "osx help" ).tag("OSX");
        change( 143, "d", "generate reference section for help" ).ref(57);

        defaultVersion = "0.8.189";
        change( 162, "d", "osx help does not always work (build.xml did not clean enough/ no univeral lib)" ).tag("OSX");
        change( 164, "d", ".css added to filetypes" );
        change( 165, "d", "standard progs pane needs description of std open" );
        change( 168, "d", "filetypes need set to standard button" );
        change( 167, "d", "prefs alogrithm buttons are not wired" );
        change( 168, "d", "select an treenode on startup" ).tag("ui");
        change( 169, "r", "refactoring to get rid of the idea of a source, now it's just one two ..." ).tag("algo");


        defaultVersion = "0.8.188";
        change( 161, "d", "prevent out mem error in FuzzyHashGenText" ).ref( 160 );
        change( 162, "d", "prevent out of mem handler in loghandlerPane" );
        change( 97, "f", "logwindow: logfile, limit text history" ).tag( "large" );

        defaultVersion = "0.8.187";
        change( 159, "d", "new logo in help" ).ref(158);

        defaultVersion = "0.8.186";
        change( 158, "F", "my own (i.e. copyright free) icon caterpillar -> butterfly" ).
                tag("licence").
                comment( "the old one used pics from the net, i.e. not licence save" );
        change( 113, "f", "more icon work" );

        defaultVersion = "0.8.185";
        change( 156, "d", "change background selection color to work on leopard" ).tag("ui").comment( "grey seams to work" );

        defaultVersion = "0.8.184";
        change( 154, "d", "tooltip for dirs" ).ref(153);
        change( 145, "F", "skyview for dir fold" ).tag("ui").comment( "new class" );


        defaultVersion = "0.8.183";
        change( 152, "d", "skyview for dir fold start" ).ref(145).tag("ui");

        defaultVersion = "0.8.182";
        change( 149, "d", "dir selector accepts illegal strings" ).tag( "ui" );
        change( 150, "F", "dir selector should use the dir from the other field as base" ).from("Mike").tag("ui");
        change( 151, "d", "get_date works by default now" ).ref(148).comment( "a '.' was missing in the regexp" );
        change( 148, "d", "popup filter sometimes does not work (e.g. alienbrain get_date...)" );

        defaultVersion = "0.8.181";
        change( 146, "F", "show current filepath" ).tag("feedback" );
        change( 141, "d", "handle newer (version) or corrupted prefs (duplicate)" ).ref(81);
        change( 81, "f", "robust: unreadable preset" ).tag("robust");

        defaultVersion = "0.8.180";
        change( 142, "d", "first osx style help" ).ref(57).tag("OSX");

        defaultVersion = "0.8.179";
        change( 138, "d", " log pref works again" );

        defaultVersion = "0.8.178";
        change( 128, "F", " configurable handling per file type" );
        change( 129, "d", "configurable diff tool" ).from( "Werner" ).ref(128);

        defaultVersion = "0.8.177";
        change( 136, "d", "filter removes nodes from skyview immediate " ).ref(177);
        change( 137, "d", "filter removes nodes from tasks immediate " ).ref(177);
        change( 117, "F", "Filter should filter the results on the fly" ).from( "Werner" );

        defaultVersion = "0.7.176";
        change( 135, "d", "filter removes nodes from tree immediate " ).ref(177);

        defaultVersion = "0.7.175";
        change( 127, "d", "win: use java dialog to get directories (explorer can't)" ).ref(124).tag("windows");
        change( 130, "d", "configurable similarity algoritm" ).ref(128);
        change( 131, "d", "configurable open setting" ).ref(128);
        change( 132, "d", "open for OSX" ).ref(128);
        change( 133, "d", "open for win32 (thanks Mike)" ).ref(128);
        change( 121, "r", "prefs to prefs in openCage.utils, actually preferences)" );
        change( 119, "d", "default preset for filters not working (again?)" ).from( "Werner" ).ref(96).ref(121);

        defaultVersion = "0.7.174";
        change( 116, "F", "Synchronized scrolling" ).from( "Werner" );
        change( 47, "d", "show selection in window midth" ).ref(116).tag("ui").comment(
                "click in skyview moves selection into the middle of the window" );

        defaultVersion = "0.6.173";
        change( 126, "d", " synchronized selection" ).ref(116);

        defaultVersion = "0.6.172";
        change(  8, "d", "adapt to win (started)" ).ref(124).tag("windows");
        change( 123, "d", "skyview adopted to windows" ).tag("windows");
        change( 125, "d", "filepath parsing adopted to windows" ).tag("windows");

        defaultVersion = "0.6.171";
        change( 122, "d", "prefs to prefs in openCage.utils" ).ref(121);

        defaultVersion = "0.6.170";
        change( 118, "f", ".get_date.dat to standard filter" ).from( "Werner" );

        defaultVersion = "0.6.169";
        change( 111, "f", "more icon work" ).ref(113);
        change( 110, "d", "summery of 3" );
        change( 108, "f", "view concept started" ).ref(114);

        defaultVersion = "0.6.168";
        change( 19, "f", "improve C++ match" ).tag( "algo" );
        change( 21, "f", "improve xml match" ).tag( "algo" );
        change( 41, "f", "better icon, e.g. transparent background" ).ref(111).
                comment( "used  Cocoflop");
        change( 80, "f", "robust: unreadable files dirs" ).ref(115).tag("robust");

        defaultVersion = "0.6.167";
        change( 98, "f", "set icon for all windows (worked already when deployed)" ).tag("OSX");
        change( 103, "f", "added more known extensions" ).tag("algo");
        change( 105, "d", "improved summery" ).ref(106);

        defaultVersion = "0.6.166";
        change( 96, "d", "ignore preset not active" ).ref(94);
        change( 78, "f", "improve log window" ).ref(97);
        change( 99, "d", "menu in main window too" );
        change( 42, "f", "break match/ do match methods started" ).ref(101);
        change(  2, "f", "merge, only button" ).ref(102);

        defaultVersion = "0.6.165";
        change( 91, "d", "dir dialog: go button not enables when values are preset" );
        change( 92, "d", "ignore cannot deal with unparsable patterns" );
        change( 95, "d", "log not set to start preset" );

        defaultVersion = "0.6.164";
        change( 89, "d", "upgrade to mydoggy 1.3.1 (no issue)" );
        change( 65, "d", "menues work only for main frame" ).ref(90);
        change( 79, "f", "finish prefs (bugs left)" );

        defaultVersion = "0.5.163";
        change( 84, "f", "presetpane buttons to open windows (used)" );
        change( 87, "f", "shutdownhook to save prefs" );
        change( 82, "f", "test area in filter pane" );
        change( 88, "d", "log view shows not last" );
        change( 100, "d", "handle unknown extensions: binary + warning" );


        defaultVersion = "0.5.162";
        change( 31, "f", "prefs: prefs started" ).ref(79);
        change(  3, "f", "filter by path regex started" ).ref(85);
        change( 83, "f", "preset = prefs (OSX)" );

        defaultVersion = "0.5.161";
        change( 74, "f", "better progress detail for longrunnig diffs" ).from( "Werner" ).ref(76).tag("large");
        change( 77, "f", "start general log window" ).ref(78).tag("feedback");
        change( 50, "f", "duplicate (77)" ).ref(77);

        defaultVersion = "0.5.160";
        change( 73, "f", "bugreport shows progname and version" );
        change( 75, "f", "send feature request" ).tag("feedback");
        change( 72, "f", "mailto string works with subject and body" );
        defaultVersion = "0.5.159";
        change( 68, "d", "fix mydoggy popup layout" ).comment( "thanxs Jason for designgrid 0.2)" ).
                tag("ui").solve( "Jason" );
        change( 69, "d", "redesign jar dir layout and ant" ).tag("build");
        change( 59, "d", "fix mydoggy popup layout (duplicate)" ).ref(68);
        defaultVersion = "0.5.158";
        defaultVersion = "0.5.157";
        change( 66, "d", "refactor colors" ).tag("visualization");
        defaultVersion = "0.5.156";
        change( 64, "d", "too much weight on content see FileDistance.java in 04" ).tag("algo");
        change( 63, "d", "oops parent never mattered" ).tag("algo");
// refactor
        defaultVersion = "0.5.155";
// refactor matching graph -> task (to many graphs )
        change( 61, "d", "hierarchical dir match: do match" ).tag("algo");
        change(  5, "d", "match renamed java dirs" ).tag("algo");
        defaultVersion = "0.5.154";
        change( 55, "d", "2 way screen" ).from( "Werner" );
        change( 54, "d", "content fuzzy equal == 1 != identical" ).tag("algo");
        defaultVersion = "0.5.152";
        change( 49, "f", "spawn external diff/merge tool plus button" );
        defaultVersion = "0.5.151";
// refactor ui again
        change(  1, "f", "show selection diff in extra panel" ).tag("ui");
        change( 52, "f", "use docking ui" ).tag("ui");
        defaultVersion = "0.4.143";
        change( 48, "d", "clear the selection when showing match of only" ).tag("ui");
        defaultVersion = "0.4.142";
        change( 43, "f", "make show help view readonly" ).tag("feedback");
        defaultVersion = "0.4.141";
        change( 22, "f", "split using refrences into extra panel" );
        change( 32, "f", "help screen" ).tag("feedback");
        defaultVersion = "0.4.140";
        change( 13, "f", "changelog in gui" ).tag("feedback");
        defaultVersion = "0.4.138";
        change(  4, "f", "build osx app (ant)" ).tag( "build" ).tag("OSX").comment( "jarbuilder" );
        change( 10, "f", "contact address in gui" ).tag("feedback");
        change( 11, "f", "copyright in gui" ).tag("feedback");
        change( 35, "f", "track versions" );
        change( 36, "f", "scrollpanes middled" ).tag("ui");
        change( 37, "f", "left scrollbar on left" ).tag("ui");
        change( 40, "f", "osx icon" ).tag("OSX");
        defaultVersion = "0.3.110";
        change(  9, "f", "3 way diff" ).tag("algo");
        change( 27, "f", "skyviewbar" ).tag( "ui" ).tag( "visualization" ).
                comment( "a bar of colored strips to show the changes of the whole document at once." );
        change( 29, "f", "match selection to parent of only file" ).tag( "algo" );
        change( 45, "f", "use color as info for changes" ).tag("visualization");
        defaultVersion = "0.2.100";
        change( 12, "f", "gui" ).comment( "First simple gui. 2 trees.");
        change( 28, "f", "match selection" );
        change( 38, "f", "designgridlayout" ).comment( "A nice layout manager.");
        defaultVersion = "0.1.070";
        change( 14, "f", "cli" );
        change( 15, "f", "deploy through ant" ).comment( "Deploy into one external dir." );
        change( 16, "f", "guice" ).comment( "IoC without XML, nice " );
        change( 23, "f", "match duplicates" ).
                comment( "I turns out relying on checksums produces strange results for duplicates,\n" +
                        "i.e. sometimes they are matched correctly but sometimes they are switched.\n" +
                        "The idea to ignore the path when the content is identical is seductive but later \n" +
                        "dropped.");
        change( 24, "f", "match unchanged dirs" ).comment("SimpleDirMatch");
        change( 25, "f", "match changed dirs" ).comment( "Hierarchical Matching?");
        change( 26, "f", "improve match for small files" ).comment(
                "This is supposed to give better similarity values for the case that you start with small standard file and change it\n" +
                        "heavily. The whole idea might be wrong see later bugs (TODO). Maybe we should just look at the context and\n" +
                        "assume same name and path we'll do the rest for this specific case.");
        change( 30, "f", "ant" ).comment( "Build with ant. First step towards a full build environment.");
        change( 34, "f", "match unchanged files with md5 (fast md5)" );
        change( 67, "f", "ignore white noise in java lines" ).comment( "The implementation is pretty radical and ignores all spaces even if they separate. But we are talking heuristic\n" +
                "anyway. So the easier implementation and speed make well up for the unlikely case that this will produce \n" +
                "many identical checksums for unequal lines. ");

    }
}
