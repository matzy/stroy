package org.openCage.peleph;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 05-abr-2010
 * Time: 8:25:50
 * To change this template use File | Settings | File Templates.
 */
public class JavaVersion {

    private final String version;

    private JavaVersion( String version ) {
        this.version = version;
    }

    public static JavaVersion v5     = new JavaVersion("5");
    public static JavaVersion v5plus = new JavaVersion("5+");
    public static JavaVersion v6     = new JavaVersion("6");
    public static JavaVersion v6plus = new JavaVersion("6+");
    public static JavaVersion v7     = new JavaVersion("7");
    public static JavaVersion v7plus = new JavaVersion("7+");

    public String jvmVersion() {
        return "1." + version;
    }
}
