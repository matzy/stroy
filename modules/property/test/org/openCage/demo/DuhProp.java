package org.openCage.demo;

import org.openCage.property.clazz.AbstractPropertyProvider;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 23, 2009
 * Time: 5:05:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DuhProp extends AbstractPropertyProvider<Integer>{

    public static final String HICKER = "hicker";

    public DuhProp() {
        super(HICKER, 42 );
    }
}
