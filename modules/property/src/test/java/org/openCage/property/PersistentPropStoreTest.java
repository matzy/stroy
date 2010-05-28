package org.openCage.property;

import org.junit.*;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.lang.functions.F1;
import org.openCage.withResource.impl.WithImpl;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 26, 2010
 * Time: 3:57:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistentPropStoreTest {

    private static File backing = FSPathBuilder.getTmpFile("xml").toFile();

    public static class SingletonPropertyProvider {

        public static Property<String> get() {
            return PersistentProp.get( new PersistingPropStore( new BackgroundExecutorImpl(), backing ), "key", "dflt", "description");
        }
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("down");
        backing.delete();
    }

    @BeforeClass
    public static void setUp() {
        System.out.println("up");
        backing.delete();

        new WithImpl().withWriter( backing, new F1<Void, Writer>() {
            @Override
            public Void call(Writer writer) {
                try {
                    writer.append("<map>\n" +
                            "  <entry>\n" +
                            "    <string>key</string>\n" +
                            "    <Property>\n" +
                            "      <obj class=\"string\">AAA</obj>\n" +
                            "      <description>description</description>\n" +
                            "    </Property>\n" +
                            "  </entry>\n" +
                            "</map>");
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return null;
            }
        });
    }

    @Test
    public void testA() {
        Property<String> a = SingletonPropertyProvider.get();

//        assertEquals( "dflt", a.get() );

        a.set( "AAA");
    }

//    @Test
//    public void testB() {
//
//        File ff = backing;
//
//        Property<String> b = SingletonPropertyProvider.the.get();
//        assertEquals( "AAA", b.get() );
//    }

}
