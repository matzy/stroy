package org.openCage.comphy;

public interface PropertyStore {

    <T extends Property> T get(Key key, Dereadalizer<T> deread);

    void add(Key key, Property prop );
}
