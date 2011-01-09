package org.openCage.ui;

import com.google.inject.Inject;
import org.openCage.ui.warning.HUDWarning;

import java.awt.Dialog;

public class MiniBrowserFactory {

    private CombinedBabel loc;
    private HUDWarning hudwarning;

    @Inject
    public MiniBrowserFactory( CombinedBabel loc, HUDWarning warning ) {
        this.loc = loc;
        this.hudwarning = warning;

    }

    public MiniBrowserDialog get( Dialog dialog) {
        return new MiniBrowserDialog( loc, hudwarning, dialog);
    }
}
