/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.withResource;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.protocol.With;
import org.openCage.withResource.wiring.IoWiring;

/**
 *
 * @author stephan
 */
public class WithTest {

    @Test( expected=Unchecked.class)
    public void testNonExisitngFile() {
        Injector injector = Guice.createInjector(new IoWiring());
        With with = injector.getInstance(With.class);

        with.withInputStream( new File( "idontexist" ), new FE1<Void, InputStream>() {

            public Void call(InputStream a) throws Exception {
                return null;
            }
        } );
    }

    @Test( expected=Unchecked.class )
    public void testExeptionWhileReading() {
        Injector injector = Guice.createInjector(new IoWiring());
        With with = injector.getInstance(With.class);


        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new FE1<Void, InputStream>() {

            public Void call(InputStream a) {
                throw new IllegalArgumentException();
            }
        } );
    }

    @Test( expected=Unchecked.class )
    public void testWithExecption() {
        Injector injector = Guice.createInjector(new IoWiring());
        With with = injector.getInstance(With.class);

        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new FE1<Void, InputStream>() {

            public Void call(InputStream a) {
                throw Unchecked.wrap( new IOException());
            }
        } );
    }

    @Test
    public void testReadSomething() {
        Injector injector = Guice.createInjector(new IoWiring());
        With with = injector.getInstance(With.class);

        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new FE1<Void, InputStream>() {

            public Void call(InputStream a) throws IOException {
                a.read();
                return null;
            }
        } );
    }

    @Test
    public void testClosingStreamInRead() {
        Injector injector = Guice.createInjector(new IoWiring());
        With with = injector.getInstance(With.class);

        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new FE1<Void, InputStream>() {

            public Void call(InputStream a) throws IOException {
                a.close();
                return null;
            }
        } );

    }


}
