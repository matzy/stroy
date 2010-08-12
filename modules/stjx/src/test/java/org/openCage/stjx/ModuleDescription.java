package org.openCage.stjx;

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


        stjx.struct( "TopLevel" ).
                list( "modules" ).of( "ModuleRef" ).
                list( "externals" ).of( "Artifact" ).
                list( "licences").of( "Licence" );

        stjx.struct( "Complete" ).
                complex( "Artifact" ).
                list( "externals" ).of( "Artifact" ).
                list( "licences").of( "Licence" );

        stjx.struct( "ArtifactDescription" ).or( "Kind" ).with( "Module", "TopLevel", "Complete" );



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
        stjx.struct( "References").list( "list" ).of("Artifact");

        stjx.struct( "Artifact" ).list( "depends" ).of( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "version" ).
                string( "licence" ).
                optional().string( "email" ).
                list( "authors" ).of( "Author" ).
                list( "contributors" ).of( "Author" ).
                //or( "Application" ).with( "Module", "External" ).
                optional().complex( "Address" ).
                list( "languages").of( "Language" ).
                optional().complex( "Java").
                list( "refs" ).of( "ArtifactRef" );

        stjx.generate( "/Users/stephan/Documents/prs/stroy-10/modules/artig2", "org.openCage.artig.stjx" );


    }

}
