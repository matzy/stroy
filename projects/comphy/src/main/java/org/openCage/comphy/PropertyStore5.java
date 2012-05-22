package org.openCage.comphy;

import java.util.*;

import static org.openCage.comphy.RU.es;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyStore5 implements Observer {

    private RMap readableProps = new RMap();

    public void add( String key, Property prop ) {
        prop.addObserver(this );
        readableProps.put(key, prop.toReadable());
        call(null);

    }

    public <T> T get( String key, Dereadalizer<T> deread ) {
        Readable rdlbVal  = readableProps.get(key);
        if ( rdlbVal instanceof ReadableString ) {
            return deread.fromString( es(rdlbVal));
        }

        throw new Error("implement me");
    }

    @Override
    public Void call(Object o) {
        System.out.println("a change!");
        show();
        return null;
    }

    public void show() {
        show( "comphy", "", readableProps );
    }

    private void show( String title, String pre, RMap map ) {
        System.out.println( pre + "<" + title + ">");

        // sort
        List<String> keys = new ArrayList<String>( map.keySet());
        Collections.sort(keys);


        for ( String key : keys ) {
            Readable val = map.get(key);

            if ( val instanceof ReadableString ) {
                System.out.println( pre + "  <" + key + ">" + es(val) + "</" + key + ">");
            } else if (val instanceof RMap ) {
                show( key, pre + "  ", (RMap)val );
            } else if (val instanceof RList ) {
                show( key, pre + "  ", (RList)val );
            } else {
                throw new Error("impl me");
            }

        }

        System.out.println( pre + "</" + title + ">");

    }

    private void show(String title, String pre, RList list) {
        System.out.println( pre + "<" + title + ">");
        String elementKey = list.getElementKey();
        for ( Readable elem : list ) {
            if ( elem instanceof ReadableString ) {
                System.out.println(pre + "  <" + elementKey + ">" + es(elem) + "</" + elementKey + ">");
            } else if (elem instanceof RMap ) {
                show( elementKey, pre + "  ", (RMap)elem );
            } else if (elem instanceof RList ) {
                show( elementKey, pre + "  ", (RList)elem );
            } else {
                throw new Error("impl me");
            }
        }
        System.out.println( pre + "</" + title + ">");
    }
}
