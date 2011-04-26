package org.openCage.babelgenPlugin;

import org.junit.Test;
import org.openCage.io.fspath.FSPathBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 18.01.11
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class CollectTest {

    @Test
    public void testSimple() {

        new CollectProps(FSPathBuilder.getPath( "C:\\Users\\spf\\Documents\\prs\\babel-tools\\babelBundles\\modules\\babelBundles\\src\\main\\resources" ));
    }

}
