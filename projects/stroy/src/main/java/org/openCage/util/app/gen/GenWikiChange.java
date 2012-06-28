package org.openCage.util.app.gen;

import org.openCage.util.www.wikidot.WikiDotGen;
import org.openCage.util.app.Change;
import org.openCage.util.app.Version2;
import org.openCage.util.app.AppInfo;

import java.util.*;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class GenWikiChange {


    private final AppInfo appInfo;
    private Set<String> tags = new HashSet<String>( );
    private List<String> users = new ArrayList<String>();


    public GenWikiChange( AppInfo appInfo ) {
        this.appInfo = appInfo;
    }

    public void gen() {
        computeUsers();
        computeTags();

        printTOC();

        printByImpl();
        printByNumber();
        printByPerson();
        printByTag();

    }


    private void printTOC() {

        System.out.println( WikiDotGen.link( "ChangeLog", "ChangeLog" ) );
        System.out.println( WikiDotGen.link( "OpenChanges", "Open Changes" ) );
        System.out.println( WikiDotGen.link( "ChangeDetails", "Change Details" ) );
        System.out.println( WikiDotGen.link( "ChangeByUser", "Change by User" ) );
        System.out.print( "> " );
        for ( String user : users) {
            System.out.print( " " + WikiDotGen.link( user, user)    );
        }
        System.out.println( "" );

        System.out.println( " " + WikiDotGen.link( "ChangeByTag", "Change by Tag" ) );
        System.out.print( "> " );
        for ( String tag : tags ) {
            System.out.print( " " + WikiDotGen.link( tag, tag)    );
        }

        System.out.println( "" );
        System.out.println( "" );

    }


    static class NumberComparator implements Comparator<Change> {

        public int compare( Change o1, Change o2 ) {
            return o2.num - o1.num;
        }
    }

    private void printByNumber() {
        System.out.println();
        System.out.println( WikiDotGen.ancor( "ChangeDetails" ) );
        System.out.println( "+ Change Details" );

        Collections.sort( appInfo.getChanges(), new NumberComparator() );

        for ( Change change : appInfo.getChanges() ) {
            change.printWikiAncor();
            System.out.println("");
        }
    }


    private void printByPerson() {
        System.out.println();
        System.out.println( WikiDotGen.ancor( "ChangeByUser" ) );
        System.out.println( "+ Change by User" );

        Collections.sort( appInfo.getChanges(), new VersionComparator() );

        for ( String from : users) {
            System.out.println( WikiDotGen.ancor( from ));
            System.out.println("+++ " + from );
            int state = 0;

            for ( Change change : appInfo.getChanges() ) {
                if ( change.isFrom(from)) {
                    if ( (state == 0) && change.version == null ) {
                        System.out.println("++++ Open");
                        state = 1;
                    } else if ( (state == 1) && change.version != null ) {
                        System.out.println("++++ Done");
                        state = 2;
                    }
                    change.printWikiLink();
                }
            }

            for ( Change change : appInfo.getChanges() ) {
                if ( change.isHelped(from)) {
                    if ( state < 3 ) {
                        System.out.println("++++ Helped");
                        state = 3;                        
                    }
                    change.printWikiLink();
                }
            }
        }

    }

    private void printByTag() {
        System.out.println();
        System.out.println( WikiDotGen.ancor( "ChangeByTag" ) );
        System.out.println( "+ Change by Tag" );

        Collections.sort( appInfo.getChanges(), new VersionComparator() );


        for ( String tag : tags ) {
            System.out.println( WikiDotGen.ancor( tag ));
            System.out.println("+++ " + tag );
            System.out.println("++++ Open");
            boolean impl = false;

            for ( Change change : appInfo.getChanges() ) {
                if ( change.hasTag(tag)) {
                    if ( !impl && change.version != null ) {
                        System.out.println("++++ Done");
                        impl = true;
                    }
                    change.printWikiLink();
                }
            }
        }

    }

    private void computeUsers() {
        Set<String> uu = new HashSet<String>();
        for ( Change change : appInfo.getChanges() ) {
            uu.addAll( change.getFroms() );
        }
        for ( Change change : appInfo.getChanges() ) {
            uu.addAll( change.getHelpers() );
        }

        users.addAll(uu);

        Collections.sort( users, new Comparator<String>() {
            public int compare(String s, String s1) {
                return s.compareTo( s1 );
            }
        });
    }

    private void computeTags() {
        for ( Change change : appInfo.getChanges() ) {
            tags.addAll( change.tags );
        }
    }

    static class VersionComparator implements Comparator<Change> {

        public int compare( Change o1, Change o2 ) {
            if ( o1.version == o2.version ) {
                return o2.num - o1.num;
            } else if ( o2.version == null ) {
                return 1;
            } else if ( o1.version == null ) {
                return -1;
            } else {
                return Version2.parseVersion( o2.version ).compareTo( Version2.parseVersion( o1.version ));
            }
        }
    }

    private void printByImpl() {

        System.out.println();
        System.out.println( WikiDotGen.ancor( "ChangeLog" ) );
        System.out.println( "+ ChangeLog" );

        Collections.sort( appInfo.getChanges(), new VersionComparator() );

        String currentVersion = null;

        for ( Change change : appInfo.getChanges() ) {

            if ( change.version != currentVersion ) {
                currentVersion = change.version;
                System.out.println("");
                change.printWikiVersionAncor();
            }

            if ( currentVersion != null ) {
                change.printWikiLink();
            }
        }

        System.out.println( "" );
        System.out.println( WikiDotGen.ancor( "OpenChanges" ) );
        System.out.println( "+ Open Changes" );


        for ( Change change : appInfo.getChanges() ) {

            if ( change.version == null ) {
                change.printWikiLink();
            }
        }
    }

    

}
