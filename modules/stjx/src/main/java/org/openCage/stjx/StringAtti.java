package org.openCage.stjx;

import org.openCage.generj.Block;
import org.openCage.generj.Call;
import org.openCage.generj.Clazz;
import org.openCage.generj.Exp;
import org.openCage.generj.IfExpr;
import org.openCage.generj.Typ;
import org.openCage.lang.Strings;

import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NameExpr.SETTER;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 8, 2010
 * Time: 2:24:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringAtti implements Atti {
    private String name;
    private boolean optional;

    public StringAtti(String name, boolean optional ) {
        this.name = name;
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public String toJava() {
        return Stjx.toJavaBeanAttribute( "String", name );
    }

    @Override public void toJavaProperty(Clazz clazz) {
        clazz.property( Typ.string, Strings.toFirstLower(name));
//        clazz.property( TYP( name ), Strings.toFirstLower(name));
    }

    @Override
    public void toFromXMLStart(Block block, String varName) {

        IfExpr ite = block.ifNotNull( CALL( DOT( NAME("attributes"), NAME( "getValue")), STR(name) ));

        ite.thn().call( DOT( NAME("elem"), SETTER(name)),
                        CALL( DOT( NAME("attributes"), NAME( "getValue")), STR(name) ));

        if( !optional ) {
            ite.els().thrw( Typ.s("IllegalArgumentException"), varName + ": attribute " + name + " is required"  );
        }

    }

    public String toSAXStart(String complexName) {
        String ret = "               String "+ name + " = attributes.getValue( \"" + name +"\" );\n" +
                "               if ( "+ name + " != null ) {\n" +
                "                  elem.set" + Strings.toFirstUpper(name ) + "( " + name + ");\n" +
                "               } \n";

        if ( !optional ) {
            ret +=                  "               else {\n                  throw new IllegalArgumentException( \"" + complexName + "\" + \" attribute " + name + " is required\" );\n" +
                    "               }\n";

        }

        return ret;
    }

    public String toRnc() {
        return "attribute " + name + " {xsd:string}";
    }

    public boolean isOptional() {
        return optional;
    }


    public static Atti optional(String name) {
        return new StringAtti( name, true );
    }

    public static Atti required(String name) {
        return new StringAtti( name, false );
    }
}
