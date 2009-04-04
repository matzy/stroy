package org.openCage.wikidot.diffs;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    private List<DiffProg> diffs = new ArrayList<DiffProg>();

    public static void main( String[] args ) {
        new Main().go();
    }

    private void go() {
        add();
        summery();
        
    }

    private void summery() {


        Collections.sort( diffs, DiffProg.alphaComparator() );

        for ( DiffProg diff : diffs ) {
            diff.printSummery();
        }
    }

    private void add() {

        add( "Accurev" ).licence( "$$" ).has().windows().osx().linux().url("http://www.accurev.com" ).textMerge().gui();

        diffs.add( new DiffProg( "Alienbrain" ).
                url( "http://www.alienbrain.com" ).osx().windows().linux().vista().licence( "$$" ).
                comment( "Alienbrain is an Asset Management tools for artists. A scm for pictures, movies ... Some versions come with Arexis Merge." ).
                gui().
                has().
                textMerge().
                gui()
                );

        add( "Arxis Merge" ).url( "http://www.araxis.com/merge-overview.html" ).licence( "$$").osx().windows().textMerge().picDiff().gui();

        add( "BBEdit" ).url( "http://www.barebones.com/products/bbedit" ).osx().worksas().licence( "$$" ).
                comment( "BBedit is a nice texteditor for OSX. It can be used as text merge tool." ).gui();

        add( "BeeDiff" ).licence( "GPL2" ).textMerge().linux();

        add( "Beyond Compare" ).licence( "free").textMerge().linux().windows();

        add( "bindiff" ).licence( "free" ).windows().os9();

        add( "Changes" ).licence( "$").textMerge().osx();

        add( "CodeCompare" ).licence( "free" ).textMerge().windows();

        add( "Compare It!" ).licence( "$" ).textMerge().windows();

        add( "CompareFolders").licence( "$" ).folderDiff().osx();

        add( "CompareFolders3" ).licence( "$").textMerge().osx().linux().windows();

        add( "Cornerstone" ).licence( "$" ).has().osx();

        add( "cs-exceldiff" ).licence( "$$" ).windows().textMerge();

        add( "cs-htmldiff" ).licence( "$$" ).windows().textMerge();

        add( "csdiff" ).licence( "free" ).windows().textMerge();

        diffs.add( new DiffProg( "diff" ).
                url( "http://en.wikipedia.org/wiki/Diff" ).
                licence("free").
                comment("The classical diff developed for Unix and the granddaddy of all the programs. It was/is a command line tool and\n" +
                "is used to diff text files.").
                osx().
                windows().
                linux().
                textDiff().
                clt()
                );

        add("diffdoc").licence( "$$" ).textMerge().windows();

        add("diffdog").licence( "$$" ).textMerge().windows();

        add( "diffj" ).licence( "LGPL" ).osx().windows().linux().javapf().comment( "Diff for Java Files" );

        add( "Diffly" ).licence( "free" ).osx();

        add( "DiffMerge" ).licence( "free*" ).osx().linux().windows();

        add( "Diffuse" ).licence( "GPL" ).linux().windows();

        add( "DirDiff" ).licence( "free" ).windows();

        add( "DirSync Pro" ).licence( "GPL3" ).windows();


        diffs.add( new DiffProg( "EasyDiff").
                url( "http://wiki.gnustep.org/index.php/EasyDiff.app" ).
                version( "0.4.0" ).
                modified( "2009.3.24" ).licence( "GPL2" ).
                openstep().gui());

        diffs.add( new DiffProg( "guiffy" ).
            url( "http://www.guiffy.com" ).osx().windows().linux().licence( "$" ).
            textMerge().gui());

        diffs.add( new DiffProg( "SwiftCompare" ).
            url( "http://www.oorjasoftware.com" ).modified( "2009.3.23" )
            .licence( "$" ).windows().textMerge().gui());

    }

    private DiffProg add( String name ) {
        DiffProg ret = new DiffProg( name );
        diffs.add( ret );

        return ret;
    }

}
