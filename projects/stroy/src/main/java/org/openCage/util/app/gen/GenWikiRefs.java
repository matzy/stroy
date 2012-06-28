package org.openCage.util.app.gen;

import org.openCage.util.app.AppInfo;
import org.openCage.util.app.Reference;
import org.openCage.util.www.wikidot.WikiDotGen;

import java.util.Set;
import java.util.HashSet;

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

public class GenWikiRefs {

    private final AppInfo appInfo;

    public GenWikiRefs( AppInfo appInfo ) {
        this.appInfo = appInfo;
    }

    public void gen() {
        printTOC();
        printRuntime();
        printBuild();
        printTest();
    }

    private void printTOC() {
        System.out.println("Stroy uses a collection of great libraries and tools." +
                "Have a look maybe they solve a problem of yours independent of stroy.");        

        System.out.println( WikiDotGen.link( "Runtime", "Runtime" ) );
        System.out.println( WikiDotGen.link( "Build", "Build" ) );
        System.out.println( WikiDotGen.link( "Test", "Test" ) );

    }

    private void printRuntime() {
        System.out.println( WikiDotGen.ancor( "Runtime" ));
        System.out.println( "+ Runtime" );
        System.out.println("These libraries are used by the application and are deployed with it.");
        for ( Reference ref : appInfo.getReferences() ) {
            if ( ref.isRuntime() ) {
                ref.printWikiFull();
            }
        }
    }

    private void printBuild() {
        System.out.println( WikiDotGen.ancor( "Build" ));
        System.out.println( "+ Build" );
        System.out.println("These libraries are used to build the application and are deployed with the source code if their licence allows this.");
        System.out.println( "DE: Mit diesen libs wird das Programm gebaut. Sie sind im Quellcodepacket" );
//        System.out.println( "ES: Estas bibliotecas se utilizan para construir el uso y se despliegan con el c�digo de fuente." );
        // TODO
        System.out.println( "ES: Estas bibliotecas se utilizan para construir el uso y se despliegan con el cédigo de fuente." );
        for ( Reference ref : appInfo.getReferences() ) {
            if ( ref.isBuild() ) {
                ref.printWikiFull();
            }
        }
    }

    private void printTest() {
        System.out.println( WikiDotGen.ancor( "Test" ));
        System.out.println( "+ Test" );
        System.out.println("These libraries are used to test the application and are deployed with the source code.");
        for ( Reference ref : appInfo.getReferences() ) {
            if ( ref.isTest() ) {
                ref.printWikiFull();
            }
        }
    }
}
