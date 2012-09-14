package org.openCage.notabug;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/13/12
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewArgs {

    @Argument(required = true)
    private String cmd;

    @Option( required = true, name="-n", metaVar = "<name>")
    private String name;


    @Option( required = false, name="-k", metaVar = "<bug>")
    private Kind kind;

    public String getName() {
        return name;
    }

    public Kind getKind() {
        return kind;
    }
}
