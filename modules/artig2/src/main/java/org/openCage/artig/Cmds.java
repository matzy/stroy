package org.openCage.artig;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 2, 2010
 * Time: 8:51:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cmds {

    @Argument()
    private List<String> arguments = new ArrayList<String>();

    private Map<String,CmdArgs> cmds = new HashMap<String, CmdArgs>();

    public Cmds cmd( CmdArgs cargs ) {
        cmds.put( cargs.getName(), cargs );
        return this;
    }

    public CmdArgs parse( String[] args ) {
        CmdLineParser baseParser = new CmdLineParser(this);
        try {
            baseParser.parseArgument( args );
        } catch (CmdLineException e) {
            baseParser.printUsage( System.out );
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if ( arguments.size() == 0 || !cmds.containsKey( arguments.get(0))) {
            System.out.println("HELP");
            return null;
        }

        CmdArgs details = cmds.get( arguments.get(0));
        CmdLineParser detailsParser = new CmdLineParser(details );

        return details;
    }
}
