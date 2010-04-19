package org.openCage.peleph;

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
}
