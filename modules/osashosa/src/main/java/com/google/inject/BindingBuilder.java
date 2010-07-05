package com.google.inject;

import com.google.inject.name.Named;
import org.openCage.sip.Key;

public class BindingBuilder<T> {

    private Class<? extends T> to;
    private Class<? extends Provider<? extends T>> provider;
    private String name = "";
    private Class scope;
    private Key<T> key;
    private T singleton;
    private String moduleName;

    public BindingBuilder( Class<T> clazz ) {
        this.key = Key.get( clazz );
    }

    public BindingBuilder(TypeLiteral<T> literal) {
        key = new Key<T>( literal );
    }

    public BindingBuilder(Key<T> key) {
        this.key = key;
    }

    public BindingBuilder to(Class<? extends T> clazz) {
        if ( clazz.isAssignableFrom( Provider.class ) ) {
            int i = 0;
        }
        to = clazz;
        return this;
    }

    public BindingBuilder toProvider( Class<? extends Provider<? extends T>> provider ) {
        this.provider = provider;
        return this;
    }

    public BindingBuilder annotatedWith(Named named) {
        this.name = named.value();
        return this;
    }

    public BindingBuilder in( Class scope ) {
        this.scope = scope;
        return this;
    }


    public Class<? extends T> getTo() {
        return to;
    }

    public Class getProvider() {
        return provider;
    }

    public String getName() {
        return name;
    }

    public Class getScope() {
        return scope;
    }

    public Key<T> getKey() {
        return key;
    }

    public boolean isDirect() {
        return to != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BindingBuilder)) return false;

        BindingBuilder that = (BindingBuilder) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }

    public void setSingleton( T sing ) {
        this.singleton = sing;
    }

    public T getSingleton() {
        return singleton;
    }

    public boolean isSingletonScope() {
        return scope != null && scope == Singleton.class;
    }

    public void setModuleName( String name ) {
        this.moduleName = name;
    }

    @Override
    public String toString() {
        return "BindingBuilder{" +
                "to=" + to +
                ", provider=" + provider +
                ", name='" + name + '\'' +
                ", scope=" + scope +
                ", key=" + key +
                ", singleton=" + singleton +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
