package com.google.inject;

import org.openCage.osashosa.OsashosaInjector;

public class Guice {

    public static Injector createInjector( Module... modules) {
        return new OsashosaInjector( modules );
    }

}
