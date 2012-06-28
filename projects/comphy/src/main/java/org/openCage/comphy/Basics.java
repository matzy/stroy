package org.openCage.comphy;

import org.openCage.lang.functions.F1;
import org.openCage.lang.inc.ImuDate;
import org.openCage.lang.inc.Str;
import org.openCage.lang.inc.Strng;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

import static org.openCage.comphy.Readables.R;

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
public class Basics {

    private Map<Class, F1<Readable,Object>> toReadables = new HashMap<Class, F1<Readable, Object>>();
    private Map<Class, F1<Object,Readable>> fromReadables = new HashMap<Class, F1<Object, Readable>>();

    public Basics() {
        register(
                String.class,
                new F1<Readable, String>() {
                    @Override public Readable call(String s) {
                        return R(s);
                    }
                },
                new F1<String, Readable>() {
                    @Override public String call(Readable readable) {
                        return readable.getStr().get();
                    }
                });

        register(
                Level.class,
                new F1<Readable, Level>() {
                    @Override
                    public Readable call(Level level) {
                        return R( level.getName());
                    }
                },
                new F1<Level, Readable>() {
                    @Override
                    public Level call(Readable readable) {
                        return Level.parse(readable.getStr().get());
                    }
                });

        register(
                Locale.class,
                new F1<Readable, Locale>() {
                    @Override
                    public Readable call(Locale locale) {
                        return R(locale.toLanguageTag());
                    }
                },
                new F1<Locale, Readable>() {
                    @Override
                    public Locale call(Readable readable) {
                        return Locale.forLanguageTag( readable.getStr().get());
                    }
                });

        register(
                Long.class,
                new F1<Readable, Long>() {
                    @Override
                    public Readable call(Long aLong) {
                        return R(aLong.toString());
                    }
                },
                new F1<Long, Readable>() {
                    @Override
                    public Long call(Readable readable) {
                        return Long.valueOf( readable.getStr().get() );
                    }
                }
        );

        register(
                ImuDate.class,
                new F1<Readable, ImuDate>() {
                    @Override
                    public Readable call(ImuDate date) {
                        return R(date.toString());
                    }
                },
                new F1<ImuDate, Readable>() {
                    @Override
                    public ImuDate call(Readable readable) {
                        return ImuDate.valueOf( readable.getStr().get() );
                    }
                }
        );

        register(Strng.class,
                new F1<Readable, Strng>() {
                    @Override
                    public Readable call(Strng str ) {
                        return R(str);
                    }
                },
                new F1<Strng, Readable>() {
                    @Override
                    public Strng call(Readable readable) {
                        return (Strng)readable.getStr();
                    }});

        register( Str.class,
                new F1<Readable, Str>() {
                    @Override
                    public Readable call(Str str) {
                        return R(str);
                    }
                },
        new F1<Str, Readable>() {
            @Override
            public Str call(Readable readable) {
                return readable.getStr();
            }
        });

    }

    public <T> void register( Class<T> clazz, F1<Readable,T> toReadable, F1<T,Readable> fromReadable ) {
        toReadables.put( clazz, (F1<Readable, Object>) toReadable);
        fromReadables.put(clazz, (F1<Object, Readable>) fromReadable);

    }

    public <T> F1<T, Readable> getFromReadable( final Class<T> clazz) {
        return (F1<T, Readable>)fromReadables.get(clazz);
    }

    public <T> F1<Readable, T> getToReadable(Class<T> clazz) {
        return (F1<Readable, T>) toReadables.get(clazz);
    }

}
