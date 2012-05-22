package org.openCage.osashosa;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 21.06.2010
 * Time: 17:23:04
 * To change this template use File | Settings | File Templates.
 */
public class Stuff {

    public static class Gen {
        public Gen( List<String> foo ) {}
    }

    @Test
    public void testGen() {
        Constructor con = Gen.class.getConstructors()[0];
        Type ll = con.getGenericParameterTypes()[0];

        int i = 0;

    }
}
