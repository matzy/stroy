package org.openCage.lang;

import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.F2;
import org.openCage.lang.functions.VF1;
import org.openCage.lang.inc.Null;
import org.openCage.lang.iterators.Iterators;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***** END LICENSE BLOCK *****/

public class Forall {

    public static IteratorProc<String> lines(File file) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            return new IteratorProc<String>( Iterators.lines(br), br );
        } catch (FileNotFoundException e) {
            throw Unchecked.wrap(e);
        }
    }

    public static <T> IteratorProc<T> forall(List<T> lst) {
        return new IteratorProc<T>( lst );
    }

    public static <T> IteratorProc<T> forall( Iterable<T> lst) {
        return new IteratorProc<T>( lst );
    }

    public static IteratorProc<String> lines( Reader reader) {
        try {
            BufferedReader br = new BufferedReader(reader);
            return new IteratorProc<String>( Iterators.lines( br ), br );
        } catch( Unchecked exp ) {
            try {
                reader.close();
            } catch (IOException e) {
            }
            throw exp;
        }
    }

    public static IteratorProc<String> lines( InputStream is ) {
        try {
            BufferedReader br = new BufferedReader( new InputStreamReader(is));
            return new IteratorProc<String>( Iterators.lines( br ), br );
        } catch( Unchecked exp ) {
            try {
                is.close();
            } catch (IOException e) {
            }
            throw exp;
        }
    }


    public static class IteratorProc<T> implements Iterable<T>, Iterator<T>{

        private final Iterator<T> iter;
        private F1<Boolean, T> filter;
        private T nextElem = null;
        private Closeable closable;

        public IteratorProc( Iterable<T> iter ) {
            this.iter = iter.iterator();
        }

        public IteratorProc(Iterator<T> iter, Closeable closeable) {
            this.iter = iter;
            this.closable = closeable;
        }

        public IteratorProc<T> skip( F1<Boolean, T> filter ) {
            this.filter = filter;
            return this;
        }

        public Set<T> toSet() {

            try {
                Set<T> ret = new HashSet<T>();

                for ( T elem : this ) {
                    ret.add( elem );
                }

                return ret;
            } finally {
                closeResource();
            }
        }

        public <Y> IteratorProc<Y> trans( final F1<Y,T> tr) {
            final IteratorProc<T> outer = this;

            return new IteratorProc<Y>( new Iterable<Y>() {
                public Iterator<Y> transIter = new Iterator<Y>() {
                    @Override
                    public boolean hasNext() {
                        return outer.hasNext();
                    }

                    @Override
                    public Y next() {
                        return tr.call(outer.next());
                    }

                    @Override
                    public void remove() {
                        outer.remove();
                    }
                };

                @Override
                public Iterator<Y> iterator() {
                    return transIter;
                }
            });
        }

        @Override
        public Iterator<T> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            getNext();
            return nextElem != null;
        }

        @Override
        public T next() {
            T ret = nextElem;
            nextElem = null;
            return ret;
        }

        @Override
        public void remove() {
            throw new Error( "impl me" );
        }

        private void getNext() {
            if ( nextElem != null ) {
                return;
            }

            while( iter.hasNext() ) {
                nextElem = iter.next();
                if ( Null.is(filter) || !filter.call(nextElem )) {
                    return;
                }
            }

            nextElem = null;
        }

        public void act( VF1<T> fct ) {
            try {
                for ( T elem : this ) {
                    fct.call(elem);
                }
            } finally {
                closeResource();
            }
        }

        public <Y> Y join( F1<Y,Void> first, F1<Y,Y> last, F2<Y,Y,T> trans ) {
            try {
                Y ret = first.call(null);
                for ( T elem : this ) {
                    trans.call(ret,elem);
                }
                last.call(ret);

                return ret;
            } finally {
                closeResource();
            }
        }

        private void closeResource() {
            if ( closable != null ) {
                try {
                    closable.close();
                } catch (IOException e) {
                }
            }
        }

        public <Y> Joiner<Y,T> join( Y start ) {
            return new Joiner<Y,T>( start, this );
        }

        public static class Joiner<Y,T> {

            private final IteratorProc<T> iter;
            private final Y start;
            private F1<Y,Y> end;
            private F1<Y,Y> sep;
            private F2<Y,Y,T> trans;

            public Joiner( Y start, IteratorProc<T> iter) {
                this.iter = iter;
                this.start = start;
            }

            public Y go() {
                try {
                    Y ret = start;
                    boolean first = true;
                    for ( T elem : iter ) {
                        if ( first ) {
                            first = false;
                        } else {
                            if ( sep != null ) {
                                ret = sep.call(ret);
                            }

                        }
                        ret = trans.call(ret,elem);
                    }

                    if ( end != null ) {
                        ret = end.call(ret);
                    }

                    return ret;
                } finally {
                    closeResource();
                }
            }

            public Joiner<Y,T> end( F1<Y,Y> end ) {
                this.end = end;
                return this;
            }

            public Joiner<Y,T> sep( F1<Y,Y> sep ) {
                this.sep = sep;
                return this;
            }

            public Joiner<Y,T> trans( F2<Y,Y,T> trans ) {
                this.trans = trans;
                return this;
            }

            private void closeResource() {
                if ( iter.closable != null ) {
                    try {
                        iter.closable.close();
                    } catch (IOException e) {
                    }
                }
            }



        }
    }

}
