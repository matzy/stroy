package org.openCage.stjx;

import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.stjx.Stjx;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 9, 2010
 * Time: 2:13:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModuleDescription {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "ArtifactDescription" );

        stjx.struct( "Module" ).
                complex( "Artifact" );


        stjx.struct( "Project" ).
                string( "name" ).
                string( "groupId" ).
                list( "modules" ).of( "ModuleRef" ).
                list( "externals" ).of( "Artifact" ).
                list( "licences").of( "Licence" );

        stjx.struct( "Deployed" ).
                complex( "Artifact" ).
                list( "externals" ).of( "Artifact" ).
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

        stjx.struct( "Licence" ).string( "name");  // TODO fill in
        stjx.struct( "Language" ).string( "name");
        stjx.struct( "Java" ).
                string( "min").
                optional().string( "max");
        stjx.struct( "Address" ).string( "page").optional().string( "shrt" );
        stjx.struct( "References").list( "references" ).of("Artifact");

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
                list( "refs" ).of( "ArtifactRef" );

        System.out.println( getProjectBase( ModuleDescription.class ) );

        stjx.generate( getProjectBase( ModuleDescription.class ).add( "modules", "artig-lib").toString(), "org.openCage.artig.stjx" );


    }

    public static FSPath getProjectBase( Class clazz ) {
        FSPath path = FSPathBuilder.getPath( clazz.getResource("."));


        while ( !path.getFileName().equals( "classes" ) && !path.getFileName().equals( "out" )) {
            path = path.parent();
        }


        return path.parent();
    }

}