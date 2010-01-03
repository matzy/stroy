package org.openCage.webgen.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 29, 2009
 * Time: 3:42:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenAntDependencies {

    List<Reference> refs = new LinkCollection().getRefs();

    public static void main(String[] args) {
        new GenAntDependencies().print();
//        new GenAntDependencies().printDependentLinks( Arrays.asList( "designgridlayout", "guice", "mac-widgets", "depend.lang", "depend.io", "depend.application", "depend.ui" ));
    }

    private void print() {
        for ( Reference ref : refs ) {
            if ( ref.isRuntime() ) {
                ref.printAnt();
            }
        }
    }

    private void printDependentLinks( List<String> lnks ) {
        Set<Reference> refLinks = new HashSet<Reference>();
        Set<String>    links    = new HashSet<String>( lnks );
        
        while( true ) {
            for ( String ln : links ) {
                refLinks.add( find( refs, ln ));
            }

            int lncount = links.size();

            for ( Reference ref : refLinks ) {
                links.addAll( ref.getDependencies() );
            }

            if ( lncount == links.size() ) {
                break;
            }
        }

        List<Reference> sortedRefs = new ArrayList<Reference>();
        for ( Reference ref : refLinks ) {
            if ( !ref.isInternal() ) {
                sortedRefs.add(ref);
            }
        }

        Collections.sort( sortedRefs, new Comparator<Reference>() {
            @Override
            public int compare(Reference reference, Reference reference1) {
                return reference.getProg().compareTo(reference1.getProg());
            }
        });

        for ( Reference ref : sortedRefs ) {
            ref.printHtmlLink();            
        }

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
