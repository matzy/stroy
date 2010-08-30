package org.openCage.stjx;

import org.openCage.generj.Block;
import org.openCage.generj.Clazz;


public interface Complex {
    String getType();

    String toJava();
    Object toJava( String pack );

    String toJavaDecl();

    String toSAXStart();

    boolean uses(String name);

    String getName();

    String toRnc();

    void setInterface(String name);

    String toSAXEnd();

    void toToXML( Clazz clazz );

    void toJavaProperty(Clazz clazz);

    void toFromXMLStart(Block start);

    void toFromXMLEnd(Block end);
}
