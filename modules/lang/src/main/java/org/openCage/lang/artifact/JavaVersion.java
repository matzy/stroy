package org.openCage.lang.artifact;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 05-abr-2010
 * Time: 8:25:50
 * To change this template use File | Settings | File Templates.
 */
public class JavaVersion {


    private int verNum;
    private boolean plus;

    private JavaVersion( int num, boolean plus ) {
        verNum = num;
        this.plus = plus;
    }

    public static JavaVersion v5     = new JavaVersion( 5, false );
    public static JavaVersion v5plus = new JavaVersion( 5, true );
    public static JavaVersion v6     = new JavaVersion( 6, false );
    public static JavaVersion v6plus = new JavaVersion( 6, true);
    public static JavaVersion v7     = new JavaVersion( 7, false);
    public static JavaVersion v7plus = new JavaVersion( 7, true );

    public String jvmVersion() {
        return "1." + verNum;
    }

    /** TODO ugh */
    public boolean is6() {
        return verNum == 6;
    }

    @Override
    public String toString() {
        return "" + verNum + ((plus) ? "+" : "");
    }
}
