package org.openCage.app;

import com.google.inject.name.Named;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/9/12
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Change {

    private final String name;
    private final String comment;
    private final List<String> tags;
    private final List<String> reporters;

    public Change( @Named("name") String name, @Named("comment") String comment, @Named("tags") List<String> tags, @Named("reporters") List<String> reporters) {
        this.name = name;
        this.comment = comment;
        this.tags = tags;
        this.reporters = reporters;
    }
}
