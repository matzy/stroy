package org.openCage.lispaffair;


import org.junit.Test;

import java.text.ParseException;

import static junit.framework.Assert.assertEquals;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 11/21/11
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class EnvironmentCapture {

    @Test
    public void testCapture() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "(block (set a 1) (set c (clone)) (set a 100) (. c a))" );

        assertEquals(1, Eval.eval(obj, new Lispaffair().getEnv()));

    }

}
