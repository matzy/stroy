package com.google.inject;

public interface Injector {

    <T> T getInstance( Class<T> tClass);

    <T> T getInstance( TypeLiteral<T> literal );

    void injectMembers(java.lang.Object o);

    <T> T getInstance(String named, TypeLiteral<T> literal);
}
