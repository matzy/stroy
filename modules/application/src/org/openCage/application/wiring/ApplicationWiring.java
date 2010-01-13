package org.openCage.application.wiring;

import org.openCage.application.Constants;
import org.openCage.application.impl.ApplicationFromConfigXStream;
import org.openCage.application.impl.ApplicationLocalizeProvider;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.openCage.withResource.wiring.IoWiring;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class ApplicationWiring implements Module{

    private static boolean once = false;

	@Override
    public void configure(Binder binder ) {

        if ( once ) {
            return;
        }
        once = true;

		binder.install( new IoWiring() );
		binder.install( new LocalizeWiring());

		binder.bind( ApplicationFromConfig.class).
			to( ApplicationFromConfigXStream.class );
		binder.bind( Localize.class ).
			annotatedWith( Names.named(Constants.APPLICATION)).toProvider( ApplicationLocalizeProvider.class );
//                binder.bind( UpdateChecker.class ).to( UpdateChecker.class );
	}

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ApplicationWiring);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
