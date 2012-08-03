package org.openCage.ruleofthree.jtothree;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.Threeable;
import org.openCage.ruleofthree.Threes;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class AClass implements Threeable {

    private final Long ll;
    private final String foo;

    @Inject
    public AClass( @Named("long") Long ll, @Named("foo")String foo ) {
        this.ll = ll;
        this.foo = foo;
    }

    public AClass() {
        ll = 7L;
        foo = "bar";
    }

    @Override
    public Three toThree() {
        return Threes.newMap().put( "long", ll ).put( "foo", foo );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AClass aClass = (AClass) o;

        if (foo != null ? !foo.equals(aClass.foo) : aClass.foo != null) return false;
        if (ll != null ? !ll.equals(aClass.ll) : aClass.ll != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ll != null ? ll.hashCode() : 0;
        result = 31 * result + (foo != null ? foo.hashCode() : 0);
        return result;
    }
}
