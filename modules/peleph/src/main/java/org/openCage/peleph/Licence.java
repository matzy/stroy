package org.openCage.peleph;

import net.jcip.annotations.Immutable;

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

@Immutable
public class Licence {

	private final String name;

	public Licence( String name ) {
		this.name = name;
	}


    public String gettName() {
        return name;
    }

    public boolean isOpenSource() {
		return true;
	}

    @Override
    public String toString() {
        return "Licence{" +
                "name='" + name + '\'' +
                '}';
    }

    public static Licence apache2() {
        return new Licence( "Apache2" );
    }

    public static Licence mpl11() {
        return new Licence( "MPL1.1" );
    }
}
