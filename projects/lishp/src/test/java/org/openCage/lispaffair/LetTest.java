package org.openCage.lispaffair;

import org.junit.Test;

import java.text.ParseException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 12/12/11
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class LetTest {
    @Test
    public void testLet1() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "(let " +
                "     x             5 " +
                " in" +
                "     x)" );

        assertEquals(5, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testLet2() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(let " +
                        "     x             5 " +
                        "     y             7 " +
                " in" +
                "     x)" );

        assertEquals(5, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testLet() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(let (: h1 h2 tl)  '(1 2 3 4 5)" +
                        "     x     5 " +
                        " :in" +
                        "     h1 " +
                        "     h2)" );

        assertEquals(2, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testLetPair() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(let (: (key val) tl)   '((1 2) (3 4) 5)" +
                "     x                  5 " +
                " in" +
                "     key " +
                "     val)" );

        assertEquals(2, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testCatch() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(try (let (x)     5 " +
                "          in" +
                "        x)" +
                "  catch exp" +
                "  finally 'huh)" );

        assertEquals(5, Eval.eval(obj, new Lispaffair().getEnv()));
    }

}
