//package org.openCage.comphy.conv;
//
//import org.openCage.comphy.*;
//import org.openCage.comphy.ThreeText;
//import org.openCage.lang.functions.F1;
//import org.openCage.lang.inc.*;
//
//import java.util.Locale;
//import java.util.UUID;
//import java.util.logging.Level;
//
//import static org.openCage.comphy.Readables.R;
//
///***** BEGIN LICENSE BLOCK *****
// * BSD License (2 clause)
// * Copyright (c) 2006 - 2012, Stephan Pfab
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *     * Redistributions of source code must retain the above copyright
// *       notice, this list of conditions and the following disclaimer.
// *     * Redistributions in binary form must reproduce the above copyright
// *       notice, this list of conditions and the following disclaimer in the
// *       documentation and/or other materials provided with the distribution.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
// * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// ***** END LICENSE BLOCK *****/
//public class Basics {
//
//    private GMap<Class, F1<ThreeText,Object>> toReadables = new GHashMap<Class, F1<ThreeText, Object>>();
//    private GMap<Class, F1<Object,ThreeText>> fromReadables = new GHashMap<Class, F1<Object, ThreeText>>();
//
//    private static Basics the = new Basics();
//
//    public static Basics get() {
//        return the;
//    }
//
//    private Basics() {
//        register(
//                String.class,
//                new F1<ThreeText, String>() {
//                    @Override public ThreeText call(String s) {
//                        return R(s);
//                    }
//                },
//                new F1<String, ThreeText>() {
//                    @Override public String call(ThreeText readable) {
//                        return readable.getString().get();
//                    }
//                });
//
//        register(
//                Level.class,
//                new F1<ThreeText, Level>() {
//                    @Override
//                    public ThreeText call(Level level) {
//                        return R( level.getName());
//                    }
//                },
//                new F1<Level, ThreeText>() {
//                    @Override
//                    public Level call(ThreeText readable) {
//                        return Level.parse(readable.getString().get());
//                    }
//                });
//
//        register(
//                Locale.class,
//                new F1<ThreeText, Locale>() {
//                    @Override
//                    public ThreeText call(Locale locale) {
////                        return R(locale.toLanguageTag());
//                        return R(locale.toString());
//                    }
//                },
//                new F1<Locale, ThreeText>() {
//                    @Override
//                    public Locale call(ThreeText readable) {
//                        return SevenInSix.getLocale(readable.getString().get() );
//                        //return Locale.forLanguageTag( readable.getString().get());
//                    }
//                });
//
//        register(
//                Long.class,
//                new F1<ThreeText, Long>() {
//                    @Override
//                    public ThreeText call(Long aLong) {
//                        return R(aLong.toString());
//                    }
//                },
//                new F1<Long, ThreeText>() {
//                    @Override
//                    public Long call(ThreeText readable) {
//                        return Long.valueOf( readable.getString().get() );
//                    }
//                }
//        );
//
//        register(
//                UUID.class,
//                new F1<ThreeText, UUID>() {
//                    @Override
//                    public ThreeText call(UUID uuid) {
//                        return R(uuid.toString());
//                    }
//                },
//                new F1<UUID, ThreeText>() {
//                    @Override
//                    public UUID call(ThreeText readable) {
//                        return UUID.fromString( readable.getString().get() );
//                    }
//                }
//        );
//        register(
//                ImmuDate.class,
//                new F1<ThreeText, ImmuDate>() {
//                    @Override
//                    public ThreeText call(ImmuDate date) {
//                        return R(date.toString());
//                    }
//                },
//                new F1<ImmuDate, ThreeText>() {
//                    @Override
//                    public ImmuDate call(ThreeText readable) {
//                        return ImmuDate.valueOf(readable.getString().get());
//                    }
//                }
//        );
//
//        register(Strng.class,
//                new F1<ThreeText, Strng>() {
//                    @Override
//                    public ThreeText call(Strng str ) {
//                        return R(str);
//                    }
//                },
//                new F1<Strng, ThreeText>() {
//                    @Override
//                    public Strng call(ThreeText readable) {
//                        return (Strng)readable.getString();
//                    }});
//
//        register( Str.class,
//                new F1<ThreeText, Str>() {
//                    @Override
//                    public ThreeText call(Str str) {
//                        return R(str);
//                    }
//                },
//        new F1<Str, ThreeText>() {
//            @Override
//            public Str call(ThreeText readable) {
//                return readable.getString();
//            }
//        });
//
//    }
//
//    public <T> void register( Class<T> clazz, F1<ThreeText,T> toReadable, F1<T,ThreeText> fromReadable ) {
//        toReadables.put( clazz, (F1<ThreeText, Object>) toReadable);
//        fromReadables.put(clazz, (F1<Object, ThreeText>) fromReadable);
//
//    }
//
//    public <T> F1<T, ThreeText> getFromReadable( final Class<T> clazz) {
//        return (F1<T, ThreeText>)fromReadables.getG(clazz);
//    }
//
//    public <T> F1<ThreeText, T> getToReadable(Class<T> clazz) {
//        return (F1<ThreeText, T>) toReadables.getG(clazz);
//    }
//
//}
