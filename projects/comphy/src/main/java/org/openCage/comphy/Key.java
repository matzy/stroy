package org.openCage.comphy;

import java.util.regex.Pattern;

public class Key implements Comparable<Key>, Readable{

    static private Pattern pattern = Pattern.compile("([A-Z]|[a-z]|\\-|\\.|_|[0-9])+");

    private final String str;

    public Key( final String str ) {
        if ( !pattern.matcher(str).matches() ) {
            throw new IllegalArgumentException("not a legal key to be used as XML tag " + str );
        }
        this.str = str;
    }

    public String get() {
        return str;
    }

    @Override
    public int compareTo(Key o) {
        return str.compareTo(o.get());
    }

    @Override
    public String toString() {
        return str;
    }

    public static Key valueOf( String str ) {
        return new Key( str );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (str != null ? !str.equals(key.str) : key.str != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return str != null ? str.hashCode() : 0;
    }
}
