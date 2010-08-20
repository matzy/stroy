package org.openCage.stjx;

import org.openCage.geni.Mesod;

public interface Complex {
    String getType();

    String toJava();

    String toJavaDecl();

    String toSAXStart();

    boolean uses(String name);

    String getName();

    String toRnc();

    void setInterface(String name);

    String toSAXEnd();

    String toToXML( Mesod mesod);
}
