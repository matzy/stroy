package org.openCage.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 25, 2010
 * Time: 9:01:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class IOUtils {

    public static void closeQuietly( InputStream is ) {
        if ( is != null ) {
            try {
                is.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void closeQuietly(FileWriter writer) {
        if ( writer != null ) {
            try {
                writer.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }
}
