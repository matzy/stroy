package org.openCage.lang.artifact;

import net.jcip.annotations.Immutable;

import java.util.HashMap;
import java.util.Map;

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
    private final String shortName;
    private final String address;
    private final Version version;

    private static Map<String,Licence> all = new HashMap<String, Licence>();

    public Licence( String name, String shortName, String address, String version  ) {
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.version = Version.valueOf( version );

        all.put( name, this );
    }

    public String gettName() {
        return name;
    }

    // TODO
    public boolean isOpenSource() {
        return true;
    }

    @Override public String toString() {
        return "Licence{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", address='" + address + '\'' +
                ", version=" + version +
                '}';
    }

    public static Licence valueOf( String str ) {
        Licence ret = all.get( str );
        if ( ret == null ) {
            throw new IllegalArgumentException("no such licence " + str );
        }

        return ret;
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Licence)) { return false; }

        Licence licence = (Licence) o;

        if (name != null ? !name.equals(licence.name) : licence.name != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public boolean canUse( Licence dep ) {

        // can I use gpl
        if ( dep.equals( gpl2 )) {
            if ( !equals( gpl2 )) {
                return false;
            }
        }

        // if i am apache2, what can I use
        // http://www.apache.org/legal/3party.html#transition
        // but http://de.wikipedia.org/wiki/Apache-Lizenz states that lgpl3 is ok
        if ( equals( apache2 )) {
            if ( dep.equals( gpl2 ) ||
                    dep.equals( gpl3 ) ||
                    dep.equals( lgpl2 ) /* ||
                    dep.equals( lgpl3 )*/) {
                return false;
            }
        }

        return true;
    }


    public static final Licence apache2 = new Licence( "Apache2", "Apache", "http://www.apache.org/licenses/LICENSE-2.0.html", "2.0" );
    public static final Licence mpl11 = new Licence( "MPL1.1", "Mozilla", "http://www.mozilla.org/MPL/MPL-1.1.html", "1.1" );
    public static final Licence gpl2 = new Licence( "GPL2", "GPL", "http://www.gnu.de/documents/gpl-2.0.de.html", "2.0" );
    public static final Licence gpl3 = new Licence( "GPL3", "GPL", "http://www.gnu.de/documents/gpl-2.0.de.html", "3.0" ); // TODO
    public static final Licence lgpl2 = new Licence( "LGPL2", "LGPL", "http://www.gnu.de/documents/lgpl-2.1.de.html", "2.1" );
    public static final Licence lgpl3 = new Licence( "LGPL3", "LGPL", "http://www.gnu.org/licenses/lgpl.html", "3.0" );
    public static final Licence bsd = new Licence( "BSD", "BSD", "http://www.opensource.org/licenses/bsd-license.php", "1989" );  // TODO details
    public static final Licence json = new Licence( "JSON", "JSON", "http://www.json.org/license.html", "2002" );
    public static final Licence creativeCommons = new Licence( "creativeCommons", "Creative Commons", "http://creativecommons.org/licenses/publicdomain/", "pre 3.0" );
    public static final Licence cpl = new Licence( "CPL", "CPL", "http://www.eclipse.org/legal/cpl-v10.html", "1.0" );
    public static final Licence mit = new Licence( "MIT", "MIT", "http://www.opensource.org/licenses/mit-license.php", "1.0" );
    public static final Licence muchsoft = new Licence( "MuchSoft", "Muchsoft", "http://www.muchsoft.com/java/docs/com/muchsoft/util/Sys.html", "2004" );
    public static final Licence publicDomain = new Licence( "Public Domian", "Public Domian", "http://en.wikipedia.org/wiki/Public_domain", "1.0"  ); // TODO

}
