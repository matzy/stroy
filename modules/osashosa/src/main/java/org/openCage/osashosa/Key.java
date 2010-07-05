package org.openCage.sip;

import com.google.inject.TypeLiteral;

import java.lang.reflect.Type;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 22.06.2010
 * Time: 06:29:49
 * To change this template use File | Settings | File Templates.
 */
public class Key<T> {

    private TypeLiteral<T> literal;

    public Key(TypeLiteral<T> literal) {
        this.literal = literal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;

        Key key = (Key) o;

        if (literal != null ? !literal.equals(key.literal) : key.literal != null) return false;

        return true;
    }

    public static <T> Key<T> get( Class<T> clazz ) {
        return new Key<T>( TypeLiteral.get(clazz ));
    }

    @Override
    public int hashCode() {
        return literal != null ? literal.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Key{" +
                "literal=" + literal +
                '}';
    }

    public TypeLiteral<T> getLiteral() {
        return literal;
    }

    public static <T> Key<T> get(Type para) {
        return new Key( TypeLiteral.get( para ));
    }
}
