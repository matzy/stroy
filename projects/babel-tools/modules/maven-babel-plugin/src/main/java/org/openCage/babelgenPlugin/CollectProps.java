package org.openCage.babelgenPlugin;

import org.openCage.io.fspath.FSPath;

import java.util.HashMap;
import java.util.Map;

public class CollectProps {

    private FSPath base;
    private Map<FSPath, String> props = new HashMap<FSPath, String>();

    public CollectProps( FSPath base ) {
        this.base = base;
        collect( base, "" );
    }

    private void collect( FSPath dir, String pkg ) {

        for ( String name : dir.toFile().list() ) {

            FSPath child = dir.add(name);

            if ( child.toFile().isDirectory() ) {
                collect( child, pkg.isEmpty() ? name : (pkg + "." + name ));
            } else if (name.endsWith("_en.properties")){

                props.put(child, pkg );
            }
        }
    }

    public Map<FSPath, String> getProps() {
        return props;
    }
}
