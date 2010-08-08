package org.openCage.stjx;

public interface Complex {
    String getType();

    String toJava();

    String toJavaDecl();

    String toSAXStart();

    boolean uses(String name);

    String getName();

    String toRnc();

    void addInterface(String name);
}
