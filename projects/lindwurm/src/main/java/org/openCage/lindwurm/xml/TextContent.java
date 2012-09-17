package org.openCage.lindwurm.xml;

import org.openCage.lindwurm.content.Content;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/17/12
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextContent implements Content {
    private String name;
    private String content;

    public TextContent(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

//    @Override
//    public String getChecksum() {
//        return new Sha1().getChecksum( getStream() );
//
//    }
//
//    @Override
//    public FuzzyHash getFuzzyHash() {
//        return Null.of(FuzzyHash.class); // TODO
//    }

    @Override
    public String getType() {
        return "txt";
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public InputStream getStream() {
        return new ByteArrayInputStream( content.getBytes() );
    }
}
