package org.openCage.stroy.find;

import org.apache.commons.cli.*;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedGen;
import org.openCage.stroy.tree.NoedGenSelectorCenter;
import org.openCage.stroy.tree.singlefile.SingleFileGen;
import org.openCage.stroy.tree.iter.DepthFirstIterator;
import org.openCage.lang.Maybe;

import java.io.File;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FindClt {


    public static void main( String[] args ) {

        Args arguments = new Args( args );
        if ( !arguments.isOk() ) {
            return;
        }

        FindClt find = new FindClt();

        find.run( arguments.getWhat(), arguments.getWhere() );

//        TreeFactory tf = null;
//
//        Noed where = tf.create( arguments.getWhere().getAbsolutePath(), false ).build( arguments.getWhere().getAbsolutePath());
    }

    void run( String what, String where ) {
        Maybe<? extends NoedGen> ngen = new NoedGenSelectorCenter().get( where, false );

        if ( !ngen.is ) {
            throw new IllegalArgumentException( "huh" );
        }

        Noed root = ngen.o.build( where );

        for ( Noed noed : new DepthFirstIterator(root)) {
            System.out.println( noed );
        }

        Noed tomatch = new SingleFileGen().build( what );
    }



}
