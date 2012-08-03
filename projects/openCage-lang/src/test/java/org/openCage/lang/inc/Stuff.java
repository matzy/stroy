package org.openCage.lang.inc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/16/12
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stuff {


    public static interface Foo {}

    public static class Foo1 implements Foo {}

    public void test() {
        List<Foo> ll = new ArrayList<Foo>();

        ll.add( new Foo1());
    }
}
