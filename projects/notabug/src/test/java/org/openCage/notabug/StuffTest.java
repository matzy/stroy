package org.openCage.notabug;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/25/12
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class StuffTest {

    @Test
    public void test() {
        System.out.println( Id.next());

        Id foo = Id.next();
        System.out.println(foo);
        System.out.println( foo.toString().substring(0,18) + "-" + foo.toString().substring( 19 ));
    }
}
