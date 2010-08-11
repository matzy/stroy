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
        Stjx stjx = new Stjx( "Artifact" );

        stjx.struct( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                optional().string( "version" ).
                string( "scope" );

        stjx.struct( "Author" ).
                string("name").
                optional().string( "email" );

        stjx.struct( "Module" ).string("kind");
        stjx.struct( "External" ).string( "ext");
        stjx.struct( "Licence" ).string( "name");
        stjx.struct( "Language" ).string( "name");
        stjx.struct( "Java" ).
                string( "min").
                optional().string( "max");
        stjx.struct( "Address" ).string( "page").optional().string( "shrt" );
        stjx.struct( "Imports").list( "list" ).of("Import");
        stjx.struct( "References").list( "list" ).of("Artifact");

        stjx.struct( "Import").string("path");

        stjx.struct( "Artifact" ).list( "depends" ).of( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "version" ).
                string( "licence" ).
                optional().string( "email" ).
                list( "authors" ).of( "Author" ).
                list( "contributors" ).of( "Author" ).
                or( "Application" ).with( "Module", "External" ).
                optional().complex( "Address" ).
                list( "languages").of( "Language" ).
                optional().complex( "Java").
                or( "refs" ).with( "Imports", "References" );

        stjx.generate( "/Users/stephan/Documents/prs/stroy-10/modules/artig2", "org.openCage.artig.stjx" );


    }

}
