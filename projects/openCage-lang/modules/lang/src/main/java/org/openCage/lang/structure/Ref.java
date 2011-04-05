package org.openCage.lang.structure;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 24.11.10
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class Ref<T> {

    private T obj;

    public Ref( T t ) {
        obj = t;
    }

    public T get() {
        return obj;
    }

    public void set(T obj) {
        this.obj = obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ref ref = (Ref) o;

        if (obj != null ? !obj.equals(ref.obj) : ref.obj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return obj != null ? obj.hashCode() : 0;
    }
}
