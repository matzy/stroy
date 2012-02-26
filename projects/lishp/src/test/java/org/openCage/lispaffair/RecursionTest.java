package org.openCage.lispaffair;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 11/21/11
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecursionTest {

    @Test
    public void testRecursion() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "(block (set f (fct (x) (if (= x 1) 1 (f (- x 1)))))  (f 2))" );

        assertEquals(1, Eval.eval(obj, new Lispaffair().getEnv()));

    }

    @Test
    public void testMutualRecursion() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "(block (set f (fct (x) (if (= x 1) 1 (g (- x 1))))) (set g (fct (x) (if (= x 1) 1 (f (- x 1))))) (f 3))" );

        assertEquals(1, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testArgs() throws  ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "((fct (x) ((fct (x) #n) 7) x) 5)" );

        assertEquals(5, Eval.eval(obj, new Lispaffair().getEnv()));

    }

}
