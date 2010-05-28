package org.openCage.property;

public class SimpleProp<T> extends PropertyImpl<T>{

    public SimpleProp( T deflt ) {
        super( null, deflt, "non persistent prop");
    }
}
