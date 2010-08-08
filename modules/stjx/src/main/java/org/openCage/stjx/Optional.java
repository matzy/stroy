package org.openCage.stjx;

/**
* Created by IntelliJ IDEA.
* User: stephan
* Date: Aug 8, 2010
* Time: 2:36:53 AM
* To change this template use File | Settings | File Templates.
*/
public class Optional {
    private Struct struct;

    public Optional(Struct struct) {
        this.struct = struct;
    }

    public Struct string(String name) {
        struct.getAttis().add( StringAtti.optional( name  ));
        return struct;
    }

    public Struct complex(String name) {
        struct.getComplexs().add( Ref.optional(name));
        return struct;
    }
}
