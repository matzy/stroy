package org.openCage.ruleofthree.jtothree;


import com.google.inject.TypeLiteral;
import org.openCage.lang.functions.F1;
import org.openCage.lang.inc.ImmuDate;
import org.openCage.rei.ReiHashMap;
import org.openCage.ruleofthree.Three;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import static org.openCage.ruleofthree.Threes.THREE;

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

    private Map<Class, F1<Three,Object>> toThrees = new ReiHashMap<Class, F1<Three, Object>>( new TypeLiteral<Class>() {} );
    private Map<Class, F1<Object,Three>> fromThrees = new ReiHashMap<Class, F1<Object, Three>>( new TypeLiteral<Class>() {});

    private static Basics the = new Basics();

    public static Basics get() {
        return the;
    }

    private Basics() {
        register(
                String.class,
                new F1<Three, String>() {
                    @Override
                    public Three call(String s) {
                        return THREE(s);
                    }
                },
                new F1<String, Three>() {
                    @Override
                    public String call(Three readable) {
                        return readable.getString();
                    }
                }
        );

        register(
                Level.class,
                new F1<Three, Level>() {
                    @Override
                    public Three call(Level level) {
                        return THREE(level.getName());
                    }
                },
                new F1<Level, Three>() {
                    @Override
                    public Level call(Three readable) {
                        return Level.parse(readable.getString());
                    }
                }
        );

        register(
                Locale.class,
                new F1<Three, Locale>() {
                    @Override
                    public Three call(Locale locale) {
//                        return THREE(locale.toLanguageTag());
                        return THREE(locale.toString());
                    }
                },
                new F1<Locale, Three>() {
                    @Override
                    public Locale call(Three readable) {
                        return SevenInSix.getLocale(readable.getString());
                        //return Locale.forLanguageTag( readable.getString().get());
                    }
                }
        );

        register(
                Integer.class,
                new F1<Three, Integer>() {
                    @Override
                    public Three call(Integer aLong) {
                        return THREE(aLong.toString());
                    }
                },
                new F1<Integer, Three>() {
                    @Override
                    public Integer call(Three readable) {
                        return Integer.valueOf( readable.getString() );
                    }
                }
        );
        register(
                Long.class,
                new F1<Three, Long>() {
                    @Override
                    public Three call(Long aLong) {
                        return THREE(aLong.toString());
                    }
                },
                new F1<Long, Three>() {
                    @Override
                    public Long call(Three readable) {
                        return Long.valueOf( readable.getString() );
                    }
                }
        );

        register(
                UUID.class,
                new F1<Three, UUID>() {
                    @Override
                    public Three call(UUID uuid) {
                        return THREE(uuid.toString());
                    }
                },
                new F1<UUID, Three>() {
                    @Override
                    public UUID call(Three readable) {
                        return UUID.fromString(readable.getString());
                    }
                }
        );
        register(
                ImmuDate.class,
                new F1<Three, ImmuDate>() {
                    @Override
                    public Three call(ImmuDate date) {
                        return THREE(date.toString());
                    }
                },
                new F1<ImmuDate, Three>() {
                    @Override
                    public ImmuDate call(Three readable) {
                        return ImmuDate.valueOf(readable.getString());
                    }
                }
        );


    }

    public <T> void register( Class<T> clazz, F1<Three,T> toReadable, F1<T,Three> fromReadable ) {
        toThrees.put(clazz, (F1<Three, Object>) toReadable);
        fromThrees.put(clazz, (F1<Object, Three>) fromReadable);

    }

    public <T> F1<T, Three> getFromThree(final Class<T> clazz) {
        return (F1<T, Three>) fromThrees.get(clazz);
    }

    public <T> F1<Three, T> getToThree(Class<T> clazz) {
        return (F1<Three, T>) toThrees.get(clazz);
    }

}
