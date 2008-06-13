package org.openCage.util.lang;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * Encapsulates are a value to be set once only
 * basically a static at but defined at runtime
 */
public class Once<T> {
    private T val;

    public Once() {
    }

    public Once( T val ) {
        this.val = val;
    }

    public void set( T val ) {
        if ( this.val != null ) {
            throw new IllegalStateException( "value can only be set once. Is: " + this.val + " new would be " + val);
        }

        this.val = val;
    }

    public T get() {
        if ( val == null ) {
            throw new IllegalStateException( "value must be set by now" );
        }
        return val;
    }

    public boolean isNull() {
        return val == null;
    }


    public String toString() {
        if ( val == null ) {
            return "<not set>";
        }
        
        return val.toString();
    }
}
