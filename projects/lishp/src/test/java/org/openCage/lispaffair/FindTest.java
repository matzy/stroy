package org.openCage.lispaffair;

import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 12/23/11
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class FindTest {

    @Test
    public void testSimple() throws ParseException {

        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '(1 2 3 7) (fct (x) (= x 3)))" );

        assertTrue((Boolean) Eval.eval(obj, new Lispaffair().getEnv()));
    }
    
    @Test
    public void testSome() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '(1 2 3 7) (fct (x) (= x 3)) (fct (x) (* x 3)))" );

        assertEquals(9, ((List) Eval.eval(obj, new Lispaffair().getEnv())).get(1));
        
    }

    @Test
    public void testSomeNot() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '(1 2 3 7) (fct (x) (= x 33)) (fct (x) (* x 3)))" );

        assertTrue( !(Boolean)((List) Eval.eval(obj, new Lispaffair().getEnv())).get(0));

    }

    @Test
    public void testTags() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '((foo 5) (du 7) (tags \"123 4\")) " +
                        "(fct (x) (let (key val) x in (= key 'tags))) " +
                        "(fct (x) (let (key val) x in val)))" );

        List answ =  ((List) Eval.eval(obj, new Lispaffair().getEnv()));
        
        assertTrue((Boolean) answ.get(0));
        assertEquals( "123 4", answ.get(1));

    }
}
