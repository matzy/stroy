//package org.openCage.stroy.file;
//
//import org.openCage.stroy.Distance;
//import org.openCage.util.io.FileUtils;
//
//import java.io.File;
//
///**
// * stroy, a differencing tool
// * Copyright (C) May 27, 2007 Stephan Pfab
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
//public class FileDistance implements Distance<File> {
//
//    public FileDistanceBroker broker = new FileDistanceBroker();
//
//    public double distance(File a, File b) {
//
//        if ( !FileUtils.getExtension( a ).equals( FileUtils.getExtension( b ))) {
//            return 1.0;
//        }
//
//        double contentDist = broker.getDistance( a ).distance( a, b);
//
//        if ( a.getName().equals( b.getName() )) {
//            contentDist /= 2;
//        }
//
//
//        if ( a.getParent().equals( b.getParent() ) ) {
//            contentDist /= 1.3;
//        }
//
//
//
//        return contentDist;
//    }
//}
