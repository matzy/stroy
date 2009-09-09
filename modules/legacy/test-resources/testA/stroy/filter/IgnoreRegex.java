//package org.openCage.stroy.filter;
//
//import java.util.regex.Pattern;
//import java.util.List;
//
///**
// * stroy, a differencing tool
// * Copyright (C) May 17, 2007 Stephan Pfab
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
//public class IgnoreRegex implements Ignore {
//
//    private String patternString;
//    private Pattern pattern;
//    private Pattern pathPattern;
//    private boolean validNamePattern = true;
//    private boolean validPathPattern = true;
//
//
//    public void add( String newPattern ) {
//        if ( patternString == null ) {
//            patternString = "(" + newPattern + ")";
//        } else {
//            patternString += "|(" + newPattern + ")";
//        }
//
//        try {
//            pattern = Pattern.compile( patternString );
//            validNamePattern = true;
//        } catch ( Exception exp ) {
//            validNamePattern = false;
//            pattern = null;
//        }
//    }
//
//    public void setPathPattern( String pat ) {
//        try {
//            pathPattern = Pattern.compile( pat );
//            validPathPattern = true;
//        } catch ( Exception exp ) {
//            validPathPattern = false;
//            pathPattern = null;
//        }
//    }
//
//    public void setNamePattern(String val) {
//        patternString = val;
//        try {
//            pattern = Pattern.compile( patternString );
//            validNamePattern = true;
//        } catch ( Exception exp ) {
//            validNamePattern = false;
//            pattern = null;
//        }
//    }
//
//    public void setExtensions(List<String> extensions) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void setPatterns(List<String> patterns) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void setPaths(List<String> paths) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//
//    public boolean ignore(String name) {
//
//        if ( pattern == null ) {
//            return false;
//        }
//
//        return pattern.matcher( name ).matches();
//    }
//
//    public boolean ignoreByPath(String path) {
//        if ( pathPattern == null ) {
//            return false;
//        }
//
//        return pathPattern.matcher( path ).matches();
//    }
//
//    public boolean isValidNamePattern() {
//        return validNamePattern;
//    }
//
//    public boolean isValidPathPattern() {
//        return validPathPattern;
//    }
//
//    public void addExtension(String ext) {
//        add( ".*\\." + ext  );
//    }
//
//    public void reset() {
//        patternString = "";
//        pattern = Pattern.compile( patternString );
//        validNamePattern = true;
//    }
//
//    public String toString() {
//        return patternString;
//    }
//}
