package org.openCage.fausterize.clt;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.openCage.fausterize.FaustString;
import org.openCage.io.Resource;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.functions.FE1;
import org.openCage.lang.iterators.Iterators;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

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

public class Main {

    @Argument(required = true)
    private String source;

    @Option( required = true, name="-p")
    private String pad;
    private URI uriPad;
    private URI uriSource;

    @Option( required = false, name="-d")
    private boolean decode = false;

    public static void main(String[] args) {
        // parse the command line arguments and options
        final Main values = new Main();
        CmdLineParser parser = new CmdLineParser(values);
        parser.setUsageWidth(80); // width of the error display area

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("fausterize [options...] arguments...");
            parser.printUsage( System.err );
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();
            System.exit(1);
        }

        System.out.println( values.source );

        values.init();

        FaustString ds = new FaustString();
        ds.setPad( values.uriPad );

        String txt = Resource.tryWith(new FE1<String, Resource>() {
            @Override public String call(Resource resource) throws Exception {

                BufferedReader reader = resource.add(
                        new BufferedReader(new InputStreamReader( values.uriSource.toURL().openStream() )));

                StringBuilder ret = new StringBuilder();
                int idx = 0;
                for (String str : Iterators.lines(reader)) {
                    ret.append(str);
                    ret.append("\n");
                }
                return ret.toString();
            }
        });

        if ( values.decode ) {

            String dec = ds.decode( txt, 0 );
            System.out.println(dec);
        } else {
            String dec = ds.encode( txt, 0 );
            System.out.println(dec);

        }
    }

    public void init() {
        getURI();
        getPadUri();
    }

    public void getURI() {
        try {
            uriSource = new URI( source );
            if ( uriSource.getScheme() != null ) {
                return;
            }
        } catch (URISyntaxException e) {
        }

        FSPath path = FSPathBuilder.getPath( new File(source) );
        if ( !path.toFile().exists() ){
            throw new IllegalArgumentException("no such file" + source );
        }

        uriSource = path.toURI();
    }


    public void getPadUri() {
        try {
            uriPad = new URI( pad );
            if ( uriPad.getScheme() != null ) {
                return;
            }
        } catch (URISyntaxException e) {
        }

        FSPath path = FSPathBuilder.getPath( new File(pad) );
        if ( !path.toFile().exists() ){
            throw new IllegalArgumentException("no such file" + pad );
        }

        uriPad = path.toURI();
    }



}
