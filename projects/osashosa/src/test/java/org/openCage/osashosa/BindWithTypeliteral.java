package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

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

public class BindWithTypeliteral {

    // guice requires an constructor with @inject
    // to use a alreasy existing class you have to wrap it
    public static class ArrayListWrapper<T> extends ArrayList<T> {

        @Inject public ArrayListWrapper() {
            super();
        }
    }

    public static class ListInteger {
        public List<Integer> ll;

        @Inject public ListInteger(List<Integer> ll) {
            this.ll = ll;
        }
    }

    public static class ListString {
        public List<String> ll;

        @Inject public ListString(List<String> ll) {
            this.ll = ll;
        }
    }

    @Test
    public void testList() {
        Injector inj = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(new TypeLiteral<List<Integer>>() {}).to(new TypeLiteral<ArrayListWrapper<Integer>>(){});
            }
        });

        ListInteger base = inj.getInstance(ListInteger.class);

        assertTrue( base.ll instanceof ArrayList );

    }

    @Test( expected = UnsupportedOperationException.class )
    public void testBadTypeParam() {
        Injector inj = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(new TypeLiteral<List<Integer>>() {}).to(new TypeLiteral<ArrayListWrapper<Integer>>(){});
            }
        });

        // show guice/osashosa strength
        // inject respects typeparam
        // List<String> ist not bound => exception
        ListString ot = inj.getInstance(ListString.class);
    }
}
