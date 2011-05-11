package org.openCage.huffman.clt;

import com.sun.jndi.toolkit.url.UrlUtil;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.openCage.huffman.BitField;
import org.openCage.huffman.BitFieldImpl;
import org.openCage.huffman.HuffmanN;
import org.openCage.io.Resource;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.functions.FE1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

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

    @Option(name = "b")
    private int wordLength;


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

        try {
            values.encode( new URI(values.source ), FSPathBuilder.getDocuments().add("tmp.huff"));
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void encode( final URI uri, final FSPath target )  {

        Resource.tryWith( new FE1<Object, Resource>() {
            @Override public Object call(Resource resource) throws Exception {

                BitField encoded = new HuffmanN(BitFieldImpl.valueOf( resource.add( new BufferedInputStream( uri.toURL().openStream())))).encode(8);

                OutputStream os = resource.add( new FileOutputStream( target.toFile()));


                os.write( encoded.toByteArray() );

                return null;
            }
        });



    }
}
