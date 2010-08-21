package com.google.inject;

import org.openCage.osashosa.OsashosaInjector;

public class Guice {

    public static Injector createInjector( Module... modules) {
        return new OsashosaInjector( Stage.DEVELOPMENT, modules );
    }

    public static Injector createInjector( Stage stage, Module... modules) {
        return new OsashosaInjector( stage, modules );
    }

}
