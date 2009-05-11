package org.openCage.stroy.find;

import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedGen;
import org.openCage.stroy.tree.NoedGenSelectorCenter;
import org.openCage.stroy.tree.singlefile.SingleFileGen;
import org.openCage.stroy.tree.iter.DepthFirstIterator;

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

        ArgsBuilder builder = new ArgsBuilderImpl( args );

        if ( !builder.isOk() ) {
            builder.printUsage();
            return;
        }

        FindArgs fargs = builder.getArgs();

        FindClt find = new FindClt();

        find.run( fargs.getWhat(), fargs.getWhere() );

    }

    void run( String what, String where ) {
        NoedGen ngen = new NoedGenSelectorCenter().find( where, false );

        if ( ngen == null ) {
            throw new IllegalArgumentException( "huh" );
        }

        Noed root = ngen.build( where );

        for ( Noed noed : new DepthFirstIterator(root)) {
            System.out.println( noed );
        }

        Noed tomatch = new SingleFileGen().build( what );
    }



}
