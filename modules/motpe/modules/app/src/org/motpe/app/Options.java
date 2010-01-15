package org.motpe.app;

import org.kohsuke.args4j.Option;

public class Options {

    @Option( name = "-a", usage="some argument")
    private String argu;

    public String getArgu() {
        return argu;
    }
}
