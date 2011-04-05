package org.openCage.generj;

import org.junit.Test;

import static org.openCage.generj.NameExpr.NAME;

public class NameExprTest {

    @Test
    public void testValidity() {
        NAME("arg");
        NAME("arg0");
    }
}
