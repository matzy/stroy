package org.openCage.peleph.demo;

import org.openCage.peleph.Artifact;
import org.openCage.peleph.ArtifactImpl;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 05-abr-2010
 * Time: 8:18:45
 * To change this template use File | Settings | File Templates.
 */
public class Demo {

    public static void main(String[] args) {
        Artifact arti = new ArtifactImpl( "fausterize").
                mpl11().
                java6p().
                descriptionShort( "fausterize a small encrypting text editor" );
    }
}
