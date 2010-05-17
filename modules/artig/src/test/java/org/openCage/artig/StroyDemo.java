package org.openCage.artig;

import org.openCage.artig.MavenGen;
import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.fspath.protocol.FSPath;
import org.openCage.gpad.FausterizeArtifact;
import org.openCage.lang.LangArtifact;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 29, 2010
 * Time: 2:28:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class StroyDemo {

    public static void main(String[] args) {

        FSPath stroyRoot =
                //FSPathBuilder.getPath( "/Users/stephan/tmp/dd" );
                FSPathBuilder.getPath( StroyDemo.class.getResource(".") ).parent(7);
        System.out.println( stroyRoot );

//        MavenGen gen = new MavenGen( new ArtigArtifact().getProject() );
//        //System.out.println( gen.getModulePom( new ArtigArtifact().getArtifact()) );
//        gen.generate( stroyRoot );


        PElephGen pgen = new PElephGen( new FausterizeArtifact().getProject() );

        pgen.generate( stroyRoot );

        
//        QuineGen qgen = new QuineGen( new LangArtifact().getProject());
//
//        System.out.println( qgen.gen( new LangArtifact()));


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
