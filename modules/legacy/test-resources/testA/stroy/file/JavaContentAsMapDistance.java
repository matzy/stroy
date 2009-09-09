//package org.openCage.stroy.file;
//
//import com.google.inject.Inject;
//import org.openCage.util.io.IterableFile;
//import org.openCage.stroy.Distance;
//import org.openCage.stroy.graph.node.TreeLeafNode;
//import org.openCage.stroy.fileMeta.LeafFileMeta;
//import org.openCage.stroy.dir.FileContent;
//import org.openCage.stroy.text.ForJava;
//import org.openCage.stroy.text.LineNoise;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by IntelliJ IDEA.
// * User: spfab
// * Date: May 30, 2007
// * Time: 12:05:30 PM
// * To change this template use File | Settings | File Templates.
// */
//public class JavaContentAsMapDistance implements Distance<TreeLeafNode<FileContent>>{
//    final private Distance<Map<Integer,Boolean>> mapDistance;
//    final private LineNoise                      noise;
//
//    @Inject
//    public JavaContentAsMapDistance( Distance<Map<Integer,Boolean>> mapDistance, @ForJava LineNoise noise ) {
//        this.mapDistance   = mapDistance;
//        this.noise         = noise;
//    }
//
//    public double distance( TreeLeafNode<FileContent> a, TreeLeafNode<FileContent> b) {
//
//        a.getContent().getFuzzyHash();
//
//        return mapDistance.distance( getContent(a), getContent(b));
//    }
//
//    private Map<Integer, Boolean> getContent(TreeLeafNode<FileContent> lfm) {
//
//        Object storage = lfm.get();
//
//        if ( storage == null ) {
//            Map<Integer, Boolean> map = new HashMap<Integer,Boolean>();
//
//            for ( String line : new IterableFile( lfm.getContent() ) ) {
//                if ( !noise.isGrayNoise( line )) {
//                    map.put( line.hashCode(), true );
//                }
//            }
//
//            lfm.store( map );
//
//            return map;
//        }
//
//        return (Map<Integer, Boolean>)storage;
//    }
//
//}
