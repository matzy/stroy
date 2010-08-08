package org.openCage.stjx;

/**
* Created by IntelliJ IDEA.
* User: stephan
* Date: Aug 8, 2010
* Time: 2:24:36 AM
* To change this template use File | Settings | File Templates.
*/
public interface Atti {
 String getType();

 String getName();

 String toJava();

 String toSAXStart();

    String toRnc();

    boolean isOptional();
}
