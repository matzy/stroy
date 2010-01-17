package org.openCage.webgen.clazz;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 17, 2010
 * Time: 12:25:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Module extends Ref {
    public Module(String name ) {
        super(name);
    }

    @Override
    public Ref address(String address, String shortAdd) {
        return super.address(address, shortAdd);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module apache2() {
        return (Module)super.apache2();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module cpl() {
        return (Module)super.cpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module lgpl() {
        return (Module)super.lgpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module gpl2() {
        return (Module)super.gpl2();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module bsd() {
        return (Module)super.bsd();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module mpl() {
        return (Module)super.mpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module mit() {
        return (Module)super.mit();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module description(String text) {
        return (Module)super.description(text);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module version(String version) {
        return (Module)super.version(version);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Module provides(String lib) {
        return (Module)super.provides(lib);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private boolean delivers() {
        return true;
    }

    public void printAnt() {
        if ( delivers() ) {
            System.out.println("<!-- ================================================================== -->");
            printTargetAndDepends();

            System.out.println("    <ant dir=\"${dependencies.basedir}/modules/" + getModuleName() + "\" inheritAll=\"false\"/>");

            System.out.println("</target>");
        }
    }


    public String getModuleName() {
        return getName().substring( "depend.".length());
    }
}
