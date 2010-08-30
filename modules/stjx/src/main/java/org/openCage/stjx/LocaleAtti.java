package org.openCage.stjx;

import org.openCage.generj.Block;
import org.openCage.generj.Clazz;
import org.openCage.generj.IfExpr;
import org.openCage.generj.Typ;
import org.openCage.lang.Strings;

import java.util.Locale;

import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NameExpr.SETTER;
import static org.openCage.generj.NewExpr.NEW;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;

public class LocaleAtti implements Atti {
    private String name;
    private boolean optional;

    public LocaleAtti(String name, boolean optional) {
        this.name = name;
        this.optional = optional;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toJava() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toSAXStart(String complexName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toRnc() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    @Override
    public void toJavaProperty(Clazz clazz) {
        clazz.property( TYP("Locale"), Strings.toFirstLower(name));
    }



    @Override
    public void toFromXMLStart(Block block, String varName) {

        IfExpr ite = block.ifNotNull( CALL( DOT( NAME("attributes"), NAME( "getValue")), STR(name) ));

        ite.thn().call( DOT( NAME("elem"), SETTER(name)),
                        NEW( TYP("Locale"), CALL( DOT( NAME("attributes"), NAME( "getValue")), STR(name) )));

        if( !optional ) {
            ite.els().thrw( Typ.s("IllegalArgumentException"), varName + ": attribute " + name + " is required"  );
        }
    }

    public static Atti required(String name) {
        return new LocaleAtti( name, false );
    }
}
