package org.openCage.webgen.clazz;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 17, 2010
 * Time: 12:26:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Other extends Ref{
    public Other(String name) {
        super(name);
    }
    
    @Override
    public Ref address(String address, String shortAdd) {
        return super.address(address, shortAdd);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other apache2() {
        return (Other)super.apache2();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other cpl() {
        return (Other)super.cpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other lgpl() {
        return (Other)super.lgpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other gpl2() {
        return (Other)super.gpl2();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other bsd() {
        return (Other)super.bsd();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other mpl() {
        return (Other)super.mpl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other mit() {
        return (Other)super.mit();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other description(String text) {
        return (Other)super.description(text);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other version(String version) {
        return (Other)super.version(version);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Other provides(String lib) {
        return (Other)super.provides(lib);    //To change body of overridden methods use File | Settings | File Templates.
    }
    
}
