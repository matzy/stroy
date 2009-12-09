//package org.openCage.util.io;
//
//import org.openCage.withResource.impl.LineReaderIterator;
//import junit.framework.TestCase;
//
//import java.net.URL;
//import java.util.Iterator;
//import java.io.File;
//
//import org.openCage.util.iterator.Iterators;
//import org.openCage.util.lang.V1;
//import org.openCage.util.lang.V2;
//
//public class LineReaderIteratorTest extends TestCase {
//
//    public void testIterate() {
//        URL url = getClass().getResource( "/org/openCage/util/io/testme.txt");
//
//        Iterator<String> it = FileUtils.iterator( new File( url.getPath() ));
//
//        int i = 0;
//        for ( String str : Iterators.loop(  it )) {
//            switch( i ) {
//                case 0: assertEquals( "aa", str ); break;
//                case 1: assertEquals( "bb", str ); break;
//                case 2: assertEquals( "cc", str ); break;
//                case 3: assertEquals( "dd", str ); break;
//                case 4: assertEquals( "ee", str ); break;
//            }
//
//            i++;
//        }
//    }
//
//    public void testClose() {
//        URL url = getClass().getResource( "/org/openCage/util/io/testme.txt");
//
//        LineReaderIterator it = FileUtils.iterator( new File( url.getPath() ));
//
//        it.next();
//        it.next();
//        it.close();
//
//        try {
//            it.next();
//            fail( "what's that" );
//        } catch ( Exception exp ) {
//            // expected
//        }
//
//        // no expection
//        it.close();
//
//    }
//
//    public void testStyle() {
//        URL url = getClass().getResource( "/org/openCage/util/io/testme.txt");
//
//        LineReaderIterator it = FileUtils.iterator( new File( url.getPath() ));
//
//        try {
//            for ( String str : Iterators.loop( it )) {
//                System.out.println( str );
//            }
//        } finally {
//            LineReaderIterator.close( it );
//        }
//
//        FileUtils.loop( new File( url.getPath() ),
//                new V1<LineReaderIterator>() {
//                    public void call( LineReaderIterator lineReaderIterator ) {
//                        for ( String str : Iterators.loop( lineReaderIterator )) {
//                            System.out.println( str );
//                        }
//                    }
//                } );
//
//        FileUtils.withIterator( new File( url.getPath()), new V1<Iterable<String>>() { public void call( Iterable<String> iterable ) {
//            for ( String str : iterable ) {
//                System.out.println( str );
//            }
//        }});
//
//
//        FileUtils.withOpen( new File( url.getPath()), new V2<String, LoopState>() {
//            public void c( String s, LoopState loopState ) {
//                System.out.println( s );
//                if ( loopState.idx() > 2 ) {
//                    loopState.doBreak();
//                    return;
//                }
//            }
//        } );
//    }
//
//    /*
//[[# 20081119]]
//++ A little stroy about the little for ;oop
//**2008.11.19** [#java java]
//
//        In the bad old times iterations over a array where written like this
//        for ( int i = 0; i < arr.length(); ++i ) {
//            arr[i] ...
//        }
//
//        A lot was written that you could easily run over the size of the array and that the index is not used
//        anyway and that other languages like lisp ... can do this much nicer.
//
//        In Java land you could also work, in some cases, with an iterator
//        while( it.hasMore() ) {
//            Foo foo = it.next();
//            ...
//        }
//
//        Which was only marginally better, because you could still run over the end and it also was plain ugly.
//        Here comes Java 5 with a its for loop and all was good:
//        for ( Foo foo : arr ) {
//            suff with foo
//        }
//
//        Cleaner saner safer compact and beautiful.
//        End of stroy ?
//
//        Sadly no because now that there is this beauty I want to use it anywhere, but that is not so easy.
//        I want to run through 2 collection in parallel.
//        for ( String str, Foo foo : arr, coll ) {
//            stuff with str and foo
//        }
//        right ? no, no such syntax. Ah I hear you say: that is rarly used. You sure ?
//        How about
//
//        for ( Integer idx, Foo foo : arr, coll ) {
//            stuff with str and foo
//        }
//
//        Because sometimes I do need the index. Well I created a little helper class and get.
//
//        for ( Count<Foo> foo : Iterators.count( arr )) {
//            do stuff with foo.o (object of type Foo)
//            and foo.i (obj index)
//        }
//
//        The same way you can use an extra construction to use
//
//        for ( T2<Foo, Bah> pair : Iterators.together( fooarr, baharr )) {
//            pair.i0, pair.i1
//        }
//
//        The loop terminates when the first array terminates. So that works but it is kind of ugly again because there
//        is no good name for the pairing.
//
//        Now on to a real challange. A file is sometimes just a list of lines. So a pleasant way of workihng with it is
//        for ( String str : FileUtils.lines( file )) {
//            ...
//        }
//
//        Looks great but can you spot the problem ?
//        Well the file is not closed. The underlying implementation uses a ReaderIterator that spits out text lines.
//        To close the InputStream you need access to that iterator which don't have. So a better alternative is
//
//         LineIterator it = FileUtils.getLineIterator( file );
//         try {
//            for ( String str : it ) {
//               ...
//            }
//         } finally {
//            LineIterator.close( it ) {
//         }
//
//         Ah back into ugly space but good. This is not just ugly but nasty because I the lazy programmer have to
//         remember to close the iterator. Which I will forget, sometimes.
//
//         So the utils class should make sure to close the iterator after I use it. The simple idea to close it in the last
//         iteration is flawed of course because I might exit the loop early. Isn't there some kind of way to handle the ins and outs.
//         Well of course its a method call
//
//
//
//
//
//
//     */
//}
