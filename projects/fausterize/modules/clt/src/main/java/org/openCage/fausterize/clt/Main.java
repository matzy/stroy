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

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 17.05.11
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
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
