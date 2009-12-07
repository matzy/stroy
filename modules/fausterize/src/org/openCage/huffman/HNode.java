package org.openCage.huffman;

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
public class HNode {
    public final int character;
    public final HNode left;
    public final HNode right;
    public final int weight;

    public HNode( int ch, HNode left, HNode right, int weight ) {
        this.character = ch;
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    @Override
    public String toString() {
        if ( character > -127 && character < 127 ) {
            return "(" + (char)character + " " + left + " " + right + " " + weight + ")";
        }
        return "(" + character + " " + left + " " + right + " " + weight + ")";
    }
}
