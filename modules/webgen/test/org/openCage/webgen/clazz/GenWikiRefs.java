package org.openCage.webgen.clazz;

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

public class GenWikiRefs {

    private final List<Ref> refs;

    public GenWikiRefs( List<Ref> refs ) {
        this.refs = refs;
    }

    public void gen() {
        printTOC();
        printRuntime();
        printOther();
//        printTest();
    }

    private void printTOC() {
        System.out.println("I use a collection of great libraries and tools. " +
                "Have a look maybe they solve a problem of yours.");

        System.out.println( WikiDotGen.link( "Runtime", "Runtime" ) );
        System.out.println( WikiDotGen.link( "Build", "Build" ) );
        System.out.println( WikiDotGen.link( "Test", "Test" ) );

    }

    private void printRuntime() {
        System.out.println( WikiDotGen.ancor( "Runtime" ));
        System.out.println( "+ Runtime" );
        System.out.println("These libraries are used by the application and are deployed with it.");
        for ( Ref ref : refs ) {
            if ( ref instanceof  Lib ) {
                ref.printWikiFull();
            }
        }
    }

    private void printOther() {
        System.out.println( WikiDotGen.ancor( "Build" ));
        System.out.println( "+ Build" );
        System.out.println("These libraries are used to build the application and are deployed with the source code if their licence allows this.");
        System.out.println( "DE: Mit diesen libs wird das Programm gebaut. Sie sind im Quellcodepacket" );
        System.out.println( "ES: Estas bibliotecas se utilizan para construir el uso y se despliegan con el c�digo de fuente." );
        for ( Ref ref : refs ) {
            if ( ref instanceof  Other ) {
                ref.printWikiFull();
            }
        }
    }
//
//    private void printTest() {
//        System.out.println( WikiDotGen.ancor( "Test" ));
//        System.out.println( "+ Test" );
//        System.out.println("These libraries are used to test the application and are deployed with the source code.");
//        for ( Reference ref : refs ) {
//            if ( ref.isTest() ) {
//                ref.printWikiFull();
//            }
//        }
//    }
}
