package org.openCage.comphy;

public interface Dereadalizer<T> {

    T fromString( RString str );
    T fromList( RList lst );
    T fromMap( RMap map );

}
