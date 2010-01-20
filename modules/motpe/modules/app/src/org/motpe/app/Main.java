package org.motpe.app;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.motpe.moo.Moo;

public class Main {

    public static void main(String[] args) {

        Options bean = new Options();
        CmdLineParser parser = new CmdLineParser(bean);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar myprogram.jar [options...] arguments...");
            parser.printUsage(System.err);
            return;
        }

        if ( bean.getArgu() != null ) {
            System.out.println("argument was " + bean.getArgu() );
        }


        System.out.println( new Moo().what() );

        new BigLabel( new Moo().what() ).setVisible( true );
    }
}
