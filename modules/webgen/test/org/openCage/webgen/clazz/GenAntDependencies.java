package org.openCage.webgen.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class GenAntDependencies {

    List<Ref> refs = new LinkCollection().getRefs();

    public static void main(String[] args) {
        new GenAntDependencies().print();
//        new GenAntDependencies().printDependentLinks( Arrays.asList( "designgridlayout", "guice", "mac-widgets", "depend.lang", "depend.io", "depend.application", "depend.ui" ));
    }

    private void print() {

        System.out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<project name=\"dependencies\" default=\"depend.fausterize\">\n" +
                "\n" +
                "    <dirname property=\"dependencies.basedir\" file=\"${ant.file.dependencies}\"/>\n");

        for ( Ref ref : refs ) {
            if ( ref instanceof Module ) {
                ref.printAnt();
            }
        }

        System.out.println("\n" +
                "    <!-- ================================================================== -->\n" +
                "    <!--     external library dependencies       --> \n" +
                "    <!-- ================================================================== -->    \n" +
                "");


        for ( Ref ref : refs ) {
            if ( ref instanceof Lib ) {
//                if ( !ref.hasLibrary() ) {
//                    throw new IllegalStateException("runtime dependency without lib:" + ref.getProg() );
//                }
                ref.printAnt();
            }
        }

        System.out.println("</project>");                                                  


    }

    private void printDependentLinks( List<String> lnks ) {
//        Set<Reference> refLinks = new HashSet<Reference>();
//        Set<String>    links    = new HashSet<String>( lnks );
//
//        while( true ) {
//            for ( String ln : links ) {
//                refLinks.add( find( refs, ln ));
//            }
//
//            int lncount = links.size();
//
//            for ( Reference ref : refLinks ) {
//                links.addAll( ref.getDependencies() );
//            }
//
//            if ( lncount == links.size() ) {
//                break;
//            }
//        }
//
//        List<Reference> sortedRefs = new ArrayList<Reference>();
//        for ( Reference ref : refLinks ) {
//            if ( !ref.isInternal() ) {
//                sortedRefs.add(ref);
//            }
//        }
//
//        Collections.sort( sortedRefs, new Comparator<Reference>() {
//            @Override
//            public int compare(Reference reference, Reference reference1) {
//                return reference.getProg().compareTo(reference1.getProg());
//            }
//        });
//
//        for ( Reference ref : sortedRefs ) {
//            ref.printHtmlLink();
//        }
//
    }

    private Reference find( List<Reference> refs, String name ) {
        for ( Reference ref : refs ) {
            if ( ref.getProg().equals( name )) {
                return ref;
            }
        }

        throw new IllegalArgumentException("wrong name " + name);
    }


}
