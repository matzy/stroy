package org.openCage.lindwurm.json;

import org.junit.Test;
import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.Ignore;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/23/12
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonLindwurmBuilderTest {

    @Test
    public void simple() {
        new JsonLindwurmBuilder().build(Null.of(Ignore.class), getClass().getResourceAsStream( "/org/openCage/lindwurm/json/simple1.json" ));
    }
}
