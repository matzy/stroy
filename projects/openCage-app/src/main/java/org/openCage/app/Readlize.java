package org.openCage.app;

import org.openCage.comphy.*;
import org.openCage.comphy.Readable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/29/12
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class Readlize {

    public Readable readalize( Object obj ) {


        if ( obj instanceof Readalizable ) {
            return ((Readalizable)obj).toReadable();
        }

        if ( obj instanceof List) {
            throw new Error( "sorry only ListProperty" );
        }

        if ( obj instanceof Map) {
            throw new Error( "sorry only MapProperty" );
        }

        if ( obj instanceof String ) {
            return new RString( (String)obj );
        }


        Class<?> c = obj.getClass();
        System.out.println( "class " + c.getName() + " {" );
        for ( Field publicField : c.getFields() ) {
            String fieldName = publicField.getName();
            String fieldType = publicField.getType().getName();
            System.out.printf( "  %s %s;%n", fieldType, fieldName );
        }
        System.out.println( "}" );

        return null;
    }


//    public static void main(String[] args) {
//        new Readlize().readalize( new ApplicationInfo(name, license));
//    }
}
