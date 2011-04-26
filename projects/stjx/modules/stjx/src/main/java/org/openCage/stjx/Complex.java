package org.openCage.stjx;

import org.openCage.generj.Block;
import org.openCage.generj.ClassI;
import org.openCage.generj.Class_;
import java.util.List;


/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
public interface Complex {

    ClassI toJava(String pack, String rootName);

    boolean uses(String name);

    boolean usesEmbedded(String name);

    String getClassName();
    String getTagName();

    String toRnc();

    void setInterface(String name);

    void toToXML( Class_ clazz );

    void toJavaProperty(Class_ clazz);

    void toFromXMLStart(Block start);

    void toFromXMLEnd(Block end);

    List<String> getRefs();
}
