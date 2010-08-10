package org.openCage.stjx;

import org.openCage.stjx.Stjx;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 9, 2010
 * Time: 2:13:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArtiTest2 {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "Artifact" );

        stjx.struct( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "scope" );

        stjx.struct( "Author" ).
                string("name").
                optional().string( "email" );

        stjx.struct( "Module" ).string( "mod" );
        stjx.struct( "External" ).string( "ext");
        stjx.struct( "Licence" ).string( "name");
        stjx.struct( "Language" ).string( "name");
        stjx.struct( "Java" ).
                string( "min").
                optional().string( "max");
        stjx.struct( "Address" ).string( "page").optional().string( "shrt" );

        stjx.struct( "Artifact" ).list( "depends" ).of( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "version" ).
                optional().string( "email" ).
                list( "authors" ).of( "Author" ).
                list( "contributors" ).of( "Author" ).
                or( "Application" ).with( "Module", "External" ).
                complex( "Licence" ).
                optional().complex( "Address" ).
                list( "languages").of( "Language" ).
                optional().complex( "Java").
                list( "references" ).of( "Artifact" );

        stjx.generate( "/Users/stephan/Documents/prs/stroy-10/modules/artig2", "org.openCage.artig" );


    }

}
