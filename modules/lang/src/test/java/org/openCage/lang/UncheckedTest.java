package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.errors.Unchecked;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

public class UncheckedTest {

    @Test
    public void testing() {
        IllegalArgumentException ill = new IllegalArgumentException("foo");
        Unchecked uc = Unchecked.wrap( ill  );

        assertEquals( "Unchecked Exception, Caused by: java.lang.IllegalArgumentException: foo", uc.toString());
        assertEquals( ill, uc.getSource());
    }


    public void foo() {
        try {
            new URL( "foo" );
        } catch (MalformedURLException e) {
            throw Unchecked.wrap(e);
        }
    }

    @Test( expected = Unchecked.class )
    public void testExecptionInMethodNotDeclaringThrows() {
        foo();
    }
}
