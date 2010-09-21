package org.openCage.stjx;

public class ZeroOrMore {


    private Struct struct;
    private String collectionName;

    public ZeroOrMore(Struct struct, String collectionName) {
        this.struct = struct;
        this.collectionName = collectionName;
    }

    public ZeroOrMore(Struct struct) {
        this.struct = struct;
    }

    public Struct complex(String of) {

        if ( collectionName != null ) {
            ListType ll = new ListType( struct, collectionName, of, false );
            struct.getStjx().addComplex( ll );
            struct.getComplexs().add( Ref.optional( collectionName ));
            return struct;
        }

        EmbeddedListType ll = new EmbeddedListType( struct, of, false );
        struct.getStjx().addComplex( ll );
        struct.getComplexs().add( Ref.optional( ll.getTagName() ));
        return struct;

    }

    public Struct string(String of) {

        if ( collectionName != null ) {
            ListType ll = new ListType( struct, collectionName, of, true );
            struct.getStjx().addComplex( ll );
            struct.getComplexs().add( Ref.optional( collectionName ));
            return struct;
        }

        MultiLine ml = new MultiLine( struct.getStjx(), struct, of );
        struct.getStjx().addComplex( ml );

        return struct.embeddedStringList( ml.getTagName() ); 
    }
}
