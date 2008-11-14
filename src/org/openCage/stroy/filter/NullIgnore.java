package org.openCage.stroy.filter;

import java.util.List;

public class NullIgnore implements Ignore {
    public boolean match( String path ) {
        return false;
    }

    public void setExtensions( List<String> extensions ) {
        throw new NoSuchMethodError( "NullIgnore does not allow modifications" );
    }

    public void setPatterns( List<String> patterns ) {
        throw new NoSuchMethodError( "NullIgnore does not allow modifications" );
    }

    public void setPaths( List<String> paths ) {
        throw new NoSuchMethodError( "NullIgnore does not allow modifications" );
    }
}
