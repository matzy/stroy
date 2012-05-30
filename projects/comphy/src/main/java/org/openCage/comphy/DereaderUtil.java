package org.openCage.comphy;

public class DereaderUtil {

    public static <T> T deread( Dereadalizer<T> dereader, Readable readable ) {
        if ( readable instanceof RString) {
            return dereader.fromString((RString)readable);
        }

        if ( readable instanceof RMap ) {
            return dereader.fromMap( (RMap)readable);
        }

        if ( readable instanceof RList ) {
            return dereader.fromList( (RList)readable);
        }

        throw new IllegalArgumentException("unknown readable " + readable);
    }
}
