package org.openCage.comphy;

import org.openCage.lang.Listeners;

import static org.openCage.comphy.RU.r;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileType implements Property {

    private String description;
    private String diff;

    private Listeners listeners = new Listeners();

    public FileType(String descr, String diff) {
        this.description = descr;
        this.diff = diff;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
        listeners.shout(null);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        listeners.shout(null);
    }



    @Override
    public void addObserver(Observer ob) {
        listeners.add(ob);
    }

    @Override
    public Readable toReadable() {
        RMap map = new RMap();
        map.put( "description", r(description) );
        map.put( "diff", r(diff ));
        return map;
    }
}
