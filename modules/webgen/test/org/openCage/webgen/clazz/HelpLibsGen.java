package org.openCage.webgen.clazz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 21-mar-2010
 * Time: 13:31:14
 * To change this template use File | Settings | File Templates.
 */
public class HelpLibsGen {
    public static void main(String[] args) {
        List<Ref> total = new LinkCollection().getRefs();

        Collection<Ref> faustDeps = new UsingUsed().transitiveClosure( total, "depend.fausterize");
        List<Ref> ordered = new ArrayList<Ref>( faustDeps );

        Collections.sort( ordered, new Comparator<Ref>() {
            @Override
            public int compare(Ref a, Ref b) {
                return a.getName().compareTo( b.getName());
            }
        });

        for ( Ref ref : ordered ) {
            if ( !(ref instanceof Module ))
            ref.htmlShort();
        }

    }
}
