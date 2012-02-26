package org.openCage.lispaffair;

import org.junit.Test;
import org.openCage.lishp.Symbol;

import java.text.ParseException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 1/17/12
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class TryCatchTest {

    @Test
    public void testSimple() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(try (throw foo)" +
                "  :catch foo 'caught" +
                "  :finally 'fin)" );

        assertEquals( Symbol.get("caught"), Eval.eval(obj, new Lispaffair().getEnv()));

    }
}
