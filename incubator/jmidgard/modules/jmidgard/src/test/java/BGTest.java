import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 8/21/11
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class BGTest {

    @Test
    public void testBD() {
        String a = "1,2";
        System.out.println(a.replace(',', '.'));
        System.out.println(a);
        //assertEquals("1.2", new BigDecimal("1,2").toString());

        //new BigDecimal("abc");

        System.out.println( new BigDecimal("101.2").remainder( new BigDecimal("10.1")));

    }
}
