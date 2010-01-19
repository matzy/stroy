//package org.openCage.wikidot.diffs;
//
//import org.openCage.wikidot.WikidotGen;
//
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//public class Main {
//
//    private List<DiffProg> diffs = new ArrayList<DiffProg>();
//
//    public static void main( String[] args ) {
//        new Main().go();
//    }
//
//    private void go() {
//        add();
//        summery();
//
//        legend();
//
//    }
//
//    private void legend() {
//        System.out.println( "++ Legend" );
//        System.out.println( WikidotGen.row( WikidotGen.bold( "Term" ) , WikidotGen.bold( "Explanation" ) ));
//        System.out.println( WikidotGen.row( "$" , "under 100$" ));
//        System.out.println( WikidotGen.row( "$$" , "more than 100$ upto 1000$" ));
//        System.out.println( WikidotGen.row( "Diff" , "Program shows differences between (text) files" ));
//        System.out.println( WikidotGen.row( "FolderDiff" , "Program shows differences between folders" ));
//        System.out.println( WikidotGen.row( "Merge" , "Program can merge (text) files" ));
//        System.out.println( WikidotGen.row( "Sync" , "Program can merge (text) files and merge folders" ));
//        System.out.println( WikidotGen.row( "MergeSync" , "Program can merge folders" ));
//        System.out.println( WikidotGen.row( "Texteditor" , "A Texteditor with the ability to merge text files" ));
//        System.out.println( WikidotGen.row( "SCM and X" , "An SCM tool with some diff program available independently of the SCM" ));
//        System.out.println( WikidotGen.row( "SCM has X" , "An SCM tool with some diff program included in the SCM" ));
//        System.out.println( WikidotGen.row( "free" , "free to use but with unclear licence" ));
//
//
//    }
//
//    private void summery() {
//
//
//        Collections.sort( diffs, DiffProg.alphaComparator() );
//
//        for ( DiffProg diff : diffs ) {
//            diff.printSummery();
//        }
//    }
//
//    private void add() {
//
//        add( "Accurev" ).licence( "$$" ).has().windows().osx().linux().url("http://www.accurev.com" )
//                .scmHasMerge();
//
//        diffs.add( new DiffProg( "Alienbrain" ).
//                url( "http://www.alienbrain.com" ).osx().windows().linux().vista().licence( "$$" ).
//                comment( "Alienbrain is an Asset Management tools for artists. A scm for pictures, movies ... Some versions come with Arexis Merge." ).
//                gui().
//                gui().scmAndMerge()
//                );
//
//        add( "Arxis Merge" ).url( "http://www.araxis.com/merge-overview.html" ).licence( "$$").osx().windows().picDiff().gui().mergeSync();
//
//        add( "BBEdit" ).url( "http://www.barebones.com/products/bbedit" ).osx().worksas().licence( "$$" ).
//                comment( "BBedit is a nice texteditor for OSX. It can be used as text merge tool." ).gui().textedit();
//
//        add( "BeeDiff" ).licence( "GPL2" ).merge().linux().modified("2009.3.5").url("http://freshmeat.net/projects/beediff");
//
//        add( "Beyond Compare" ).licence( "free").merge().linux().windows().url("http://www.scootersoftware.com/");
//
//        add( "bindiff" ).licence( "free" ).windows().os9().url("http://www.rorohiko.com/wordpress/downloads/bindiff").modified("2009.3.6");
//
//        add( "Changes" ).licence( "$").merge().osx().url("http://changesapp.com");
//
//        add( "CodeCompare" ).licence( "free" ).merge().windows().url("http://www.adamberent.com/CodeCompare.html");
//
//        add( "Compare It!" ).licence( "$" ).merge().windows().url("ttp://www.grigsoft.com/wincmp3.htm").version("4.1").modified("2009.3.6");
//
//        add( "CompareFolders").licence( "$" ).folderDiff().osx().url("http://www.infinitenexus.com/cf/index.html");
//
//        add( "CompareFolders3" ).licence( "$").merge().osx().linux().windows().url("http://www.psc-consulting.ca/fenske/");
//
//        add( "Cornerstone" ).licence( "$" ).osx().modified( "2009.3.12" ).version("1.1.7").url("http://www.zennaware.com/cornerstone");
//
//        add( "cs-exceldiff" ).licence( "$$" ).windows().merge().url("http://www.componentsoftware.com/Products/ExcelDiff/index.htm").modified("2009.3.5");
//
//        add( "cs-htmldiff" ).licence( "$$" ).windows().merge().modified("2009.3.5").url("http://www.componentsoftware.com/products/HTMLDiff/index.htm");
//
//        add( "CSDiff" ).licence( "free" ).windows().merge().modified("2009.3.5").url("http://www.componentsoftware.com/products/CSDiff/index.htm");
//
//        add( "diff" ).
//                url( "http://en.wikipedia.org/wiki/Diff" ).
//                licence("free").
//                comment("The classical diff developed for Unix and the granddaddy of all the programs. It was/is a command line tool and\n" +
//                "is used to diff text files.").
//                osx().
//                windows().
//                linux().
//                diff().
//                clt();
//
//        add("diffdoc").licence( "$$" ).merge().windows().url("http://www.softinterface.com/Compare-File-Programs/Compare-File-Programs.HTM");
//
//        add("diffdog").licence( "$$" ).merge().windows().url("http://www.altova.com/products/diffdog/diff_merge_tool.html");
//
//        add( "diffj" ).licence( "LGPL" ).
//                osx().windows().linux().javapf().
//                comment( "diffj is an application that compares Java files and projects. It operates similarly to diff(1), but compares only Java tokens and types, and skips whitespace and comments." ).
//                url("http://www.incava.org/projects/java/diffj").diff().special( "diff for java files" );
//
//        add( "Diffly" ).licence( "free" ).osx().url("http://lucidmac.com/products/diffly");
//
//        add( "DiffMerge" ).licence( "free*" ).osx().linux().windows().url("http://www.sourcegear.com/diffmerge");
//
//        add( "Diffuse" ).licence( "GPL" ).linux().windows().url("http://diffuse.sourceforge.net");
//
//        add( "DirDiff" ).licence( "free" ).windows().url("http://www.softpedia.com/get/System/File-Management/DirDiff.shtml");
//
//        add( "DirSync Pro" ).licence( "GPL3" ).windows().url("http://directorysync.sourceforge.net/index.html");
//
//
//        add( "EasyDiff").
//                url( "http://wiki.gnustep.org/index.php/EasyDiff.app" ).
//                version( "0.4.0" ).
//                modified( "2009.3.24" ).licence( "GPL2" ).
//                openstep().gui();
//
//        add( "ExamDiff" ).licence( "free" ).windows().textDiff().url( "http://www.prestosoft.com/ps.asp?page=edp_examdiff" ).modified( "2009.3.5" );
//
//        add( "ExamDiff Pro" ).licence( "$" ).windows().textDiff().url( "http://www.prestosoft.com/ps.asp?page=edp_examdiff" ).modified( "2009.3.5" );
//
//        add( "FileMerge" ).osx().may().gui().clt().licence( "free" ).url("http://developer.apple.com/documentation/Darwin/Reference/ManPages/man1/opendiff.1.html").
//                comment("on OSX if you install xcode");
//
//        add("fc").licence("with win").windows().textDiff().url("http://www.ss64.com/nt/fc.html").modified("2009.3.13");
//
//        add( "guiffy" ).
//            url( "http://www.guiffy.com" ).osx().windows().linux().licence( "$" ).
//            merge().gui();
//
//        add( "JFileSync" ).gpl().windows() // sync
//        .url("http://jfilesync.sourceforge.net").modified("2009.3.5");
//
//        add( "jMeld" ).lgpl().windows().osx().linux().javapf().url("http://www.xs4all.nl/~keeskuip/jmeld/");
//
//        add( "kdiff3" ).gpl().linux().osx().windows().mergeSync().url("ttp://kdiff3.sourceforge.net/");
//
//        add("meld").gpl().merge().osx().linux().url("[http://meld.sourceforge.net/");
//
//        add("p4merge").free().merge().osx().linux().windows().url("http://www.perforce.com/perforce/products/merge.html");
//
//        add("SmartSynchronize").licence("$").merge().osx().linux().windows().url("http://www.syntevo.com/smartsynchronize");
//
//        add("stroy").mpl().folderDiff().linux().windows().osx().special( "Finds moved/reanmed/changed files" ).gui().version("1.0.2").
//                url("http://stroy.wikidot.com");
//
//        add( "SwiftCompare" ).
//            url( "http://www.oorjasoftware.com" ).modified( "2009.3.23" )
//            .licence( "$" ).windows().merge().gui();
//
//        add( "TextMate" ).textedit().osx().licence("$").url("http://macromates.com");
//
//        add( "TextWrangler" ).free().osx().licence("$").url("http://www.barebones.com/products/TextWrangler/");
//
//        add( "TkDiff" ).gpl().osx().merge().osx().windows().linux().url("http://sourceforge.net/projects/tkdiff");
//
//        add( "TortoiseIDiff" ).gpl().diff().windows().url( "http://tortoisesvn.tigris.org/TortoiseIDiff.html" );
//
//        add( "TortoiseMerge" ).gpl().merge().windows().url("http://tortoisesvn.tigris.org/TortoiseMerge.html");
//
//        add( "Ultra Compare" ).licence("$").merge().windows().url("http://www.ultraedit.com/products/ultracompare.html");
//
//        add( "Unison" ).gpl().sync().windows().osx().linux().url("http://www.cis.upenn.edu/~bcpierce/unison");
//
//        add( "WinDiff" ).licence("free").merge().windows().comment( "Comes with Windows").url("http://en.wikipedia.org/wiki/WinDiff");
//
//        add( "WinMerge" ).gpl().merge().windows().url("http://winmerge.org");
//
//        add( "ws-it DirDiff" ).licence("$").windows().sync().modified("2009.3.5").url("http://www.ws-it.de/product/ddiff/index.html");
//
//        add( "XWinDiff" ).gpl().merge().linux().url( "http://www.lurklurk.org/xwindiff.html" ).modified( "2009.3.6" ).version( "1.1");
//
//        add( "xxdiff" ).gpl().merge().linux().url( "http://furius.ca/xxdiff" ).modified("2009.3.6").version("3.2");
//
//
//
//    }
//
//    private DiffProg add( String name ) {
//        DiffProg ret = new DiffProg( name );
//        diffs.add( ret );
//
//        return ret;
//    }
//
//}
