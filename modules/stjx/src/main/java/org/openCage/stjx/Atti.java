package org.openCage.stjx;

import org.openCage.generj.Block;
import org.openCage.generj.Clazz;

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

 String toSAXStart(String complexName);

    String toRnc();

    boolean isOptional();

    void toJavaProperty(Clazz clazz);

    void toFromXMLStart(Block thn, String varName);
}
