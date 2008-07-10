package org.openCage.stroy.update;

import org.openCage.util.prefs.PListSelectionString;
import org.openCage.util.prefs.PreferenceString;

import java.util.Date;


public class Interval {

    private static String key = "update.interval";
    private static String lastKey = "update.last";
    private static final long WEEK = 1000 * 60 * 60 * 24 * 7;
    private static final long MONTH = WEEK * 30;

    public String getKey() {

        PListSelectionString.getOrCreate( key , "Pref.Update.Weekly", "Pref.Update.Every", "Pref.Update.Weekly", "Pref.Update.Monthly", "Pref.Update.Never" );
        return key;
    }

    public boolean isTime() {

        PListSelectionString inter = PListSelectionString.getOrCreate( key , "Pref.Update.Weekly", "Pref.Update.Every", "Pref.Update.Weekly", "Pref.Update.Monthly", "Pref.Update.Never" );
        PreferenceString     last = PreferenceString.getOrCreate( lastKey, "" + new Date().getTime() );

        long diff = new Date().getTime() - Long.valueOf( last.get() );

        if ( inter.get().getSelection().equals( "Pref.Update.Every" ) ) {
            return true;
        } else if ( inter.get().getSelection().equals( "Pref.Update.Weekly" ) ) {
            return diff >= WEEK;
        } else if ( inter.get().getSelection().equals( "Pref.Update.Weekly" ) ) {
            return diff >= MONTH;
        }

        return false;
    }

    public void done() {
        PreferenceString     last = PreferenceString.getOrCreate( lastKey, "" + new Date().getTime() );
        last.set( "" + new Date().getTime() );
    }
}
