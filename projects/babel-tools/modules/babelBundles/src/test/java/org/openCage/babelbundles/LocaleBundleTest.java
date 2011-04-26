package org.openCage.babelbundles;

import org.junit.Test;
import org.openCage.babel.LocalePreference;
import org.openCage.babelBundles.LocaleBundle;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 20.01.11
 * Time: 09:53
 * To change this template use File | Settings | File Templates.
 */
public class LocaleBundleTest {

    @Test
    public void testSig() {
        new LocaleBundle( new LocalePreference()).English();
    }
}
