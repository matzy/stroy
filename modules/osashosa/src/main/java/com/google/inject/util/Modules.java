package com.google.inject.util;

import com.google.inject.Module;
import org.openCage.osashosa.OverrideModule;

public final class Modules  {
//    public static final com.google.inject.Module EMPTY_MODULE;

    private Modules() {}

    public static OverriddenModuleBuilder override( Module... modules) {
        return new OverrideModule( modules ){};
    }

    public static interface OverriddenModuleBuilder  {

        Module with( Module... modules);
    }
}