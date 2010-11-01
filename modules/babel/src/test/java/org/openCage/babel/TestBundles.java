package org.openCage.babel;

import org.junit.Test;
import sun.util.CoreResourceBundleControl;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 11/1/10
 * Time: 7:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestBundles {

    @Test
    public void tt() {
        ResourceBundle fallback = ResourceBundle.getBundle( "org.openCage.babel.testbundle", new Locale("") );

        assertEquals("ffff", fallback.getString("fallbackonly"));

        int i = 0;

        ResourceBundle de = ResourceBundle.getBundle( "org.openCage.babel.testbundle", new Locale("de") );

        //assertEquals("ffff", de.getString("fallbackonly"));

        Enumeration<String> keys = de.getKeys();
        while ( keys.hasMoreElements() )  {
            System.out.println(keys.nextElement());
        }

        for ( String key : de.keySet() ){
            System.out.println(key);
        }

        //de.



        assertFalse(de.containsKey("fallbackonly"));


    }

    @Test
    public void testControl() {
        ResourceBundle de = ResourceBundle.getBundle( "org.openCage.babel.testbundle", new Locale("de"),
                new ResourceBundle.Control() {
                    @Override
                    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
                        if (baseName == null)
                            throw new NullPointerException();
                        if (locale.equals(new Locale("de"))) {
                            return Arrays.asList(
                                    locale,
                                    new Locale("es"),
//                                    Locale.TAIWAN,
                                    // no Locale.CHINESE here
                                    Locale.ROOT);
                        } else if (locale.equals(Locale.TAIWAN)) {
                            return Arrays.asList(
                                    locale,
                                    // no Locale.CHINESE here
                                    Locale.ROOT);
                        }
                        return super.getCandidateLocales(baseName, locale);
                    }
                } );

        for ( String key : de.keySet() ){
            System.out.println(key);
        }

    }
}
