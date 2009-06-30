package org.openCage.stroy2;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 28, 2009
 * Time: 3:19:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hallo World");

        CmdBuilder cmdBuilder = new CmdBuilder( args );
        
        if ( !cmdBuilder.isOk() ) {
            cmdBuilder.printUsage();
            System.exit(1);
        }

        StroyCmd cmd = cmdBuilder.build();

        new Stroy( cmd.getLeft(), cmd.getRight() );

    }
}
