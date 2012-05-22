package org.openCage.comphy;


import org.openCage.lang.Listeners;

import static org.openCage.comphy.RU.r;

public class StringProperty implements Property {

    private Listeners listeners = new Listeners();
    private String str;

    public String get() {
        return str;
    }

    public void set(String str) {
        this.str = str;
        listeners.shout(null);
    }


    public StringProperty(String str) {
        this.str = str;
    }

    @Override
    public void addObserver(Observer ob) {
        listeners.add(ob);
    }

    @Override
    public Readable toReadable() {
        return r(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringProperty that = (StringProperty) o;

        if (str != null ? !str.equals(that.str) : that.str != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return str != null ? str.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Property: " + str;
    }
}
