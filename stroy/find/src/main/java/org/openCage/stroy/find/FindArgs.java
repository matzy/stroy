package org.openCage.stroy.find;

/**
 * Created by IntelliJ IDEA.
 * User: mstoremuenchen
 * Date: May 9, 2009
 * Time: 8:47:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindArgs {

    private final String what;
    private final String where;


    public FindArgs( String what, String where ) {
        this.what = what;
        this.where = where;
    }

    public String getWhat() {
        return what;
    }

    public String getWhere() {
        return where;
    }
}
