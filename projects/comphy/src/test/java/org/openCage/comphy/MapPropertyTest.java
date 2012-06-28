package org.openCage.comphy;

import org.junit.Test;
import org.openCage.lang.inc.Strng;
import org.openCage.lang.structure.Ref;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
public class MapPropertyTest {

    @Test
    public void testPut() {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        map.put( Strng.valueOf("duh"), new StringProperty("foo"));

        assertEquals( 1, count.get().intValue() );

    }

    @Test
    public void testElemModify () {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty str = new StringProperty("foo");
        map.put( Strng.valueOf("duh"), str );

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });

        str.set("bar");


        assertEquals( 1, count.get().intValue() );

    }

    @Test
    public void testPutOverride () {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty str = new StringProperty("foo");
        map.put( Strng.valueOf("duh"), str );
        map.put( Strng.valueOf("duh"), new StringProperty("new") );

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });


        str.set("bar");


        assertEquals(0, count.get().intValue());

    }

    @Test
    public void testPutAll() {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty stra = new StringProperty("aa");
        map.put( Strng.valueOf("a"), stra );
        StringProperty strb = new StringProperty("b");
        map.put( Strng.valueOf("b"), strb );

        Map<Strng,StringProperty> map2 = new HashMap<Strng, StringProperty>();
        StringProperty strb2 = new StringProperty("b2");
        map2.put( Strng.valueOf("b"), strb2 );
        StringProperty strc = new StringProperty("c");
        map2.put( Strng.valueOf("c"), strc );

        map.putAll(map2);

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });



        stra.set("22"); // event
        assertEquals(1, count.get().intValue());

        strb.set("22"); // no event
        assertEquals(1, count.get().intValue());

        strb2.set("22"); // event
        assertEquals(2, count.get().intValue());

        strc.set("22"); // event
        assertEquals(3, count.get().intValue());

    }

    @Test
    public void testClear() {
        MapProperty<StringProperty> map = new MapProperty<StringProperty>();
        StringProperty str = new StringProperty("foo");
        map.put( Strng.valueOf("duh"), str );
        map.clear();

        final Ref<Integer> count = new Ref<Integer>(0);

        map.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                count.set( count.get() + 1);
            }
        });


        str.set("bar");

        assertEquals(0, count.get().intValue());
    }
}
