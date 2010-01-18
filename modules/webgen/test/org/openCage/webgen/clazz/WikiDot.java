package org.openCage.webgen.clazz;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 29, 2009
 * Time: 3:24:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class WikiDot {

    public static void main(String[] args) {
        GenWikiRefs gen = new GenWikiRefs( new LinkCollection().getRefs());

        gen.gen();
    }

}
