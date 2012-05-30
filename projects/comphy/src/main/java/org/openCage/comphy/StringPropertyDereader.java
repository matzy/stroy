package org.openCage.comphy;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringPropertyDereader implements Dereadalizer<StringProperty> {
    @Override
    public StringProperty fromString( RString str) {
        return new StringProperty(str.get());
    }

    @Override
    public StringProperty fromList(RList lst) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StringProperty fromMap(RMap map) {
        throw new IllegalArgumentException( "can't create a RString from a map" );
    }


}
