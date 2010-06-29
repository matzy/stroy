package com.google.inject.name;

import com.google.inject.Binder;

import java.util.Map;
import java.util.Properties;

public class Names  {
    private String name;

    private Names() {}

    public static Named named( String name) {
        return new NamedImpl( name );
    }

    public static void bindProperties( Binder binder, Map<String, String> properties) { /* compiled code */ }

    public static void bindProperties( Binder binder, Properties properties) { /* compiled code */ }
}