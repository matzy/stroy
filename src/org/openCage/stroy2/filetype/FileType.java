package org.openCage.stroy2.filetype;

import eu.medsea.util.MimeUtil;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Feb 10, 2009
 * Time: 4:15:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileType {

    public static void main(String[] args) {

        if ( args.length == 0 ) {
            System.err.println("Usage: file <file>");
            System.err.println("       Determines the type of the file");
            System.err.println("       e.g. file duda.txt");
            System.err.println("            text/plain");
            System.exit(1);
        }

        System.out.println( MimeUtil.getExtensionMimeTypes( args[0]));
        System.out.println( MimeUtil.getMagicMimeType( args[0]));


//        System.out.println( MimeUtil.getExtensionMimeTypes( new File("Sys.jar")));
//        System.out.println( MimeUtil.getExtensionMimeTypes( new File("C:\\prs\\stroy\\external\\production\\Sys.jar")));
//        System.out.println( MimeUtil.getMagicMimeType( new File("C:\\prs\\stroy\\external\\production\\Sys.jar")));
//
//        System.out.println( MimeUtil.getMagicMimeType( new File("Readme")));
//        System.out.println( MimeUtil.getMagicMimeType( new File("C:\\home\\download\\MimeUtil\\Readme.txt")));
//
//        System.out.println( MimeUtil.getMagicMimeType( new File("C:\\prs\\stroy\\src\\org\\openCage\\stroy\\locale\\native\\messages_ja.properties")));
//        System.out.println( MimeUtil.getMagicMimeType( new File("C:\\prs\\stroy\\src\\org\\openCage\\stroy\\locale\\native\\test_ja.properties")));
//
//        System.out.println( MimeUtil.getMagicMimeType( new File("C:\\Documents and Settings\\spfab\\Desktop\\Apress.Google.Guice.Agile.Lightweight.Dependency.Injection.Framework.Apr.2008.pdf")));
//
//        System.out.println( MimeUtil.getExtensionMimeTypes( new File("C:\\prs\\stroy\\src\\org\\openCage\\stroy\\locale\\Message.java")));
//        System.out.println( MimeUtil.getMagicMimeType( new File("C:\\prs\\stroy\\src\\org\\openCage\\stroy\\locale\\Message.java")));


    }
}
