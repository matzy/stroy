package org.openCage.comphy;

import com.google.inject.Provider;

public abstract class AbstractPropertyProvider<T extends Property> implements Provider<T> {

    private final PropertyStore5 store;
    private final Dereadalizer<T> deread;
    private final T deflt;
    private final String key;

    public AbstractPropertyProvider(PropertyStore5 store, Dereadalizer<T> deread, T deflt, String key) {
        this.store = store;
        this.deread = deread;
        this.deflt = deflt;
        this.key = key;
    }

    public T get() {
        T ret = store.get(key, deread);

        if (ret == null) {
            ret = deflt;
            store.add(key, ret);
        }

        return ret;
    }
}

