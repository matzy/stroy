package org.openCage.babel;

import org.openCage.lang.ListenerControl;
import org.openCage.lang.Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LocalePreference {

    private ReentrantReadWriteLock  lock      = new ReentrantReadWriteLock( );
    private Listeners<List<Locale>> listeners = new Listeners<List<Locale>>();
    private List<Locale>            locales   = new ArrayList<Locale>();

    public LocalePreference() {
        locales.add( Locale.getDefault());
        locales.add( Locale.ENGLISH );
    }

    public List<Locale> getLocales() {
        try {
            lock.readLock().lock();
            return locales;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setLocales(List<Locale> locales) {
        try {
            lock.writeLock().lock();
            this.locales = locales;

            listeners.shout( locales );
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void setLocales(Locale ... locales ) {
        setLocales( Arrays.asList( locales ));
    }

    public ListenerControl<List<Locale>> getListenerControl() {
        return listeners.get();
    }
}
