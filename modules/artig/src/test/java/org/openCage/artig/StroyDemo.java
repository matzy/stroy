package org.openCage.artig;

import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.artifact.ArtifactProvider;

public class StroyDemo {

    public static void main(String[] args) {

        System.out.println(FSPathBuilder.getPath( StroyDemo.class.getResource(".")));
        FSPath stroyRoot =
                //FSPathBuilder.getPath( "/Users/stephan/tmp/dd" );
                FSPathBuilder.getPath( StroyDemo.class.getResource(".") ).parent(7);        // TODO check modules ... 
        System.out.println( stroyRoot );

////        MavenGen gen = new MavenGen( new ArtigArtifact().getProject() );
//        MavenGen gen = new MavenGen( new SipArtifact().getProject() );
//        //System.out.println( gen.getModulePom( new ArtigArtifact().getArtifact()) );
//        gen.generate( stroyRoot );
//
//
//        PElephGen pgen = new PElephGen( new FausterizeArtifact().getProject() );
//
//        pgen.generate( stroyRoot );

        
//        QuineGen qgen = new QuineGen( new SipArtifact().getProject());
//
//        System.out.println( qgen.gen( new SipArtifact()));


//        ArtigArtifact artig = new ArtigArtifact();
//        artig.getProject().get( "com.google.inject", "guice" ).setSubstitute( artig.getProject().get( "openCage", "openCage-sip") );
//        MavenGen gen = new MavenGen( artig.getProject() );
//        gen.generate( stroyRoot );

        ArtifactProvider stroy = new StroyArtifact();

//        stroy.getProject().showDeps();
//        System.exit(0);

//        stroy.getProject().get( "com.google.inject", "guice" ).
//                setSubstitute( stroy.getProject().get( "openCage", "openCage-osashosa") );
        MavenGen gen = new MavenGen( stroy.getProject() );
        gen.generate( stroyRoot );

        PElephGen pgen = new PElephGen( stroy.getProject() );
        pgen.generate( stroyRoot );


    }
}
//
//<?xml version="1.0" encoding="UTF-8"?>
//<project name="fausterize" default="default" basedir=".">
//
//    <property name = "el.version"  value="0.9.0" />
//
//    <property name = "isApp" value="true"/>
//
//    <property name = "buildnumber"     value="443"/>
//    <property name = "app.main"        value="org.openCage.gpad.UIMain"/>
//    <property name = "app.name"        value="fausterize"/>
//    <property name = "app.signature"   value="faust"/>
//    <property name = "app.bundleid"    value="org.openCage.fausterize"/>
//    <property name = "app.helpbook"    value="HelpBook"/>
//    <property name = "app.icon.small.png" value="org/openCage/gpad/resources/fausterize-small.png"/>
//    <property name="app.description.short" value="fausterize a small encrypting text editor" />
//    <property name="app.description.full" value="fausterize a small encrypting text editor" />
//    <property name="readme" value="org/openCage/gpad/resources/README.txt"/>
//
//    <property name="el.localization" value="English,German" />
//
//    <property name = "el.src"        value="src/main/java" />
//    <property name = "el.srcres"     value="src/main/resources" />
//
//
//
//
//    <import file="../../common.xml"/>
//    <import file="../../build-resources/build-common.xml"/>
//</project>
