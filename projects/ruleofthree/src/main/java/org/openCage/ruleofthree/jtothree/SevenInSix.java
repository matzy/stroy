package org.openCage.ruleofthree.jtothree;


import org.openCage.lang.Strings;

import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/19/12
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class SevenInSix {

    public static Locale getLocale(String languageTg) {

        String languageTag = languageTg.trim();


        if (null == languageTag) {

            return null;

        }


        String[] components = languageTag.split("_", 3);


        switch (components.length) {

            case 1:

                return new Locale(components[0]);

            case 2:

                return new Locale(components[0], components[1]);

            case 3:

                return new Locale(components[0], components[1], components[2]);

            default:

                return null;

        }

    }

}
