package org.openCage.huffman.clt;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

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
    }
}
