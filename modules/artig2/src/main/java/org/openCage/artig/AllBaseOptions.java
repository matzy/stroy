package org.openCage.artig;

import org.kohsuke.args4j.Argument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 2, 2010
 * Time: 8:44:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllBaseOptions {
    @Argument()
    private List<String> args = new ArrayList<String>();

    public List<String> getArgs() {
        return args;
    }


}
