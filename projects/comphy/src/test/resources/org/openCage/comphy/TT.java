//package org.openCage.comphy;
//
//import javax.swing.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: stephan
// * Date: 5/16/12
// * Time: 5:15 PM
// * To change this template use File | Settings | File Templates.
// */
//public class TT {
//
//    public static void main(String[] args) {
//
//        new TT().run();
//
//    }
//
//    private void run() {
//        PropStore ps = new PropStore();
//
//        ps.add( "one", new StringProperty("foo"));
//        ps.add( "two", new StringProperty("foo"));
//        ps.add( "three", new StringProperty("<woo>"));
//
//        PropList loglevels = new PropList("loglevel");
//        loglevels.add("SEVERE");
//        loglevels.add("FINE");
//        loglevels.add("FINER");
//        ps.add("four", loglevels );
//
//        loglevels.add( "WARNING");
//        loglevels.add( "FINEST");
//
//
//        AggregateProp<FileType> duda = new AggregateProp<FileType>();
//
//        duda.set( new FileType("A", "B"));
//
//        ps.add( "five", duda );
//
//        ps.write();
//    }
//}
