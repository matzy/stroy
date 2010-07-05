package com.google.inject;

import org.openCage.sip.ModuleInjector;

public class Guice {

    public static Injector createInjector( Module... modules) {
        return new ModuleInjector( modules );
    }

}
