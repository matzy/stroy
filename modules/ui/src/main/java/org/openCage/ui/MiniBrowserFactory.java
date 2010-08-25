package org.openCage.ui;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.localization.Localize;
import org.openCage.ui.warning.HUDWarning;

import java.awt.Dialog;

public class MiniBrowserFactory {

    private Localize loc;
    private HUDWarning hudwarning;

    @Inject
    public MiniBrowserFactory( @Named( Constants.UI)Localize loc, HUDWarning warning ) {
        this.loc = loc;
        this.hudwarning = warning;

    }

    public MiniBrowserDialog get( Dialog dialog) {
        return new MiniBrowserDialog( loc, hudwarning, dialog);
    }
}
