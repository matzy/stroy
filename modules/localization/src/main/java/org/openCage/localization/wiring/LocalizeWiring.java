package org.openCage.localization.wiring;

import com.google.inject.TypeLiteral;
import org.openCage.localization.impl.BundleCheckImpl;
import org.openCage.localization.impl.LocaleProperty;
import org.openCage.localization.impl.LocalizeBuilderImpl;
import org.openCage.localization.protocol.BundleCheck;
import org.openCage.localization.protocol.LocalizeBuilder;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.openCage.property.Property;

import java.util.Locale;

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

public class LocalizeWiring implements  Module {
    private static boolean once;

    public void configure(Binder binder) {

        if ( once ) {
            return;
        }
        once = true;


//		binder.bind( Localize.class ).
//			annotatedWith( Names.named("basic")).toProvider( LocalizeBuilderImpl.class );
//	}

        binder.bind( LocalizeBuilder.class ).to( LocalizeBuilderImpl.class );
        binder.bind( new TypeLiteral<Property<Locale>>() {} ).
                annotatedWith( Names.named( LocaleProperty.THE_LOCALE)).
                toProvider( LocaleProperty.class ).
                in( Singleton.class );

        binder.bind( BundleCheck.class).to( BundleCheckImpl.class );
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof LocalizeWiring;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

