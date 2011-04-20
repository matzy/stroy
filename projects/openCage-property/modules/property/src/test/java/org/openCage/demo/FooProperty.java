package org.openCage.demo;

import org.openCage.lang.functions.F1;
import org.openCage.property.Property;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 23, 2009
 * Time: 2:25:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class FooProperty implements Property<String> {
    @Override
    public String get() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public void setDefault() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void modify(F1<String, String> modi) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addPropertyChangeListener(F1<Void, String> listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void set(String val) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
