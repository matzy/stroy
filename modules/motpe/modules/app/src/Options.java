import org.kohsuke.args4j.Option;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 14, 2010
 * Time: 8:12:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Options {

    @Option( name = "-a", usage="some argument")
    private String argu;

    public String getArgu() {
        return argu;
    }
}
