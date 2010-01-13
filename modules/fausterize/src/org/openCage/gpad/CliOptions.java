package org.openCage.gpad;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 13, 2010
 * Time: 5:32:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class CliOptions {


    @Option( name="-a", usage="set the encoding algorithm")
    private String algorithm;

    @Argument
    private String filepath;

    public String getFilepath() {
        return filepath;
    }
}
