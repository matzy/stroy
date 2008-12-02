package org.openCage.stroy.algo.checksum;

import org.openCage.stroy.algo.tree.IOState;
import org.openCage.util.string.Strings;
import org.openCage.util.logging.Log;
import com.twmacinta.util.MD5;
import com.JavaExchange.www.RandomGUID;

import java.io.File;
import java.io.IOException;

public class FileChecksum implements ChecksumCalculator {


    public String getChecksum( Object obj, IOState state ) {

        if ( state.isError() ) {
            return new RandomGUID().toString();
        }

        File file = (File)obj;

        try {
            return Strings.asHex( MD5.getHash( file ));
        } catch ( IOException e ) {
            Log.warning( "read error of file: " + file.getAbsolutePath()  );
            state.setError( e );
            return new RandomGUID().toString();
        }
    }


}
