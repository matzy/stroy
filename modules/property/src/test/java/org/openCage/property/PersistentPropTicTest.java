package org.openCage.property;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.lang.Sisl;
import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.F1;
import org.openCage.io.With;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 27, 2010
 * Time: 11:22:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersistentPropTicTest {

    private static File backing = FSPathBuilder.getTmpFile("xml").toFile();

    @BeforeClass
    public static void setUp() {
        System.out.println("up");
        backing.delete();

        new With().withWriter( backing, new F1<Void, Writer>() {
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

        Sisl.bindSingleton( Property.class, "A", new F0<Property>() {
            @Override
            public Property<String> call() {
                return PersistentProp.get( Sisl.get( PropStore.class ), "key", "BBB", "hah");
            }
        });

        Sisl.bindSingleton( PropStore.class, new F0<PropStore>() {
            @Override
            public PropStore call() {
                return new PersistingPropStore( Sisl.get( BackgroundExecutor.class ), backing );
            }});

        Sisl.bind( BackgroundExecutor.class, new F0<BackgroundExecutor>() {
            @Override
            public BackgroundExecutor call() {
                return new BackgroundExecutorImpl();
            }
        });
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("down");
        backing.delete();
    }


    @Test
    public void testA() {
        Property<String> a = Sisl.get( Property.class, "A");
        a.set( "AAA");
    }




}
