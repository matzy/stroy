package org.openCage.artig;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 17, 2010
 * Time: 3:14:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CmdOptions {

    @Option( name="-v", usage="print verbose massages" )
    private boolean verbose = false;

    @Option( name="-d", usage="the project directory", required = true )
    private String dir;

    @Argument
    private List<String> args = new ArrayList<String>();

    public boolean isVerbose() {
        return verbose;
    }

    public List<String> getArgs() {
        return args;
    }

    public String getDir() {
        return dir;
    }
}
