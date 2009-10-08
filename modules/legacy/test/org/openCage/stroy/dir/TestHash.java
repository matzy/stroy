package org.openCage.stroy.dir;

import org.openCage.util.io.FileUtils;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

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


@SuppressWarnings( {"HardCodedStringLiteral"} )
public class TestHash {

    static private Map<Integer, List<String>> hashes = new HashMap<Integer, List<String>>();
    static private int dup = 0;

    public static void main(String[] args) {

        fill( new File( "/Users/spfab/Documents/aaf-game/spfab_Cortex_A_intg"));



    }

    private static void fill(File file) {

        if ( file.getName().equals( ".svn" ) ||
                file.getName().equals( ".class" ) ||
                file.getName().equals( ".DS_Store" ) ||
                file.getName().equals( ".copyarea.db" ) ||
                file.getName().equals( ".copyarea.dat" )) {
            return;
        }
        

        if ( file.isDirectory() ) {
            for ( File child : file.listFiles() ) {
                fill( child );
            }
        } else {

            if ( ! FileUtils.getExtension( file ).equals( "java" )) {
                return;
            }

            throw new Error( "not impl" );

//            FileUtils.withIterator( file, new V1<Iterable<String>>() {
//                public void call( Iterable<String> iterable ) {
//                    for ( String line : iterable ) {
//                        if ( !hashes.containsKey( line.hashCode())) {
//                            hashes.put( line.hashCode(), new ArrayList<String>());
//                        }
//
//                        List<String> before  = hashes.get( line.hashCode() );
//
//                        boolean found = false;
//                        for ( String ll : before ) {
//                            if ( ll.equals(line )) {
//                                found = true;
//                                break;
//                            }
//                        }
//
//                        if ( !found ) {
//                            before.add( line );
//
//                            if ( before.size() > 1 ) {
//                                dup++;
//                                System.out.println("-- " + dup + " of " + hashes.size() );
//                                System.out.println("   " + line.hashCode());
//                                for ( String dd : before ) {
//                                    System.out.println("   " + dd );
//                                }
//                            }
//                        }
//
//                    }
//                }
//            } );

        }
    }
}
