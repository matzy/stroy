package org.openCage.peleph.demo;

import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.Author;
import org.openCage.lang.artifact.PElephGen;
import org.openCage.lang.artifact.Project;
import org.openCage.ui.UIArtifact;

public class Demo {

    public static void main(String[] args) {

        Project proj = new Project( "stroy" );

//        <groupId>com.intellij</groupId>
//        <artifactId>annotations</artifactId>
//        <version>7.0.3</version>

        Author stephan = proj.author( "Stephan Pfab" ).email( "mailto:openCag@gmail.com" );
        Author misa    = proj.author( "Misa Inabe" );
        Author miguel    = proj.author( "Miguel Cuadron Marion" );


        Artifact idea_anno = proj.external("com.intellij", "annotations" ).
                    descriptionShort( "Nullable annotations (bundled with Idea CE)" ).
                    address( "http://www.jetbrains.com/index.html", "jetbrains").
                    apache2().
                    version( "7.0.3");

//                    provides( "annotations.jar");


        Artifact guice = proj.external( "com.google.inject", "guice").
                apache2().
                java6p().
                version( "2.0" );

        Artifact lang = proj.module( "openCage", "lang").
                mpl11().
                java6p().
                version( "0.1.0").
                descriptionShort( "a library with small java language level additions" );

        Artifact barti = proj.module( "openCage", "barti").
                mpl11().
                java6p().
                version( "0.0.1").
                descriptionShort( "a library with small java language level additions" );

        Artifact fausterize = proj.module( "openCage", "fausterize").
                mpl11().
                java6p().
                descriptionShort( "fausterize a small encrypting text editor" ).
                author( stephan ).
                contributer( misa ).
                email( "mailto:openCag@gmail.com" ).
                depends( lang ).
                depends( guice );


//        PElephGen gen1 = new PElephGen( proj );
//
//        System.out.println(gen1.deps());
//        System.out.println(gen1.buildxml( lang ));
//
//        PElephGen genL = new PElephGen();
//
//        genL.buildxml( new LangArtifact().getArtifact() );

        PElephGen genP = new PElephGen( new UIArtifact().getProject() );

        System.out.println( genP.deps());

        new UIArtifact().getProject().validate();

    }
}
