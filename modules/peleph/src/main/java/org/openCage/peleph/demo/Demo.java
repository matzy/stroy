package org.openCage.peleph.demo;

import org.openCage.peleph.Artifact;
import org.openCage.peleph.PElephGen;
import org.openCage.peleph.Project;

public class Demo {

    public static void main(String[] args) {

        Project proj = new Project( "stroy" );

//        <groupId>com.intellij</groupId>
//        <artifactId>annotations</artifactId>
//        <version>7.0.3</version>

        Artifact idea_anno = proj.external("com.intellij", "annotations" ).
                    descriptionShort( "Nullable annotations (bundled with Idea CE)" ).
                    address( "http://www.jetbrains.com/index.html", "jetbrains").
                    apache2();
//                    provides( "annotations.jar");


        Artifact guice = proj.external( "com.google.inject", "guice").
                apache2().
                java6p().
                version( "2.0" );

        Artifact lang = proj.module( "openCage", "lang").
                mpl11().
                java6p().
                descriptionShort( "a library with small java language level additions" );

        Artifact fausterize = proj.module( "openCage", "fausterize").
                mpl11().
                java6p().
                descriptionShort( "fausterize a small encrypting text editor" ).
                depends( lang ).
                depends( guice );


        PElephGen gen1 = new PElephGen( proj );

        System.out.println(gen1.deps());
    }
}
