//package org.openCage.stjx;
//
//import org.openCage.generj.*;
//import org.openCage.lang.Strings;
//
//import java.util.List;
//
//import static org.openCage.generj.BinOp.PLUS;
//import static org.openCage.generj.Call.CALL;
//import static org.openCage.generj.NameExpr.NAME;
//import static org.openCage.generj.Str.STR;
//import static org.openCage.generj.Typ.STRING;
//import static org.openCage.generj.Typ.TYP;
//import static org.openCage.generj.Typ.TYPOF;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: Sep 10, 2010
// * Time: 8:23:12 AM
// * To change this template use File | Settings | File Templates.
// */
//public class EmbeddedStringList implements Complex {
//
//    private Struct base;
//    private String listName;
//    private String elementName;
//
//
//    public EmbeddedStringList(Struct base, String name) {
//        this.base = base;
//        this.listName = name + "List";
//        this.elementName = name;
//    }
//
//
//    @Override
//    public ClassI toJava(String pack) {
//        return null;  // not standalone
//    }
//
//    @Override
//    public boolean uses(String name) {
//        return false;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public boolean usesEmbedded(String name) {
//        return false;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public String getClassName() {
//        return listName;
//    }
//
//    @Override
//    public String getTagName() {
//        return listName;
//    }
//
//    @Override
//    public String toRnc() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void setInterface(String name) {
//        throw new IllegalArgumentException( "a list is a list" );
//    }
//
//    @Override
//    public void toToXML(Clazz clazz) {
//        Mesod mesod = clazz.publc().sttic().method( STRING, "toString" + listName );
//
//        mesod.arg( STRING, NAME("prefix")).arg( TYPOF("List", STRING), NAME(listName ));
//
//        mesod.body().
//                fild( STRING, NAME("ret"));
//
//        mesod.body().fr( STRING, "vr",  NAME(listName)).body().
//                assignPlus( NAME("ret"), CALL( NAME("toString" + listName),
//                        PLUS( NAME("prefix"), STR("   ")),
//                        NAME("vr")) );
//
//
//
////         mesod.body().assignPlus( NAME("ret"), PLUS( NAME("prefix"), STR( "</"+name+">\\n" ) ));
//
//        mesod.body().retrn( NAME("ret"));
//    }
//
//    @Override
//    public void toJavaProperty(Clazz clazz) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void toFromXMLStart(Block start) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void toFromXMLEnd(Block end) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public List<String> getRefs() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//}
