package org.openCage.webgen.clazz;

import java.util.*;

/**
 * build the list of used depenencies (transitive closure)
 */
public class UsingUsed {

    public Collection<Ref> transitiveClosure( List<Ref> all, String app ) {
        return transitiveClosure( all, getRef(all, app ));
    }


    /**
     * build the list of transitive used references
     * @param all all possible references
     * @param app the starting point
     * @return
     */
    public Collection<Ref> transitiveClosure( List<Ref> all, Ref app ) {

        Set<Ref> deps = new HashSet<Ref>();
        deps.addAll( getRefs( all, app.getRuntime()));
        deps.addAll( getRefs( all, app.getOther() ));
        deps.addAll( getRefs( all, app.getTest() ));


        while ( true ) {
            int count = deps.size();

            Set<Ref> newDeps = new HashSet<Ref>( deps );

            for ( Ref ref : deps ) {
                newDeps.addAll( transitiveClosure( all, ref ));
            }


            deps = newDeps;

            if ( count >= deps.size() ) {
                return deps;
            }
        }

    }

    public Ref getRef( List<Ref> all, String name ) {
        for ( Ref ref: all ) {
            if ( ref.getName().equals( name )) {
                return ref;
            }
        }

        throw new IllegalArgumentException( "unknown ref: " + name );
    }

    public List<Ref> getRefs( List<Ref> all, List<String> names ) {
        List<Ref> ret = new ArrayList<Ref>();

        for ( String name : names ) {
            ret.add( getRef(all, name));
        }

        return ret;
    }

}
