package org.openCage.gpad;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.lang.clazz.MRU;
import org.openCage.property.clazz.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

import java.io.File;

import static org.openCage.gpad.Constants.PROJECT_DIR;

/**
 * A persisted MRU for opened files for fausterize
 */
public class FaustMRUProperty extends AbstractPropertyProvider<MRU<String>> {

    public static final String KEY = "FaustMRU";

    @Inject
    public FaustMRUProperty( @Named("trans") PropStore store ) {
        super( store, KEY, getDefault() );

    }

    private static MRU<String> getDefault() {
        MRU<String> mru = new MRU<String>();

        String message = FSPathBuilder.getHome().add(PROJECT_DIR, "1.fst1").toString();
        new File( message ).getParentFile().mkdirs();

        // add legacy message (0.6 and before)
        if ( new File( message ).exists() ) {
            mru.use(message);
        } else {
            mru.use( FSPathBuilder.getDocuments().add( Constants.FAUSTERIZE, "1.fst1").toString());
        }

        return mru;
    }
}
