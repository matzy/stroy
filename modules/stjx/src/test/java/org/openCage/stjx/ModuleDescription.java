package org.openCage.stjx;

import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;

public class ModuleDescription {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "ArtifactDescription" );

        stjx.mpl( "Stephan Pfab", "openCage@gmail.com", "2006 - 2010", "stroy" ); 

        stjx.struct( "Module" ).
                complex( "Artifact" ).
                optional().complex( "App");

        stjx.struct( "App" ).
            string( "mainClass" ).
            optional().string( "icon" ).
            complex( "Download" );

        stjx.struct( "Download" ).
                string( "screenshot").
                string( "icon" ).
                string( "download");

        stjx.struct( "Project" ).
                string( "name" ).
                string( "groupId" ).
                zeroOrMore( "modules" ).complex( "ModuleRef" ).
                zeroOrMore( "externals").complex( "Artifact" ).
                zeroOrMore( "licences").complex( "Licence" ).
                zeroOrMore( "dropIns").complex( "ArtifactRef" );

        stjx.struct( "Deployed" ).
                optional().string( "icon" ).
                complex( "Artifact" ).
                zeroOrMore( "dependencies").complex( "Artifact" ).
                zeroOrMore( "licences" ).complex( "Licence" );

        stjx.or( "Kind" ).with( "Module", "Project", "Deployed" );

        stjx.struct( "ArtifactDescription" ).
                string( "version" ).
                complex( "Kind" );



        stjx.struct( "ModuleRef" ).
//                STRING( "groupId" ).
                string( "name" );

        stjx.enm( "scope", "compile", "test", "knowhow" );

        stjx.struct( "ArtifactRef" ).
                optional().string( "groupId" ).
                string( "name" ).
                optional().string( "version" ). // TODO ?
                optional().enm( "scope" );

        stjx.struct( "Author" ).
                string("name").
                optional().string( "email" );

        stjx.struct( "LicenceRef" ).string( "name" );

        stjx.struct( "Licence" ).
                string( "name" ).
                string( "version" ).
                complex( "Address" ).
                zeroOrMore( "positives" ).complex( "LicenceRef" ).
                zeroOrMore( "negatives" ).complex( "LicenceRef" );
        stjx.struct( "Language" ).string( "name");
        stjx.struct( "Java" ).
                string( "min").
                optional().string( "max");
        stjx.struct( "Address" ).string( "page").optional().string( "shrt" );
        //stjx.struct( "References").zeroOrMore( "references" ).complex( "Artifact");

        stjx.struct( "DropInFor" ).complex("ArtifactRef");

        stjx.struct( "Artifact" ).zeroOrMore( "depends" ).complex( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "version" ).
                string( "licence" ).
                string( "description" ).
                optional().string( "support" ).
                zeroOrMore( "authors" ).complex( "Author" ).
                zeroOrMore( "contributors" ).complex( "Author" ).
                //or( "Application" ).with( "Module", "External" ).
                optional().complex( "Address" ).
                zeroOrMore( "languages" ).complex( "Language" ).
                optional().complex( "Java").
                zeroOrMore( "refs").complex( "ArtifactRef" ).
                optional().complex( "DropInFor" ).
                optional().multiLine( "FullDescription");

        System.out.println( getProjectBase( ModuleDescription.class ) );

        stjx.generate( getProjectBase( ModuleDescription.class ).add( "modules", "artig-lib").toString(), "org.openCage.artig.stjx" );


    }

    public static FSPath getProjectBase( Class clazz ) {
        FSPath path = FSPathBuilder.getPath( clazz.getResource("."));


        while ( !path.getFileName().equals( "classes" ) &&
                !path.getFileName().equals( "out" ) &&
                !path.getFileName().equals( "modules" )) {
            path = path.parent();
        }


        return path.parent();
    }



}
