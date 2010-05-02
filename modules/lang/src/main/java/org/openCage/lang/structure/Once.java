package org.openCage.lang.structure;

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

public class Once<T> {

    private T val;
    private boolean set = false;

    public Once( T deflt ) {
        val = deflt;
    }

    public synchronized T get() {
        set = true;
        return val;
    }

    public synchronized void set( T t ) {
        if ( set ) {
            if ( val.equals( t )) {
                return;
            }
            
            throw new IllegalStateException("can't set Once twice");
        }

        set = true;
        val = t;
    }

    public boolean isSet() {
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Once)) return false;

        Once once = (Once) o;

        if (val != null ? !val.equals(once.val) : once.val != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }

    public synchronized void setIf( Once<T> t ) {

        if ( !t.isSet() ) {
            return;
        }

        if ( set ) {
            if ( val.equals( t.val )) {
                return;
            }
            throw new IllegalStateException("can't set Once twice");
        }

        set = true;
        val = t.val;
    }

}
