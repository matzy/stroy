//package org.openCage.app;
//
//import org.openCage.comphy.Dereadalizer;
//import org.openCage.comphy.RList;
//import org.openCage.comphy.RMap;
//import org.openCage.comphy.RString;
//
//public class ApplicationInfoDereader implements Dereadalizer<ApplicationInfo>{
//    @Override public ApplicationInfo fromString(RString str) { throw new IllegalArgumentException("no for ApplicationInfo"); }
//
//    @Override public ApplicationInfo fromList(RList lst)  { throw new IllegalArgumentException("no for ApplicationInfo"); }
//
//    @Override
//    public ApplicationInfo fromMap(RMap map) {
//        return new ApplicationInfo( map.get("name").toString(), map.get("license").toString());
//    }
//}
