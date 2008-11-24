package org.openCage.stroy.algo.fuzzyHash;

import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.util.io.FileUtils;
import org.openCage.util.lang.V1;

import java.io.InputStream;
import java.util.Set;
import java.util.HashSet;

import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 23.11.2008
 * Time: 15:53:55
 * To change this template use File | Settings | File Templates.
 */
public class FuzzyHashGenIS implements FuzzyHashGen<InputStream>{

    private final CountChangeMetric metric;
//    private final NoiceGen noiceGen;
//    private final HashGen;

    @Inject
    public FuzzyHashGenIS( CountChangeMetric metric ) {
        this.metric = metric;
    }


    public FuzzyHash create( InputStream inputStream, String type ) {

        final Set<String> lines = new HashSet<String>();

        FileUtils.withIterator( inputStream, new V1<Iterable<String>>() {
            public void call( Iterable<String> iterable ) {
                for ( String str : iterable ) {
                    lines.add( str );
                }
            }
        } );

        return null;
//        return new SetFuzzyHash( metric, type, lines );
    }
}
