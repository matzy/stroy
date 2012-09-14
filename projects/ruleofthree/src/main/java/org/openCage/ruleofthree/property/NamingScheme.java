package org.openCage.ruleofthree.property;

import org.openCage.kleinod.collection.ImmuList;
import org.openCage.ruleofthree.ThreeKey;

import java.io.File;

/**
* Created with IntelliJ IDEA.
* User: stephan
* Date: 7/23/12
* Time: 9:15 AM
* To change this template use File | Settings | File Templates.
*/
public interface NamingScheme {
    File getPath(File backingFile, String key);

    String getPropName();

    String getFile(File backingFile, ImmuList<String> names);

    ThreeKey getKey( ImmuList<String> names );
}
