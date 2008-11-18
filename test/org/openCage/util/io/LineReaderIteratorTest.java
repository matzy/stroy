package org.openCage.util.io;

import junit.framework.TestCase;

import java.net.URL;
import java.util.Iterator;
import java.io.File;

import org.openCage.util.iterator.Iterators;
import org.openCage.util.lang.FVoid1;

public class LineReaderIteratorTest extends TestCase {

    public void testIterate() {
        URL url = getClass().getResource( "/org/openCage/util/io/testme.txt");

        Iterator<String> it = FileUtils.iterator( new File( url.getPath() ));

        int i = 0;
        for ( String str : Iterators.loop(  it )) {
            switch( i ) {
                case 0: assertEquals( "aa", str ); break;
                case 1: assertEquals( "bb", str ); break;
                case 2: assertEquals( "cc", str ); break;
                case 3: assertEquals( "dd", str ); break;
                case 4: assertEquals( "ee", str ); break;
            }

            i++;
        }
    }

    public void testClose() {
        URL url = getClass().getResource( "/org/openCage/util/io/testme.txt");

        LineReaderIterator it = FileUtils.iterator( new File( url.getPath() ));

        it.next();
        it.next();
        it.close();

        try {
            it.next();
            fail( "what's that" );
        } catch ( Exception exp ) {
            // expected
        }

        // no expection
        it.close();

    }

    public void testStyle() {
        URL url = getClass().getResource( "/org/openCage/util/io/testme.txt");

        LineReaderIterator it = FileUtils.iterator( new File( url.getPath() ));

        try {
            for ( String str : Iterators.loop( it )) {
                System.out.println( str );
            }
        } finally {
            LineReaderIterator.close( it );
        }

        FileUtils.loop( new File( url.getPath() ),
                new FVoid1<LineReaderIterator>() {
                    public void call( LineReaderIterator lineReaderIterator ) {
                        for ( String str : Iterators.loop( lineReaderIterator )) {
                            System.out.println( str );
                        }
                    }
                } );

        FileUtils.withIterator( new File( url.getPath()), new FVoid1<Iterable<String>>() { public void call( Iterable<String> iterable ) {
            for ( String str : iterable ) {
                System.out.println( str );
            }
        }});

    }
}
