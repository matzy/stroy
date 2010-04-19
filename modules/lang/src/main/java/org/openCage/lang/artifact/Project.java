package org.openCage.lang.artifact;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private final String name;
    private List<Artifact> modules = new ArrayList<Artifact>();
    private List<Artifact> externals = new ArrayList<Artifact>();

    public Project( @NotNull String name ) {
        this.name = name;
    }

    public Artifact module( @NotNull String groupid, @NotNull String name ) {
        Artifact mod = new Artifact(groupid, name );
        modules.add( mod );
        return mod;
    }

    public Artifact external( @NotNull String groupid, @NotNull String name ) {
        Artifact ext = new Artifact(groupid, name );
        externals.add( ext );
        return ext;
    }

    public Author author( @NotNull String name ) {
        return new Author( name );
    }

    public List<Artifact> getModules() {
        return modules;
    }

    public boolean isModule( Artifact arti ) {
        return modules.contains( arti );
    }

    public List<Artifact> getExternals() {
        return externals;
    }

    public Artifact get(String group, String name) {
        Artifact arti = new Artifact( group, name );

        for ( Artifact module : modules ) {
            if ( module.equals( arti )) {
                return module;
            }
        }

        for ( Artifact ext : externals ) {
            if ( ext.equals( arti )) {
                return ext;
            }
        }

        throw new IllegalStateException( "artifact not found " + group + " " + name ); 
    }

    public void validate() {
        for ( Artifact mod : modules ) {
            mod.validate();
        }

        for ( Artifact ext : externals ) {
            ext.validate();
        }
    }
}
