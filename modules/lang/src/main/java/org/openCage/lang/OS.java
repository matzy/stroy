package org.openCage.lang;


// TODO
public class OS {

    private static String OS_NAME = System.getProperty( "os.name" );

    public static void main(String[] args) {
        System.out.println( System.getProperty("os.name"));
    }

    private OS() {
        throw new IllegalStateException( "utility method" );
    }

    public static boolean isWindows() {
        return OS_NAME.startsWith("Windows");
    }

    public static boolean isLinux() {
        return false;
    }

    public static boolean isOSX() {
        return OS_NAME.equals( "Mac OS X" );
    }

    public static boolean isUnix() {
        return !isWindows();  // TODO
    }


}
