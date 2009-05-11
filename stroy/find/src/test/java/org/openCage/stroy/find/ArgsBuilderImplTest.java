package org.openCage.stroy.find;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

public class ArgsBuilderImplTest {


    @Test
    public void testHelp() {
        ArgsBuilder builder = new ArgsBuilderImpl( (String[])Arrays.asList( "--help" ).toArray());

        assertFalse( builder.isOk());
    }
}
