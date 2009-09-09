//package org.openCage.stroy.file;
//
//import org.openCage.stroy.fileMeta.LeafFileMeta;
//import org.openCage.stroy.Distance;
//import org.openCage.util.io.IterableFile;
//
//import java.util.Map;
//import java.util.HashMap;
//
//import com.google.inject.Inject;
//
///**
// * Created by IntelliJ IDEA.
// * User: spfab
// * Date: May 30, 2007
// * Time: 2:00:30 PM
// * To change this template use File | Settings | File Templates.
// */
//public class TextContentAsMapDistance implements Distance<LeafFileMeta> {
//    final private Distance<Map<Integer,Boolean>> mapDistance;
//
//    @Inject
//    public TextContentAsMapDistance( Distance<Map<Integer,Boolean>> mapDistance ) {
//        this.mapDistance   = mapDistance;
//    }
//
//    public double distance( LeafFileMeta a, LeafFileMeta b) {
//
//        return mapDistance.distance( getContent(a), getContent(b));
//    }
//
//    private Map<Integer, Boolean> getContent(LeafFileMeta lfm) {
//
//        Object storage = lfm.get();
//
//        if ( storage == null ) {
//            Map<Integer, Boolean> map = new HashMap<Integer,Boolean>();
//
//            for ( String line : new IterableFile( lfm.getContent() ) ) {
//                map.put( line.hashCode(), true );
//            }
//
//            lfm.store( map );
//
//            return map;
//        }
//
//        return (Map<Integer, Boolean>)storage;
//    }
//}
