package org.openCage.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Feb 9, 2009
 * Time: 2:27:57 PM
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("Simple")
public class Simple {

    @XStreamAlias("duda")
    String foo;

    public Simple( String foo ) {
        this.foo = foo;
    }
}
