package org.openCage.webgen.clazz;

import org.openCage.webgen.protocol.WebGen;
import org.openCage.webgen.impl.WikidotGen;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class GenWikiRefs {

    private final List<Ref> refs;
    private final WebGen gen = new WikidotGen(); 

    public GenWikiRefs( List<Ref> refs ) {
        this.refs = refs;
    }

    public void gen() {
        printTOC();
        printDeps();
        printRuntime();
        printOther();
//        printTest();
    }

    private void printTOC() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("I use a collection of great libraries and tools. " +
                "Have a look maybe they solve a problem of yours.");

        System.out.println( gen.link( "Libraries", "Libraries" ) );
        System.out.println( gen.link( "Knowhow", "Knowhow" ) );
//        System.out.println( gen.link( "Test", "Test" ) );

    }

    private void printDeps() {
        System.out.println( gen.ancor( "Project Dependencies" ));
        System.out.println( gen.title( 1, "Project Dependencies" ));

        printProjectDepends( "fausterize", "depend.fausterize" );
        System.out.println("");

        printProjectDepends( "March of the Pink Elephants", "March of the Pink Elephants" );

    }

    private void printProjectDepends( String name, String real ) {
        System.out.println( name + " depends on");
        Collection<Ref> faustDeps = new UsingUsed().transitiveClosure( refs, real);
        List<Ref> ordered = new ArrayList<Ref>( faustDeps );

        Collections.sort( ordered, new Comparator<Ref>() {
            @Override
            public int compare(Ref a, Ref b) {
                return a.getName().compareTo( b.getName());
            }
        });

        System.out.println("");
        for ( Ref ref: ordered ) {
            if ( !(ref instanceof Module )) {
                System.out.print( gen.link( ref.getName(), ref.getName()) + " " );                
            }
        }

        System.out.println("");
    }

    private void printRuntime() {
        System.out.println( gen.ancor( "Libraries" ));
        System.out.println( gen.title( 1, "Libraries" ));
        System.out.println("These libraries are used by the application and are deployed with it (if the licence allows it).");
        for ( Ref ref : refs ) {
            if ( ref instanceof  Lib ) {
                ref.printWikiFull();
            }
        }
    }

    private void printOther() {
        System.out.println( gen.ancor( "Knowhow" ));
        System.out.println( gen.title( 1, "Knowhow" ));
        System.out.println("Resources in other form, e.g. apps, articles, code snippets");
//        System.out.println( "DE: Mit diesen libs wird das Programm gebaut. Sie sind im Quellcodepacket" );
//        System.out.println( "ES: Estas bibliotecas se utilizan para construir el uso y se despliegan con el c�digo de fuente." );
        for ( Ref ref : refs ) {
            if ( ref instanceof  Other ) {
                ref.printWikiFull();
            }
        }
    }
//
//    private void printTest() {
//        System.out.println( gen.ancor( "Test" ));
//        System.out.println( "+ Test" );
//        System.out.println("These libraries are used to test the application and are deployed with the source code.");
//        for ( Reference ref : refs ) {
//            if ( ref.isTest() ) {
//                ref.printWikiFull();
//            }
//        }
//    }
}
