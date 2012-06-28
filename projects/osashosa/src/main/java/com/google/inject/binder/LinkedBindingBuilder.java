package com.google.inject.binder;

public interface LinkedBindingBuilder<T>  extends com.google.inject.binder.ScopedBindingBuilder {

    ScopedBindingBuilder to(java.lang.Class<? extends T> aClass);

    ScopedBindingBuilder to(com.google.inject.TypeLiteral<? extends T> typeLiteral);

    ScopedBindingBuilder to(com.google.inject.Key<? extends T> key);

    void toInstance(T t);

    ScopedBindingBuilder toProvider(com.google.inject.Provider<? extends T> provider);

    ScopedBindingBuilder toProvider(java.lang.Class<? extends com.google.inject.Provider<? extends T>> aClass);

    ScopedBindingBuilder toProvider(com.google.inject.Key<? extends com.google.inject.Provider<? extends T>> key);
}