package org.openCage.stroy.tree.zip;

import org.openCage.stroy.tree.Fiel;
import org.openCage.util.string.Strings;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;

import com.twmacinta.util.MD5;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 09.11.2008
 * Time: 17:07:39
 * To change this template use File | Settings | File Templates.
 */
public class ZipFiel implements Fiel {
    private String rootPath;
    private ZipEntry zipEntry;
    private String checkSum;

    public ZipFiel( String rootPath, ZipEntry zipEntry ) {
        this.rootPath = rootPath;
        this.zipEntry = zipEntry;
    }

    public String getChecksum() {
        if ( checkSum == null ) {
            try {
                ZipFile zf = new ZipFile( rootPath );
                InputStream is = zf.getInputStream( zipEntry );
                checkSum = Strings.asHex( MD5.getHash( is ));
                zf.close();
            } catch( IOException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return checkSum;
    }
}
