package org.openCage.lindwurm.combined;

import org.openCage.lindwurm.content.Content;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/7/12
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProxyContent implements Content {

    private Content content;

    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getLocation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public InputStream getStream() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
