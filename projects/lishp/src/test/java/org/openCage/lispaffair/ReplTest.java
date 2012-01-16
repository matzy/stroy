package org.openCage.lispaffair;

import org.junit.Test;
import org.openCage.lishp.Symbol;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 11/12/11
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReplTest {


    @Test
    public void testPlus() throws ParseException {


    }

    @Test
    public void testPlusList() throws ParseException {
        LispFormat frmt = new LispFormat();

        List ll = new ArrayList();
        ll.add( Symbol.get("+"));
        ll.add( 3 );
        ll.add( 4 );

        assertEquals(7, Eval.eval(ll, new Lispaffair().getEnv()));

    }
}
