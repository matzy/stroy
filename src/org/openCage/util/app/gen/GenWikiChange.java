package org.openCage.util.app.gen;

import org.openCage.util.www.wikidot.WikiDotGen;
import org.openCage.util.app.Change;
import org.openCage.util.app.VersionImpl;
import org.openCage.util.app.AppInfo;

import java.util.*;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
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
                return VersionImpl.parseVersion( o2.version ).compareTo( VersionImpl.parseVersion( o1.version ));
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
