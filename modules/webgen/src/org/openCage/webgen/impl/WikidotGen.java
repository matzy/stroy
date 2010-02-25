package org.openCage.webgen.impl;

import org.openCage.webgen.protocol.WebGen;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 19, 2010
 * Time: 9:37:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class WikidotGen implements WebGen {
    @Override
    public String anchor(String name) {
        return "[[# " + normalize( name ) + "]]";
    }

    @Override
    public String link(String name, String text) {
        return "[#" + normalize( name ) + " " + text + "]";
    }

    @Override
    public String link(String page, String name, String text) {
        return "["+ page +"#" + normalize( name ) + " " + text + "]";
    }

    @Override
    public String normalize(String ref) {
        return ref.replace( '.', '-').replace( ' ', '-');
    }

    @Override
    public String externalLink(String ext, String text) {
        return "[" +  ext + " " + text + "]";
    }

    @Override
    public String title(int level, String text) {
        return "++++++".substring(0,level) +   " " + text;
    }
}
