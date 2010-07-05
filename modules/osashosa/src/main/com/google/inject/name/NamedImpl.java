package com.google.inject.name;

import java.lang.annotation.Annotation;

public class NamedImpl implements Named {

    private final String name;

    public NamedImpl(String name) {
        this.name = name;
    }

    @Override
    public String value() {
        return name;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Named.class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedImpl)) return false;

        NamedImpl named = (NamedImpl) o;

        if (name != null ? !name.equals(named.name) : named.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
