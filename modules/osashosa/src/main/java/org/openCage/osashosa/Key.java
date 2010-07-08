package org.openCage.osashosa;

import com.google.inject.TypeLiteral;

import java.lang.reflect.Type;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/
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
