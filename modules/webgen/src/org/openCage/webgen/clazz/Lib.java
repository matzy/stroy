package org.openCage.webgen.clazz;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 17, 2010
 * Time: 12:42:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Lib extends Ref{
    public Lib(String name) {
        super(name );
        location( "production" );
    }


    @Override
    public Ref address(String address, String shortAdd) {
        return super.address(address, shortAdd);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib apache2() {
        return (Lib)super.apache2();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib cpl() {
        return (Lib)super.cpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib lgpl() {
        return (Lib)super.lgpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib gpl2() {
        return (Lib)super.gpl2();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib bsd() {
        return (Lib)super.bsd();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib mpl() {
        return (Lib)super.mpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib mit() {
        return (Lib)super.mit();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib description(String text) {
        return (Lib)super.description(text);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib version(String version) {
        return (Lib)super.version(version);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib provides(String lib) {
        return (Lib)super.provides(lib);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Lib location(String loc) {
        return (Lib)super.location(loc);
    }


    private boolean delivers() {
        return true;
    }

    public void printAnt() {
        System.out.println("<!-- ================================================================== -->");
        printTargetAndDepends();

        if ( getProvidedLibs().isEmpty() ) {
            throw new IllegalStateException("lib does not produce libs: " + getName());
        }

        for ( String lib : getProvidedLibs()){
            System.out.println("   <copy todir=\"${dependencies.basedir}/build/libs\" file=\"${dependencies.basedir}/external/" +
                getLocation() + "/" +
                lib + "\"/>");
        }

        System.out.println("</target>");
    }


}
