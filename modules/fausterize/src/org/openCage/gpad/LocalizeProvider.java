package org.openCage.gpad;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 11, 2009
 * Time: 8:40:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocalizeProvider implements Provider<Localize> {

    @Inject
    private LocalizeBuilder builder;

    public Localize get() {
        return builder.build("org.openCage.gpad.fausterize-text", Arrays.asList(builder.get()));
    }
}
