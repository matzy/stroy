package com.google.inject.name;

public class Names  {
    private Names() {}

    public static Named named( String name) {
        return new NamedImpl( name );
    }
//
//    public static void bindProperties( Binder binder, Map<String, String> properties) { /* compiled code */ }
//
//    public static void bindProperties( Binder binder, Properties properties) { /* compiled code */ }
}