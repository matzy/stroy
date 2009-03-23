package org.openCage.stroy.tree.filter;

import java.util.List;

public class NullIgnore implements Ignore {
    public boolean match( String path ) {
        return false;
    }

    public void setExtensions( List<String> extensions ) {
    }

    public void setPatterns( List<String> patterns ) {
    }

    public void setPaths( List<String> paths ) {
    }
}
