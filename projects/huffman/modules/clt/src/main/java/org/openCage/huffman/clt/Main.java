package org.openCage.huffman.clt;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openCage.huffman.BitField;
import org.openCage.huffman.BitList;
import org.openCage.huffman.Huffman;
import org.openCage.huffman.HuffmanN;
import org.openCage.io.Resource;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.functions.FE1;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Handler;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 5/9/11
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    @Argument(required = true)
    private String source;
    private URI uriSource;

    @Option(name = "-b")
    private int wordLength;

    @Option( name = "-d", required = false )
    private boolean decode = false;

    @Option( name = "-o", required = true )
    private String target;
    private FSPath targetPath;


    public static void main(String[] args) {
        // parse the command line arguments and options
        Main values = new Main();
        CmdLineParser parser = new CmdLineParser(values);
        parser.setUsageWidth(80); // width of the error display area

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java DotsMain [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();
            System.exit(1);
        }

        System.out.println( values.source );

        values.init();


        if ( values.decode ) {
            values.decode();
        } else {
            values.encode();
        }
    }

    public void init() {
        getURI();
        getTarget();
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

    public void getTarget() {
        targetPath = FSPathBuilder.getPath( new File(target) );
    }

    public void encode()  {

        Resource.tryWith( new FE1<Object, Resource>() {
            @Override public Object call(Resource resource) throws Exception {

                BitField encoded = new HuffmanN(BitList.valueOf(resource.add(new BufferedInputStream(uriSource.toURL().openStream())))).encode(8);

                OutputStream os = resource.add( new FileOutputStream( targetPath.toFile()));

                os.write( encoded.toByteArray() );

                return null;
            }
        });
    }

    public void decode() {
        Resource.tryWith( new FE1<Object, Resource>() {
            @Override public Object call(Resource resource) throws Exception {
                BitField decoded = HuffmanN.decode( BitList.valueOf(resource.add(new BufferedInputStream(uriSource.toURL().openStream()))));
                OutputStream os = resource.add( new FileOutputStream( targetPath.toFile()));

                os.write( decoded.toByteArray() );
                return null;
            }
        });
    }

}
