//package org.openCage.stjx;
//
//import org.openCage.generj.Block;
//import org.openCage.generj.Clazz;
//import org.openCage.generj.Exp;
//import org.openCage.generj.Mesod;
//import org.openCage.generj.NameExpr;
//import org.openCage.generj.Str;
//import org.openCage.generj.Typ;
//import org.openCage.lang.Strings;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: Aug 28, 2010
// * Time: 7:48:23 AM
// * To change this template use File | Settings | File Templates.
// */
//public class KeyVal implements Complex {
//    private String mapName;
//    private String key;
//    private String val;
//
//    public KeyVal( String mapName, String key, String complex) {
//        this.mapName = mapName;
//        this.key = key;
//        this.val = complex;
//    }
//
//    @Override
//    public String getType() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public String toJava() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public String toJavaDecl() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public String toSAXStart() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public boolean uses(String name) {
//        return false;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public String getName() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public String toRnc() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void setInterface(String name) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public String toSAXEnd() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void toToXML(Clazz clazz) {
//        Mesod mesod = clazz._public()._static().method( Typ.STRING, "toString" + mapName );
//
////        String lower = Strings.toFirstLower(name);
//
//        mesod.arg( Typ.STRING, "prefix" ).arg( Typ.s(mapName), mapName ).
//                body().
//                    field( Typ.STRING, "ret").init( NameExpr.n("prefix") ).
//                    assignPlus( "ret", new Str("<" + mapName + " "));
//
//        mesod.body().assignPlus( "ret", Exp.bi( "+", Exp.n("prefix"), Exp.s( "</"+mapName+">\\n" ) ));
//
//        mesod.body()._return( Exp.n("ret "));
//
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
//}
