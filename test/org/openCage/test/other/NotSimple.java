package org.openCage.test.other;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Feb 9, 2009
 * Time: 2:37:22 PM
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("Simple")
public class NotSimple {
    @XStreamAlias("duda")
    String aa;

    @XStreamAlias("oo")
    String uu;

    public NotSimple( String foo, String uu ) {
        this.aa = foo;
        this.uu = uu;
    }
}
