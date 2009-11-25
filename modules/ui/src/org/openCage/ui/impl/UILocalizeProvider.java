/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Arrays;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;

/**
 *
 * @author stephan
 */
public class UILocalizeProvider implements Provider<Localize>{

    @Inject
    private LocalizeBuilder builder;

    public Localize get() {
        return builder.build("org.openCage.ui.impl.uitexts", Arrays.asList(builder.get()));
    }
}
