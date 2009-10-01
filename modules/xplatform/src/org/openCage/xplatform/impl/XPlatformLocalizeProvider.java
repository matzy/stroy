/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.xplatform.impl;

import com.google.inject.Inject;
import java.util.Arrays;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;

/**
 *
 * @author stephan
 */
public class XPlatformLocalizeProvider {

    @Inject
    private LocalizeBuilder builder;

    public Localize get() {
        return builder.build("org.openCage.xlatform.impl.xplatformtexts", Arrays.asList(builder.get()));
    }
}
