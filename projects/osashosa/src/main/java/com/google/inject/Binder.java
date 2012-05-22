package com.google.inject;

public interface Binder {
    void install( Module module);

    <T> BindingBuilder<T> bind( Class<T> clazz);

    <T> BindingBuilder<T> bind( TypeLiteral<T> tTypeLiteral);

}
