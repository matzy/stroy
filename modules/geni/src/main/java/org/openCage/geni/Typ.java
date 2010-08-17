package org.openCage.geni;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 17, 2010
 * Time: 10:06:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class Typ {
    private String name;
    private Typ of;

    public Typ(String name) {
        this.name = name;
    }

    public Typ(String name, Typ of) {
        this.name = name;
        this.of = of;
    }

    public static Typ s( String name ) {
        return new Typ( name );
    }

    public static Typ of( String name, Typ of ) {
        return new Typ( name, of );
    }

    public String toString() {
        if ( of == null ) {
            return name;
        }

        return name + "<" + of.toString() + ">";
    }

    public static Typ vod = new Typ("void");
}
