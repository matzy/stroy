package org.openCage.stjx;

import org.omg.CosNaming.NamingContextOperations;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 19, 2010
 * Time: 2:22:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZeroOrMore {


    private Struct struct;
    private String collectionName;

    public ZeroOrMore(Struct struct, String collectionName) {
        this.struct = struct;
        this.collectionName = collectionName;
    }

    public Struct complex(String of) {
//        check( name );

        ListType ll = new ListType( struct, collectionName, of, false );
        struct.getStjx().addComplex( ll );
        struct.getComplexs().add( Ref.optional( collectionName ));
        return struct;
    }
}
