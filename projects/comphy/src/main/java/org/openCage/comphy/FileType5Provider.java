//package org.openCage.comphy;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.openCage.comphy.RU.es;
//
///**
// * Created with IntelliJ IDEA.
// * User: stephan
// * Date: 5/21/12
// * Time: 10:27 AM
// * To change this template use File | Settings | File Templates.
// */
//public class FileType5Provider extends AbstractPropertyProvider<FileType> {
//    public FileType5Provider(PropertyStore store) {
//        super(store, new Dereadalizer<FileType>() {
//            @Override public FileType fromString(String str) {return null;}
//
//            @Override public FileType fromList(RList lst) { return null; }
//
//            @Override public FileType fromMap(RMap map) {
//                return new FileType(es(map.get("description")), es(map.get("diff")));
//            }
//        }, new FileType("unclear", "std"), new Key("foo"));
//    }
//}
