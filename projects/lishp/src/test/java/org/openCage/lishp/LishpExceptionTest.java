package org.openCage.lishp;

import org.junit.Test;

public class LishpExceptionTest {

    public void noExp() {
        throw new LishpException( Symbol.get("duh"), "foo");
    }

    @Test( expected = LishpException.class )
    public void testRuntime() {
        noExp();
    }
}
