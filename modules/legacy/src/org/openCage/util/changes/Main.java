//package org.openCage.util.changes;
//
//
//import org.openCage.util.app.Change;
//import org.openCage.util.www.wikidot.WikiDotGen;
//
//import java.util.*;
//
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
//public class Main {
//
//    private static List<Change> changes = new ArrayList<Change>();
//    private static String defaultVersion;
//    private static Set<String> tags = new HashSet<String>( );
//    private static Set<String> froms = new HashSet<String>( );
//
//    public static void main( String[] args ) {
//
//        list();
//        computeFroms();
//        computeTags();
//
//        printTOC();
//
//        printByImpl();
//        printByNumber();
//        printByPerson();
//        printByTag();
//
//    }
//
//    private static void printTOC() {
//
//        System.out.println( WikiDotGen.link( "ChangeLog", "ChangeLog" ) );
//        System.out.println( WikiDotGen.link( "ChangeDetails", "Change Details" ) );
//        System.out.println( WikiDotGen.link( "ChangeByReporter", "Change by Reporter" ) );
//        System.out.print( "> " );
//        for ( String user : froms ) {
//            System.out.print( " " + WikiDotGen.link( user, user)    );
//        }
//        System.out.println( "" );
//
//        System.out.println( " " + WikiDotGen.link( "ChangeByTag", "Change by Tag" ) );
//        System.out.print( "> " );
//        for ( String tag : tags ) {
//            System.out.print( " " + WikiDotGen.link( tag, tag)    );
//        }
//
//        System.out.println( "" );
//        System.out.println( "" );
//
//    }
//
//    private static Change add( int num, String typ, String title ) {
//        Change ch = new Change( num, typ, title );
//        if ( defaultVersion != null ) {
//            ch.implemented( defaultVersion );
//        }
//        changes.add( ch );
//        return ch;
//    }
//
//    static class NumberComparator implements Comparator<Change> {
//
//        public int compare( Change o1, Change o2 ) {
//            return o2.num - o1.num;
//        }
//    }
//
//    private static void printByNumber() {
//        System.out.println();
//        System.out.println( WikiDotGen.ancor( "ChangeDetails" ) );
//        System.out.println( "+ Change Details" );
//
//        Collections.sort( changes, new NumberComparator() );
//
//        for ( Change change : changes ) {
//            change.printWikiAncor();
//            System.out.println("");
//        }
//    }
//
//
//    private static void printByPerson() {
//        System.out.println();
//        System.out.println( WikiDotGen.ancor( "ChangeByReporter" ) );
//        System.out.println( "+ Change by Reporter" );
//
//        Collections.sort( changes, new VersionComparator() );
//
//        for ( String from : froms ) {
//            System.out.println( WikiDotGen.ancor( from ));
//            System.out.println("+++ " + from );
//            System.out.println("++++ Open");
//            boolean impl = false;
//
//            for ( Change change : changes ) {
//                if ( change.isFrom(from)) {
//                    if ( !impl && change.version != null ) {
//                        System.out.println("++++ Done");
//                        impl = true;
//                    }
//                    change.printWikiLink();
//                }
//            }
//        }
//
//    }
//
//    private static void printByTag() {
//        System.out.println();
//        System.out.println( WikiDotGen.ancor( "ChangeByTag" ) );
//        System.out.println( "+ Change by Tag" );
//
//        Collections.sort( changes, new VersionComparator() );
//
//
//        for ( String tag : tags ) {
//            System.out.println( WikiDotGen.ancor( tag ));
//            System.out.println("+++ " + tag );
//            System.out.println("++++ Open");
//            boolean impl = false;
//
//            for ( Change change : changes ) {
//                if ( change.hasTag(tag)) {
//                    if ( !impl && change.version != null ) {
//                        System.out.println("++++ Done");
//                        impl = true;
//                    }
//                    change.printWikiLink();
//                }
//            }
//        }
//
//    }
//
//    private static void computeFroms() {
//        for ( Change change : changes ) {
//            froms.addAll( change.getFroms() );
//        }
//    }
//
//    private static void computeTags() {
//        for ( Change change : changes ) {
//            tags.addAll( change.tags );
//        }
//    }
//
//    static class VersionComparator implements Comparator<Change> {
//
//        public int compare( Change o1, Change o2 ) {
//            if ( o1.version == o2.version ) {
//                return o2.num - o1.num;
//            } else if ( o2.version == null ) {
//                return 1;
//            } else if ( o1.version == null ) {
//                return -1;
//            } else {
//                return Version2.parseVersion( o2.version ).compareTo( Version2.parseVersion( o1.version ));
//            }
//        }
//    }
//
//    private static void printByImpl() {
//
//        System.out.println();
//        System.out.println( WikiDotGen.ancor( "ChangeLog" ) );
//        System.out.println( "+ ChangeLog" );
//
//        Collections.sort( changes, new VersionComparator() );
//
//        String currentVersion = null;
//
//        for ( Change change : changes ) {
//
//            if ( change.version != currentVersion ) {
//                currentVersion = change.version;
//                System.out.println("");
//                change.printWikiVersionAncor();
//            }
//
//            if ( currentVersion != null ) {
//                change.printWikiLink();
//            }
//        }
//    }
//
//    private static void list() {
//        // 206
//
//        add(  6, "f", "show selection in skybar" ).tag( "visualization" );
//        add(209, "F", "show obj window in skybar" ).tag("ui").from("Mike").tag( "visualization" );
//        add(  7, "f", "keyboard navigation" ).tag("ui").tag("keys");
//        add( 17, "f", "generate fuzzyhash file for bugreport" ).tag("test").tag("algo");
//        add( 39, "f", "total diff for 3 way" ).tag( "3way" );
//        add( 51, "f", "std logfile" ).tag("feedback");
//        add( 60, "f", "test database" ).tag("test");
//        add( 64, "f", "measure balance between content and location" ).tag("test").tag("algo");
//        add( 71, "d", "generate mf in ant (jar list just once)" ).tag("build");
//        add( 18, "f", "get gui for bugreport" );
//        add( 20, "f", "improve ruby match" ).tag("algo");
//        add( 33, "d", "make button in refs work iff mailto: or http" );
//        add( 46, "f", "buttons for go to next/prev change" ).tag("ui");
//        add( 53, "d", "fix external 3 way diff" ).tag("3way");
//        add( 56, "f", "error dialog" ).from( "Werner" ).tag("ui").tag("feedback");
//        add( 76, "f", "improve progress reports, numbers" );
//        add( 85, "f", "finish filter by path regex" );
//        add( 93, "f", "file mode for dirs selection" );
//        add( 94, "d", "finish filter for paths (also when reading in)" );
//        add( 101, "f", "add methods to break match buttons" );
//        add( 102, "f", "add methods to merging button" ).tag("merge");
//        add( 104, "f", "special handling for more extensions" );
//        add( 106, "f", "make summary dynamic" ).tag("feedback");
//        add( 107, "f", "regex search for dir/file" ).tag("ui");
//        add( 109, "f", "improve details" );
//        add( 112, "f", "about: add image" );
//        add( 114, "f", "more view concept" );
//        add( 115, "f", "robust: unreadable files dirs: test plus dirs" );
//        add( 120, "F", "store computes hashes per path" );
//        add( 139, "F", "show contents before match (good for big dirs)" ).from( "Mike" ).tag("large");
//        add( 147, "d", "skyview click does not always jump to the right file" );
//        add( 153, "F", "tooltip with detailed info" );
//        add( 155, "F", "handle osx forcs, folders as files" ).tag("OSX");
//        add( 157, "F", "color prefs" );
//        add( 160, "d", "large text file fuzzyHash needs work" ).tag("large");
//        add( 163, "d", "help lib is not univeral" );
//        add( 166, "F", "add standard editors text, pic ..." );
//        add( 171, "F", "diff modes: e.g. paths only to find moves and onlys" );
//        add( 172, "F", "add change value (don't missuse quality)" );
//        add( 177, "d", "different popup per type, e.g. dir (but osx bundles)" );
//        add( 180, "d", "show in finder" ).tag("ui");
//        add( 181, "d", "open in terminal" ).tag("ui");
//        add( 182, "d", "win32: exec: cmd or arg with spaces does not work" ).from( "Chuan" ).tag("windows");
//        add( 183, "F", "a view to select match based on a threshhold" ).from( "Chuan" );
//
//        add( 187, "F", "Ignore CR/LF difference" ).from( "Chuan" );
//        add( 188, "f", "Synchronized collapsing tree items" ).from( "Chuan" );
//        add( 189, "f", "Grouping tree items by type first, instead of by name" ).from( "Chuan" );
//        add( 190, "F", "Filter out identical items from the view" ).from( "Chuan" ).tag("thinning");
//        add( 191, "f", "Folder color should show child difference" ).from( "Chuan" );
//        add( 192, "d", "Shortcut key to diff: ctrl + click" ).from( "Chuan" );
////7.       Full string compare for identical files, so the user could confident to skip them
//        // 197 again
//        add( 193, "F", "Send a directory to diff" ).from( "Chuan" );
//        add( 194, "-", "When click an item which is only present in one tree, it jumps to a parent folder in other tree, which is a bit confusing").from( "Chuan" );
////
//        add( 195, "-", "\"Compare Again\" button").from( "Chuan" );
////        add( 196, )        //11.   Show tool tips when hovering over a tree item
//        // shuold work already//
//        add( 196, "f", "Double click to open a file").from( "Chuan" );
//        add( 197, "F", "Select multiple tree items and send to diff once").from( "Chuan" );
//        add( 198, "F", "Continuous diff mode: instance send to diff when a different item is clicked" ).from( "Chuan" );
//        add( 199, "d", "Observe file system change and update view instantly" ).from( "Chuan").
//                tag( "merge" );
//        add( 200, "w", "Use gradually color change to show the degree of difference").from( "Chuan");
//        add( 201, "F", "File Explorer integration: select / copy" ).from( "Chuan").
//                tag( "merge" );
//        add( 202, "F", "It tries to match files whose paths are greatly different" ).from( "Chuan").
//                tag( "algo" );
//        add( 204, "F", "redesign file types, standard filetype lib based on 'unix file'" );
//        add( 205, "F", "redesign file types prefs to show file type defualt prefs and froms prefs" ).ref(204);
//        add( 207, "F", "non persistet filter out" ).tag( "thinning" ).tag( "ui" ).tag("algo").comment( "so you can remove stuff you checked already" );
//        add( 210, "F", "show change state in extra skybar" ).tag( "merge" ).tag( "ui" ).tag("algo").
//                comment( "it is orthogonal information" );
//        add( 211, "F", "scm integration" ).tag( "scm integration" ).tag("merge").comment( "get the files from an sacm instead of the disk. Get only changed files. Work with them" );
//
//        defaultVersion = "0.9.194";
//        add( 184, "d", " more help files" );
//        add( 185, "d", " pics in help files" );
//        add( 186, "d", " web page in about and help" );
//        add( 203, "F", "change tracking, add web generation" ).
//                comment( "redesigned change tracking. It's now list of change objects with methods to generate nice web pages"
//                         + "for Wikidot and text. There are tags and froms and change referals."
//                         + "And it's kept in the source tree. Still in one package :-).");
//        add( 208, "d", "skview logs errors when used on tiny dirs" ).tag( "ui" ).
//                comment( "refactored BlockedBlocks, tested it. The problem was assuming that the strips are always bigger than height/num-of-nodes" );
//        add( 206, "d", "add screenshots to webpage" ).tag( "feedback" );
//
//        defaultVersion = "0.8.193";
//        add( 180, "d", "win32: normalize not working (wrong pattern)" ).tag("windows");
//        add( 181, "d", "diff offered for unmatched files" );
//        add( 90, "d", " improve menues (e.g. dialog are not modal and don't show menu)" );
//
//
//        defaultVersion = "0.8.192";
//        add( 175, "d", " javadoc via ant" );
//        add( 176, "d", " add standard editors text (>166)" );
//        add( 174, "F", " add open with text editor (needs 166)" );
//        add( 140, "d", " handle default diff for all platforms (no linux yet)" );
//        add( 178, "F", " allow open of resource bundles, e.g. foo.pages (just open)" );
//        add( 179, "d", " different popups for different file types" );
//        add( 58, "f", " external diff for win" );
//        add( 86, "f", " preset for external diff prog (i.e. make it configurable)" );
//
//        defaultVersion = "0.8.191";
//        add( 124, "F", "adapt to win" );
//        add( 173, "d", "generate exe (jsmooth)" ).ref(124).tag("build").tag("windows");
//        add( 144, "d", "win help" ).ref(124).tag("windows");
//
//
//        defaultVersion = "0.8.190";
//        add( 170, "d", "introduced Lazy class and refactored some Once usages" );
//        add( 44, "f", "get osx help (duplicate)" ).ref(57).tag("OSX");
//        add( 57, "F", "osx help" ).tag("OSX");
//        add( 143, "d", "generate reference section for help" ).ref(57);
//
//        defaultVersion = "0.8.189";
//        add( 162, "d", "osx help does not always work (build-lang.xml did not clean enough/ no univeral lib)" ).tag("OSX");
//        add( 164, "d", ".css added to filetypes" );
//        add( 165, "d", "standard progs pane needs description of std open" );
//        add( 168, "d", "filetypes need set to standard button" );
//        add( 167, "d", "prefs alogrithm buttons are not wired" );
//        add( 168, "d", "select an treenode on startup" ).tag("ui");
//        add( 169, "r", "refactoring to get rid of the idea of a source, now it's just one two ..." ).tag("algo");
//
//
//        defaultVersion = "0.8.188";
//        add( 161, "d", "prevent out mem error in FuzzyHashGenText" ).ref( 160 );
//        add( 162, "d", "prevent out of mem handler in loghandlerPane" );
//        add( 97, "f", "logwindow: logfile, limit text history" ).tag( "large" );
//
//        defaultVersion = "0.8.187";
//        add( 159, "d", "new logo in help" ).ref(158);
//
//        defaultVersion = "0.8.186";
//        add( 158, "F", "my own (i.e. copyright free) icon caterpillar -> butterfly" ).
//                tag("licence").
//                comment( "the old one used pics from the net, i.e. not licence save" );
//        add( 113, "f", "more icon work" );
//
//        defaultVersion = "0.8.185";
//        add( 156, "d", "change background selection color to work on leopard" ).tag("ui").comment( "grey seams to work" );
//
//        defaultVersion = "0.8.184";
//        add( 154, "d", "tooltip for dirs" ).ref(153);
//        add( 145, "F", "skyview for dir fold" ).tag("ui").comment( "new class" );
//
//
//        defaultVersion = "0.8.183";
//        add( 152, "d", "skyview for dir fold start" ).ref(145).tag("ui");
//
//        defaultVersion = "0.8.182";
//        add( 149, "d", "dir selector accepts illegal strings" ).tag( "ui" );
//        add( 150, "F", "dir selector should use the dir from the other field as base" ).from("Mike").tag("ui");
//        add( 151, "d", "get_date works by default now" ).ref(148).comment( "a '.' was missing in the regexp" );
//        add( 148, "d", "popup filter sometimes does not work (e.g. alienbrain get_date...)" );
//
//        defaultVersion = "0.8.181";
//        add( 146, "F", " show obj filepath" );
//        add( 141, "d", " handle newer (version) or corrupted prefs (duplicate)" ).ref(81);
//        add( 81, "f", " robust: unreadable preset" );
//
//        defaultVersion = "0.8.180";
//        add( 142, "d", "first osx style help" ).ref(57);
//
//        defaultVersion = "0.8.179";
//        add( 138, "d", " log pref works again" );
//
//        defaultVersion = "0.8.178";
//        add( 128, "F", " configurable handling per file type" );
//        add( 129, "d", "configurable diff tool" ).from( "Werner" ).ref(128);
//
//        defaultVersion = "0.8.177";
//        add( 136, "d", "filter removes nodes from skyview immediate " ).ref(177);
//        add( 137, "d", "filter removes nodes from tasks immediate " ).ref(177);
//        add( 117, "F", "Filter should filter the results on the fly" ).from( "Werner" );
//
//        defaultVersion = "0.7.176";
//        add( 135, "d", "filter removes nodes from tree immediate " ).ref(177);
//
//        defaultVersion = "0.7.175";
//        add( 127, "d", "win: use java dialog to get directories (explorer can't)" ).ref(124);
//        add( 130, "d", "configurable similarity algoritm" ).ref(128);
//        add( 131, "d", "configurable open setting" ).ref(128);
//        add( 132, "d", "open for OSX" ).ref(128);
//        add( 133, "d", "open for win32 (thanks Mike)" ).ref(128);
//        add( 121, "r", "prefs to prefs in openCage.utils, actually preferences)" );
//        add( 119, "d", "default preset for filters not working (again?)" ).from( "Werner" ).ref(96).ref(121);
//
//        defaultVersion = "0.7.174";
//        add( 116, "F", "Synchronized scrolling" ).from( "Werner" );
//        add( 47, "d", " show selection in window midth" ).ref(116).tag("ui").comment(
//                "click in skyview moves selection into the middle of the window"
//        );
//
//        defaultVersion = "0.6.173";
//        add( 126, "d", " synchronized selection" ).ref(116);
//
//        defaultVersion = "0.6.172";
//        add(  8, "d", " adapt to win (started)" ).ref(124);
//        add( 123, "d", " skyview adopted to windows" );
//        add( 125, "d", " filepath parsing adopted to windows" );
//
//        defaultVersion = "0.6.171";
//        add( 122, "d", " prefs to prefs in openCage.utils" ).ref(121);
//
//        defaultVersion = "0.6.170";
//        add( 118, "f", ".get_date.dat to standard filter" ).from( "Werner" );
//
//        defaultVersion = "0.6.169";
//        add( 111, "f", " more icon work" ).ref(113);
//        add( 110, "d", " summary of 3" );
//        add( 108, "f", " view concept started" ).ref(114);
//
//        defaultVersion = "0.6.168";
//        add( 19, "f", "improve C++ match" ).tag( "algo" );
//        add( 21, "f", "improve xml match" );
//        add( 41, "f", "better icon, e.g. transparent background" ).ref(111).
//                comment( "used  Cocoflop");
//        add( 80, "f", "robust: unreadable files dirs" ).ref(115);
//
//        defaultVersion = "0.6.167";
//        add( 98, "f", " set icon for all windows (worked already when deployed)" );
//        add( 103, "f", " added more known extensions" );
//        add( 105, "d", " improved summary" ).ref(106);
//
//        defaultVersion = "0.6.166";
//        add( 96, "d", "ignore preset not active" ).ref(94);
//        add( 78, "f", "improve log window" ).ref(97);
//        add( 99, "d", "menu in main window too" );
//        add( 42, "f", "break match/ do match methods started" ).ref(101);
//        add(  2, "f", "merge, only button" ).ref(102);
//
//        defaultVersion = "0.6.165";
//        add( 91, "d", "dir dialog: go button not enables when values are preset" );
//        add( 92, "d", "ignore cannot deal with unparsable patterns" );
//        add( 95, "d", "log not set to start preset" );
//
//        defaultVersion = "0.6.164";
//        add( 89, "d", "upgrade to mydoggy 1.3.1 (no issue)" );
//        add( 65, "d", "menues work only for main frame" ).ref(90);
//        add( 79, "f", "finish prefs (bugs left)" );
//
//        defaultVersion = "0.5.163";
//        add( 84, "f", "presetpane buttons to open windows (used)" );
//        add( 87, "f", "shutdownhook to save prefs" );
//        add( 82, "f", "test area in filter pane" );
//        add( 88, "d", "log view shows not last" );
//        add( 100, "d", "handle unknown extensions: binary + warning" );
//
//
//        defaultVersion = "0.5.162";
//        add( 31, "f", "prefs: prefs started" ).ref(79);
//        add(  3, "f", "filter by path regex started" ).ref(85);
//        add( 83, "f", "preset = prefs (OSX)" );
//
//        defaultVersion = "0.5.161";
//        add( 74, "f", "better progress detail for longrunnig diffs" ).from( "Werner" ).ref(76).tag("large");
//        add( 77, "f", "start general log window" ).ref(78).tag("feedback");
//        add( 50, "f", "duplicate (77)" ).ref(77);
//
//        defaultVersion = "0.5.160";
//        add( 73, "f", "bugreport shows progname and version" );
//        add( 75, "f", "send feature request" );
//        add( 72, "f", " mailto string works with subject and body" );
//        defaultVersion = "0.5.159";
//        add( 68, "d", "fix mydoggy popup layout" ).comment( "thanxs Jason for designgrid 0.2)" ).
//                tag("ui");
//        add( 69, "d", "redesign jar dir layout and ant" ).tag("build");
//        add( 59, "d", "fix mydoggy popup layout (duplicate)" ).ref(68);
//        defaultVersion = "0.5.158";
//        defaultVersion = "0.5.157";
//        add( 66, "d", "refactor colors" ).tag("visualization");
//        defaultVersion = "0.5.156";
//        add( 64, "d", "too much weight on content see FileDistance.java in 04" );
//        add( 63, "d", "oops parent never mattered" ).tag("algo");
//// refactor
//        defaultVersion = "0.5.155";
//// refactor matching graph -> task (to many graphs )
//        add( 61, "d", "hierarchical dir match: do match" );
//        add(  5, "d", "match renamed java dirs" );
//        defaultVersion = "0.5.154";
//        add( 55, "d", "2 way screen" ).from( "Werner" );
//        add( 54, "d", "content fuzzy equal == 1 != identical" ).tag("algo");
//        defaultVersion = "0.5.152";
//        add( 49, "f", "spawn external diff/merge tool plus button" );
//        defaultVersion = "0.5.151";
//// refactor ui again
//        add(  1, "f", "show selection diff in extra panel" ).tag("ui");
//        add( 52, "f", "use docking ui" ).tag("ui");
//        defaultVersion = "0.4.143";
//        add( 48, "d", "clear the selection when showing match of only" ).tag("ui");
//        defaultVersion = "0.4.142";
//        add( 43, "f", "make show help view readonly" ).tag("feedback");
//        defaultVersion = "0.4.141";
//        add( 22, "f", "split using refrences into extra panel" );
//        add( 32, "f", "help screen" ).tag("feedback");
//        defaultVersion = "0.4.140";
//        add( 13, "f", "changelog in gui" ).tag("feedback");
//        defaultVersion = "0.4.138";
//        add(  4, "f", "build osx app (ant)" ).tag( "build" ).tag("OSX").comment( "jarbuilder" );
//        add( 10, "f", "contact address in gui" ).tag("feedback");
//        add( 11, "f", "copyright in gui" ).tag("feedback");
//        add( 35, "f", "track versions" );
//        add( 36, "f", "scrollpanes middled" ).tag("ui");
//        add( 37, "f", "left scrollbar on left" ).tag("ui");
//        add( 40, "f", "osx icon" ).tag("OSX");
//        defaultVersion = "0.3.110";
//        add(  9, "f", "3 way diff" ).tag("algo");
//        add( 27, "f", "skyviewbar" ).tag( "ui" ).tag( "visualization" ).
//                comment( "a bar of colored strips to show the changes of the whole document at once." );
//        add( 29, "f", "match selection to parent of only file" ).tag( "algo" );
//        add( 45, "f", "use color as info for changes" ).tag("visualization");
//        defaultVersion = "0.2.100";
//        add( 12, "f", "gui" ).comment( "First simple gui. 2 trees.");
//        add( 28, "f", "match selection" );
//        add( 38, "f", "designgridlayout" ).comment( "A nice layout manager.");
//        defaultVersion = "0.1.070";
//        add( 14, "f", "cli" );
//        add( 15, "f", "deploy through ant" ).comment( "Deploy into one external dir." );
//        add( 16, "f", "guice" ).comment( "IoC without XML, nice " );
//        add( 23, "f", "match duplicates" ).
//                comment( "I turns out relying on checksums produces strange results for duplicates,\n" +
//                        "i.e. sometimes they are matched correctly but sometimes they are switched.\n" +
//                        "The idea to ignore the path when the content is identical is seductive but later \n" +
//                        "dropped.");
//        add( 24, "f", "match unchanged dirs" ).comment("SimpleDirMatch");
//        add( 25, "f", "match changed dirs" ).comment( "Hierarchical Matching?");
//        add( 26, "f", "improve match for small files" ).comment(
//                "This is supposed to give better similarity values for the case that you start with small standard file and change it\n" +
//                        "heavily. The whole idea might be wrong see later bugs (TODO). Maybe we should just look at the context and\n" +
//                        "assume same name and path we'll do the rest for this specific case.");
//        add( 30, "f", "ant" ).comment( "Build with ant. First step towards a full build environment.");
//        add( 34, "f", "match unchanged files with md5 (fast md5)" );
//        add( 67, "f", "ignore white noise in java lines" ).comment( "The implementation is pretty radical and ignores all spaces even if they separate. But we are talking heuristic\n" +
//                "anyway. So the easier implementation and speed make well up for the unlikely case that this will produce \n" +
//                "many identical checksums for unequal lines. ");
//    }
//}
