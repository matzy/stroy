package org.openCage.geni;

import org.openCage.lang.Strings;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 20, 2010
 * Time: 10:56:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class NewExpr implements Expr {
    private Typ typ;
    private List<String> args;

    public NewExpr( Typ typ, String ... args ) {
        this.typ = typ;
        this.args.addAll( Arrays.asList( args ));
    }

    public String toString() {
        return "new " + typ + "( " + Strings.join( args ) + ")";
    }
}
