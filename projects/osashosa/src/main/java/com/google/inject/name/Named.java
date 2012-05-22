package com.google.inject.name;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.METHOD})
@com.google.inject.BindingAnnotation
public @interface Named {

    java.lang.String value();
}