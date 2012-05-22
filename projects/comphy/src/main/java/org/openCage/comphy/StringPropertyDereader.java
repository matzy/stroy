package org.openCage.comphy;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringPropertyDereader implements Dereadalizer<StringProperty> {
    @Override
    public StringProperty fromString(String str) {
        return new StringProperty(str);
    }

    @Override
    public StringProperty fromList(List<Readable> lst) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StringProperty fromMap(Map<String, Readable> map) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
