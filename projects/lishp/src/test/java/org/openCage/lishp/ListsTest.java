package org.openCage.lishp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 1/18/12
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class ListsTest {

    @Test
    public void testSimple() {
        
        List<Integer> ll = Arrays.asList( 1,2,3,4,5,6,7,8,9);
        
        List<List<Object>> res = Lists.split( (List)ll, 2, 7, 12 );

        assertEquals( 1, res.get(0).size());
        assertEquals( 5, res.get(1).size());
        assertEquals( 3, res.get(2).size());

    }


}
