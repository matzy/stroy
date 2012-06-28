package org.openCage.stjx;

import org.openCage.io.fspath.FSPathBuilder;

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

public class Licenses {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "Licenses" );

        stjx.struct( "Licenses" ).zeroOrMore().complex( "License");

        stjx.struct( "License" ).
                string( "name" ).
//                string( "version" ).
                zeroOrMore("aliases").string("alias").
                string( "uri" ).
                zeroOrMore( "positives" ).string( "license" ).
                zeroOrMore( "negatives" ).string( "license" );


//        stjx.struct( "Licence" ).
//                string( "name").
//                string( "uri" ).
//                enm("type").
//                string( "timestamp" ).
//                string("seqno").
//                optional().string("to").
//                zeroOrMore().complex( "DependsOn" ).
//                zeroOrMore( "DatabaseFiles").string( "File" );
//
//        stjx.enm( "type", "full", "increment", "difference" );
//
//        stjx.struct("DependsOn").
//                string( "name" ).
//                enm( "type").
//                string( "timestamp").
//                string( "seqno").
//                optional().string("to");

        stjx.generate( FSPathBuilder.getPath("/Users/stephan/tmp/licences").toString(), "org.openCage.pom4app" );

    }

}
