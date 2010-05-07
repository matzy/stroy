package org.openCage.lang.artifact;

import org.junit.Test;
import org.openCage.lang.artifact.Project;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 25, 2010
 * Time: 9:09:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArtifactTest {

    @Test
    public void testEquals() {
        Project proj1 = new Project("1");
        Project proj2 = new Project("2");
        assertEquals( proj1.module( getClass(), "a", "b" ), proj1.module( getClass(), "a", "b" ));
        assertEquals( proj1.module( getClass(), "a", "b" ), proj2.module( getClass(), "a", "b" ));
        //assertEquals( proj1.module( "a", "b" ), proj2.external( "a", "b" ));
    }
}
