package org.openCage.notabug;

import org.openCage.kleinod.Strings;
import org.openCage.kleinod.collection.ImmuList;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.property.NamingScheme;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/23/12
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdToPath implements NamingScheme {

    // 012345678911234567
    // 844b481d-898f-42ee-8533-60cf90151bb8

    @Override
    public File getPath(File backingFile, String key) {

        String one = key.substring( 0, 8);
        String two = key.substring( 8 );

        return new File( backingFile.getAbsolutePath() + "/" + one + "/" + two + ".xml");
    }

    @Override
    public String getPropName() {
        return "Task";
    }

    @Override
    public String getFile(File backingFile, ImmuList<String> names) {
        return new File( backingFile.getAbsolutePath() + "/" + names.get(0) + "/" + names.get(1)).getAbsolutePath();
    }

    @Override
    public ThreeKey getKey(ImmuList<String> names) {
        return new ThreeKey( names.get(0) + Strings.withoutEnd(names.get(1), ".xml"));
    }
}
