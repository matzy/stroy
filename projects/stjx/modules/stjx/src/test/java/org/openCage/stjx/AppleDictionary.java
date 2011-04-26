package org.openCage.stjx;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 11/14/10
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppleDictionary {

    public static void main(String[] args) {

        Stjx ad = new Stjx( "Proj" );

        ad.struct( "Proj").multiLine( "ProjName" ).
                          zeroOrMore().complex("File");

        ad.struct( "File" ).multiLine( "Filepath").
                            zeroOrMore().complex("TextItem");


        ad.struct( "TextItem").multiLine("Description").
                               multiLine("Position").
                               complex( "TranslationSet" );

        ad.struct( "TranslationSet").complex("base");

        ad.struct( "base").string("loc");
    }
}
