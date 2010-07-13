package com.google.inject;

public final class ConfigurationException extends RuntimeException {
    private String message;
//    private final com.google.inject.internal.ImmutableSet<com.google.inject.spi.Message> messages;
//    private java.lang.Object partialValue;
//    private static final long serialVersionUID = 0L;

//    public ConfigurationException(java.lang.Iterable<com.google.inject.spi.Message> messages) { /* compiled code */ }

//    public com.google.inject.ConfigurationException withPartialValue(java.lang.Object partialValue) { /* compiled code */ }
//
//    public java.util.Collection<com.google.inject.spi.Message> getErrorMessages() { /* compiled code */ }
//
//    public <E> E getPartialValue() { /* compiled code */ }
//
//    public java.lang.String getMessage() { /* compiled code */ }


    public ConfigurationException(String message) {
        this.message = message;
    }

    
}