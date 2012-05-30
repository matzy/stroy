package org.openCage.app;

import org.openCage.comphy.*;
import org.openCage.comphy.Readable;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/29/12
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationInfo { //implements Readalizable {

    private final String name;
    private final String license;

    public ApplicationInfo(String name, String license) {
        this.name = name;
        this.license = license;
    }

//    @Override
//    public Readable toReadable() {
//        RMap map = new RMap();
//        map.put("name", name);
//        map.put( "license", license );
//        return map;
//    }
}
