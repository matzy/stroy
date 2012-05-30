package org.openCage.comphy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openCage.comphy.RU.es;

public class ReadableToXML {

    public  StringBuilder write( String title, RMap map ) {
        StringBuilder builder = new StringBuilder();
        write( builder, new Key(title), "", map );
        return builder;
    }


    private void write(StringBuilder builder, Key title, String pre, RMap map) {
        builder.append(pre + "<" + title + ">\n");

        // sort
        List<Key> keys = new ArrayList<Key>( map.keySet());
        Collections.sort(keys);


        for ( Key key : keys ) {
            Readable val = map.get(key);

            if ( val instanceof RString) {
                builder.append(pre + "  <" + key + ">" + es(val) + "</" + key + ">\n");
            } else if (val instanceof RMap ) {
                write(builder, key, pre + "  ", (RMap) val);
            } else if (val instanceof RList ) {
                write(builder, key, pre + "  ", (RList) val);
            } else {
                throw new Error("impl me");
            }

        }

        builder.append(pre + "</" + title + ">\n");
    }


    private void write(StringBuilder builder, Key title, String pre, RList list) {
        builder.append(pre + "<" + title + ">\n");
        Key elementKey = list.getElementKey();
        for ( Readable elem : list ) {
            if ( elem instanceof RString) {
                builder.append(pre + "  <" + elementKey + ">" + es(elem) + "</" + elementKey + ">\n");
            } else if (elem instanceof RMap ) {
                write(builder, elementKey, pre + "  ", (RMap) elem);
            } else if (elem instanceof RList ) {
                write( builder, elementKey, pre + "  ", (RList)elem );
            } else {
                throw new Error("impl me");
            }
        }
        builder.append(pre + "</" + title + ">\n");
    }


}
