/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.xplatform.wriring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.muchsoft.util.Sys;
import org.openCage.xplatform.impl.FileChooserOSX;
import org.openCage.xplatform.impl.FileChooserWindows;
import org.openCage.xplatform.protocol.FileChooser;

/**
 *
 * @author stephan
 */
public class XPlatformWiring implements Module {

    public void configure(Binder binder) {

        if ( Sys.isMacOSX() ) {
            binder.bind( FileChooser.class ).to( FileChooserOSX.class );
        } else if ( Sys.isWindows() ) {
            binder.bind( FileChooser.class ).to( FileChooserWindows.class );
        }
    }

}
