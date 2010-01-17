package org.openCage.webgen.clazz;

import com.sun.tools.javac.main.JavaCompiler;
import org.openCage.lang.clazz.Count;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 17, 2010
 * Time: 10:27:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Ref {

    private final String name; // was prog

    private String address;
    private String shortAddress;

    private String licence;
    private String licenceShort;

    private String version;
    private String descr;

    // wants DAG of refs
    private List<String> runtime = new ArrayList<String>();
    private List<String> build   = new ArrayList<String>();
    private List<String> test    = new ArrayList<String>();
    private List<String> other = new ArrayList<String>();

    private List<String> libs = new ArrayList<String>();
    private String location = "";

    public Ref( String name ) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Ref address( String address, String shortAdd ) {
        this.address = address;
        this.shortAddress = shortAdd;
        return this;
    }

    public Ref apache2() {
        licence = "http://www.apache.org/licenses/LICENSE-2.0";
        licenceShort = "Apache 2.0";
        return this;
    }

    public Ref cpl() {
        licence = "http://junit.sourceforge.net/cpl-v10.html";
        licenceShort = "CPL 1.0";
        return this;
    }

    public Ref lgpl() {
        licence = "http://www.gnu.org/copyleft/lesser.html";
        licenceShort = "LGPL";
        return this;
    }

    public Ref gpl2() {
        licence = "http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt";
        licenceShort = "GPL2";
        return this;
    }

    public Ref bsd() {
        licence = "http://www.opensource.org/licenses/bsd-license.php";
        licenceShort = "BSD";
        return this;
    }
    public Ref mpl() {
        licence = "http://www.mozilla.org/MPL/MPL-1.1.html";
        licenceShort = "MPL1.1";
        return this;
    }

    public Ref mit() {
        licence = "http://www.opensource.org/licenses/mit-license.php";
        licenceShort = "MIT";
        return this;
    }

    public Ref licence( String add, String shortAdd ) {
        licence = add;
        licenceShort = shortAdd;
        return this;
    }

    public Ref description( String text ) {
        descr = text;
        return this;
    }

    public Ref version(String version) {
        this.version = version;
        return this;
    }

    public Ref provides(String lib) {
        libs.add( lib );
        return this;
    }


    public Ref depends( String dep ) {
        runtime.add( dep );
        return this;
    }

    public Ref build_depends(String dep) {
        build.add( dep );
        return this;
    }

    public Ref other_depends(String dep) {
        other.add( dep );
        return this;
    }

    public void printAnt() {
//        if ( delivers() ) {
//            System.out.println("<!-- ================================================================== -->");
//            printTargetAndDepends();
//
//            if ( isRuntime() ) {
//                System.out.println("   <copy todir=\"${dependencies.basedir}/build/libs\" file=\"${dependencies.basedir}/external/production/" +
//                    libName + "\"/>");
//            } else if ( isInternal() ) {
//
//                System.out.println("    <ant dir=\"${dependencies.basedir}/modules/" + getInternalName() + "\" inheritAll=\"false\"/>");
//            }
//
//            System.out.println("</target>");
//    //        <target name="jgoodies-binding">
//    //            <copy todir="${dependencies.basedir}/build/libs" file="${dependencies.basedir}/external/production/binding-2.0.6.jar"/>
//    //            <concat destfile="${dependencies.basedir}/build/references.html" append="yes"> <file file="${dependencies.basedir}/external/description/jgoodies/binding-short.txt"/> </concat>
//    //        </target>
//        }
    }

    protected void printTargetAndDepends() {
        System.out.print("<target name= \"" + name + "\"");
        for ( Count<String> dep : Count.count(runtime) ) {
            if ( dep.isFirst() ) {
                System.out.print("\n    depends=\"");
            }

            System.out.print(  dep.obj() );

            if ( dep.isLast()) {
                System.out.print("\"");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println( ">");
    }


    private boolean delivers() {
        return false;
    }

    public List<String> getProvidedLibs() {
        return libs;
    }


    public Ref location(String loc) {
        location = loc;
        return this;
    }

    public String getLocation() {
        return location;
    }
}
