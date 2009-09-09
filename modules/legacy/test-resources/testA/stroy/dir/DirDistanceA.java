//package org.openCage.stroy.dir;
//
//import org.openCage.stroy.fileMeta.DirFileMeta;
//import org.openCage.stroy.fileMeta.FileMeta;
//import org.openCage.stroy.Distance;
//import org.openCage.stroy.dir.doubles.IdMap;
//
///**
// * stroy, a differencing tool
// * Copyright (C) Jun 7, 2007 Stephan Pfab
// * <p/>
// * This library is free software; you can redistribute it and/or
// * modify it under the terms of the GNU Lesser General Public
// * License as published by the Free Software Foundation; either
// * version 2.1 of the License, or (at your option) any later version.
// * <p/>
// * This library is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// * Lesser General Public License for more details.
// * <p/>
// * You should have received a copy of the GNU Lesser General Public
// * License along with this library; if not, write to the Free Software
// * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
// */
//public class DirDistanceA implements Distance<DirFileMeta> {
//
//    private final IdMap idmap;
//
//    public DirDistanceA( IdMap idmap ) {
//        this.idmap = idmap;
//    }
//
//    public double distance(DirFileMeta a, DirFileMeta b) {
//
//        int deleted       = 0;
//        int same          = 0;
//        int sameName      = 0;
//
//        for ( FileMeta kid : a.getChildren() ) {
//            if ( !idmap.isConnected( kid ) ) {
//                boolean findName = false;
//                for ( FileMeta bkid : b.getChildren() ) {
//
//                    if ( ! idmap.isConnected( bkid ) ) {
//                        if ( bkid.getContent().getName().equals( kid.getContent().getName() )) {
//                            findName = true;
//                            sameName++;
//                            break;
//                        }
//                    }
//                }
//
//                if ( !findName ) {
//                    deleted++;
//                }
//
//            } else {
//                if ( idmap.find( kid ).getParent() == b ) {
//                    same++;
//                } else {
//                    deleted++;
//                }
//            }
//        }
//
//        double val = Math.min( 1.0, 1 - (((double)same + 0.5 * sameName )/ ((double)a.getChildren().size() )));
//
//        if ( a.getContent().getName().equals( b.getContent().getName() ) ) {
//            val *= 0.5;
//        }
//
//        return val;
//    }
//}
