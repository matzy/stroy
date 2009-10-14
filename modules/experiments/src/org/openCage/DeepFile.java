/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage;

import java.io.File;

/**
 *
 * @author spfab
 */
public class DeepFile {

    public static void main(String[] args) {
        File file = new File( "\\\\127.0.0.1\\c$" );
        File file2 = new File( "\\\\127.0.0.1\\c$\\home\\accurev\\CICOTeam\\muc-server\\muc-external\\AAFExpander\\aafexpander-jardist\\src\\main\\resources\\com\\avid\\interplay\\aafexpander\\aafexpander-jardist\\src\\main\\resources\\com\\avid\\interplay\\aafexpander\\aafexpander-jardist\\src\\main\\resources\\com\\avid\\interplay\\aafexpander\\AVX2_Plug-Ins\\Codecs" );
        System.out.println( "" + file.exists() + "   "+ file2.exists());

        for ( File ff : file2.listFiles() ) {
            System.out.println( ff.getAbsolutePath());
        }

//       File foo = new File( "\\\\\\127.0.0.1\\c$\\home\\accurev\\CICOTeam\\muc-server\\muc-external\\AAFExpander\\aafexpander-jardist\\src");
//
//       while ( foo.exists() ) {
//           System.out.println( "" + foo.getAbsolutePath().length() + " " + foo.getAbsolutePath());
//           for ( File next : foo.listFiles() ) {
//               if ( next.isDirectory() ) {
//                   foo = next;
//                   break;
//               }
//           }
//       }

    }

}
