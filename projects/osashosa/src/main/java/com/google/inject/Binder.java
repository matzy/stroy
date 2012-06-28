package com.google.inject;

import org.openCage.osashosa.BindingBuilder;

public interface Binder {
    void install( Module module);

    <T> com.google.inject.binder.AnnotatedBindingBuilder<T> bind( Class<T> clazz);

    <T> BindingBuilder<T> bind( TypeLiteral<T> tTypeLiteral);

}
