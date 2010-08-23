//package org.openCage.lang.artifact;
//
//import com.sun.istack.internal.NotNull;
//import org.openCage.lang.structure.ESet;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
//public class Project {
//
//    private final String name;
//    private Set<Artifact> modules = new HashSet<Artifact>();
//    private Set<Artifact> externals = new HashSet<Artifact>();
//    private ESet<Artifact> all = new ESet<Artifact>();
//    private Map<String,List<Artifact>> alternatives = new HashMap<String, List<Artifact>>();
//
//    private static Map<String,Project> projects = new HashMap<String, Project>();
//
//    private Project( @NotNull String name ) {
//        this.name = name;
//    }
//
//    public static Project get( String name ) {
//        Project proj = projects.get( name );
//        if ( proj != null ) {
//            return proj;
//        }
//
//        proj = new Project( name );
//        projects.put( name, proj );
//
//        return proj;
//    }
//
//    public Artifact module( @NotNull Class resourceBase, @NotNull String groupid, @NotNull String name ) {
//
//        Artifact mod = all.getAdd( new Artifact(groupid, name ));
//        mod.base( resourceBase );
//        modules.add( mod );
//        return mod;
//    }
//
//    public Artifact external( @NotNull String groupid, @NotNull String name ) {
//        Artifact ext = all.getAdd( new Artifact(groupid, name ));
//        externals.add( ext );
//        return ext;
//    }
//
//    public Artifact alternative( String id, String groupId, String name ) {
//        List<Artifact> alts = alternatives.get( id );
//        if ( alts == null ) {
//            alts = new ArrayList<Artifact>();
//            alternatives.put( id, alts );
//        }
//
//        Artifact arti = new Artifact(groupId, name );
//        alts.add( arti );
//
//        return arti;
//    }
//
//
//    public void set( String id, String groupId, String name ) {
//        List<Artifact> alts = alternatives.get( id );
//        if ( alts == null ) {
//            throw new IllegalArgumentException( "no alternatives for " + id );
//        }
//
//        int idx = alts.indexOf( new Artifact( groupId, name ));
//
//        if ( idx < 0 ) {
//            throw new IllegalArgumentException( groupId + "-" + name + " not an alternative for " + id );
//        }
//
//        Artifact alt = alts.get(idx);
//
//
//    }
//
//
//    public Author author( @NotNull String name ) {
//        return new Author( name );
//    }
//
//    public Collection<Artifact> getModules() {
//        return modules;
//    }
//
//    public Collection<Artifact> getAll() {
//        return all;
//    }
//
//    public boolean isModule( Artifact arti ) {
//        return modules.contains( arti );
//    }
//
//    public Collection<Artifact> getExternals() {
//        return externals;
//    }
//
//    public Artifact get(String group, String name) {
//
//        if ( group == null || group.isEmpty() ) {
//            throw new IllegalStateException( "artifact not found " + group + " " + name + " did you mean " + didYouMean(name));
//        }
//
//        Artifact arti = all.get( new Artifact( group, name ));
//
//        if ( arti != null ) {
//            return arti;
//        }
//
//        throw new IllegalStateException( "artifact not found " + group + " " + name + " did you mean " + didYouMean(name));
//    }
//
//    private String didYouMean( String name ) {
//        String ret = "";
//        for ( Artifact arti : all ) {
//            if ( arti.gettName().equals( name ) ) {
//                ret += arti.toString() + " ";
//            }
//        }
//
//        return ret;
//    }
//
//    public void validate() {
//        for ( Artifact mod : modules ) {
//            mod.validate();
//        }
//
//        for ( Artifact ext : externals ) {
//            ext.validate();
//        }
//    }
//
//    public void include( Project other ) {
//        for ( Artifact arti : other.modules ) {
//            Artifact old = all.get( arti );
//            if ( old != null ) {
//                if ( !isModule( old )) {
//                    throw new IllegalArgumentException( "can't change artifact type" );
//                }
//                old.merge( arti );
//            } else {
//                modules.add( arti );
//                all.add( arti );
//            }
//        }
//
//        for ( Artifact arti : other.externals ) {
//            Artifact old = all.get( arti );
//            if ( old != null ) {
//                if ( isModule( old )) {
//                    throw new IllegalArgumentException( "can't change artifact type" );
//                }
//                old.merge( arti );
//            } else {
//                externals.add( arti );
//                all.add( arti );
//            }
//        }
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String toString() {
//        return "Project{" +
//                "name='" + name + '\'' +
//                ", all=" + all +
//                '}';
//    }
//
//    public void showDeps() {
//        List<Artifact> sorted = new ArrayList<Artifact>( all );
//        Collections.sort( sorted );
//
//        for ( Artifact arti : sorted ) {
//            arti.showDeps(0);
//        }
//    }
//}
