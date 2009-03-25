package org.openCage.stroy.hash;

import org.openCage.lang.E1;
import org.openCage.stroy.mimetype.MimeList;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Mar 24, 2009
 * Time: 5:56:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FuzzyHashFctSelector<T> {

    public E1<FuzzyHash,? extends T> select( MimeList mimes );
}
