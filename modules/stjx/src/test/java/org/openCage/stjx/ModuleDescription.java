package org.openCage.stjx;

import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;

public class ModuleDescription {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "ArtifactDescription" );

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
                list( "modules" ).of( "ModuleRef" ).
                list( "externals" ).of( "Artifact" ).
                list( "licences").of( "Licence" );

        stjx.struct( "Deployed" ).
                optional().string( "icon" ).
                complex( "Artifact" ).
                list( "dependencies" ).of( "Artifact" ).
                list( "licences").of( "Licence" );

        stjx.struct( "ArtifactDescription" ).
                string( "version" ).
                or( "Kind" ).with( "Module", "Project", "Deployed" );



        stjx.struct( "ModuleRef" ).
//                string( "groupId" ).
                string( "name" );

        stjx.struct( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                optional().string( "version" ). // TODO ?
                string( "scope" );

        stjx.struct( "Author" ).
                string("name").
                optional().string( "email" );

        stjx.struct( "LicenceRef" ).string( "name" );

        stjx.struct( "Licence" ).
                string( "name" ).
                string( "version" ).
                complex( "Address" ).
                list( "positives" ).of( "LicenceRef" ).
                list( "negatives" ).of( "LicenceRef" );
        stjx.struct( "Language" ).string( "name");
        stjx.struct( "Java" ).
                string( "min").
                optional().string( "max");
        stjx.struct( "Address" ).string( "page").optional().string( "shrt" );
        stjx.struct( "References").list( "references" ).of("Artifact");

        stjx.struct( "DropInFor" ).complex("ArtifactRef");

        stjx.struct( "Artifact" ).list( "depends" ).of( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "version" ).
                string( "licence" ).
                optional().string( "support" ).
                list( "authors" ).of( "Author" ).
                list( "contributors" ).of( "Author" ).
                //or( "Application" ).with( "Module", "External" ).
                optional().complex( "Address" ).
                list( "languages").of( "Language" ).
                optional().complex( "Java").
                list( "refs" ).of( "ArtifactRef" ).
                optional().complex( "DropInFor" );

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
