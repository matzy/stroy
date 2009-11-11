package org.openCage.gpad;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;

import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 17, 2009
 * Time: 9:03:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class FaustNum implements TextEncoder<Character> {

    List<String>[] num2line;
    Set<String> knownLines = new HashSet();
    Map<String,Integer> line2num = new HashMap<String,Integer>();

    public FaustNum() {
        num2line = new List[256];
        for ( int i = 0; i < 256; ++i ) {
            num2line[i] = new ArrayList<String>();
        }

        File ff = new File( getClass().getResource( "faust.txt" ).getPath());

        int idx = 0;
        FileLineIterable it = new WithImpl().getLineIteratorCloseInFinal( ff );
        try {
            for ( String str : it ) {
                if ( str.contains(":") || str.contains("(") || str.trim().equals( "" )) {
                    continue;
                }

                if ( knownLines.contains(str )) {
                    continue;
                }

                ++idx;
                knownLines.add( str );
                num2line[idx % 256].add(str);
                line2num.put( str, idx % 256 );
            }
        } finally {
            it.close();
        }
    }

    public String encode( Character ch ) {
        List<String> posi =  num2line[ch]; // TODO limit 255 for now
        return posi.get(((int)(Math.random() * posi.size())));
    }

    public Character decode( String line ) {
        return (char)(line2num.get( line ).intValue());
    }

}
