package org.openCage.ruleofthree.jtothree;


import org.openCage.kleinod.collection.ReiHashMap;
import org.openCage.kleinod.immutable.ImmuDate;
import org.openCage.kleinod.lambda.F0;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.type.TypeLit;
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

    private Map<Class, F1<Three,Object>> toThrees   = new ReiHashMap<Class, F1<Three, Object>>( new TypeLit<Class>() {} );
    private Map<Class, F1<Object,Three>> fromThrees = new ReiHashMap<Class, F1<Object, Three>>( new TypeLit<Class>() {});
    private Map<Class, F0<Object>>       defaults   = new ReiHashMap<Class, F0<Object>>( new TypeLit<Class>(){});

    private static Basics the = new Basics();

    public static Basics get() {
        return the;
    }

    private Basics() {
        register(
                char.class,
                new F0<Character>(){
                    @Override
                    public Character call() {
                        return ' ';
                    }
                },
                new F1<Three, Character>() {
                    @Override
                    public Three call(Character s) {
                        return THREE(""+s);
                    }
                },
                new F1<Character, Three>() {
                    @Override
                    public Character call(Three readable) {
                        return readable.getString().charAt(0);
                    }
                }
        );
        register(
                Character.class,
                new F0<Character>(){
                    @Override
                    public Character call() {
                        return ' ';
                    }
                },
                new F1<Three, Character>() {
                    @Override
                    public Three call(Character s) {
                        return THREE(""+s);
                    }
                },
                new F1<Character, Three>() {
                    @Override
                    public Character call(Three readable) {
                        return readable.getString().charAt(0);
                    }
                }
        );
        register(
                String.class,
                new F0<String>(){
                    @Override
                    public String call() {
                        return "";
                    }
                },
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
                new F0<Level>(){
                    @Override
                    public Level call() {
                        return Level.INFO;
                    }
                },
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
                new F0<Locale>(){
                    @Override
                    public Locale call() {
                        return Locale.getDefault();
                    }
                },
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
                int.class,
                new F0<Integer>(){
                    @Override
                    public Integer call() {
                        return 0;
                    }
                },
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
                Integer.class,
                new F0<Integer>(){
                    @Override
                    public Integer call() {
                        return 0;
                    }
                },
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
                long.class,
                new F0<Long>(){
                    @Override
                    public Long call() {
                        return 0L;
                    }
                },
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
                Long.class,
                new F0<Long>(){
                    @Override
                    public Long call() {
                        return 0L;
                    }
                },
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
                Double.class,
                new F0<Double>(){
                    @Override
                    public Double call() {
                        return 0D;
                    }
                },
                new F1<Three, Double>() {
                    @Override
                    public Three call(Double aLong) {
                        return THREE(aLong.toString());
                    }
                },
                new F1<Double, Three>() {
                    @Override
                    public Double call(Three readable) {
                        return Double.valueOf( readable.getString() );
                    }
                }
        );
        register(
                double.class,
                new F0<Double>(){
                    @Override
                    public Double call() {
                        return 0D;
                    }
                },
                new F1<Three, Double>() {
                    @Override
                    public Three call(Double aLong) {
                        return THREE(aLong.toString());
                    }
                },
                new F1<Double, Three>() {
                    @Override
                    public Double call(Three readable) {
                        return Double.valueOf( readable.getString() );
                    }
                }
        );
        register(
                Float.class,
                new F0<Float>(){
                    @Override
                    public Float call() {
                        return 0F;
                    }
                },
                new F1<Three, Float>() {
                    @Override
                    public Three call(Float aLong) {
                        return THREE(aLong.toString());
                    }
                },
                new F1<Float, Three>() {
                    @Override
                    public Float call(Three readable) {
                        return Float.valueOf( readable.getString() );
                    }
                }
        );
        register(
                float.class,
                new F0<Float>(){
                    @Override
                    public Float call() {
                        return 0F;
                    }
                },
                new F1<Three, Float>() {
                    @Override
                    public Three call(Float aLong) {
                        return THREE(aLong.toString());
                    }
                },
                new F1<Float, Three>() {
                    @Override
                    public Float call(Three readable) {
                        return Float.valueOf( readable.getString() );
                    }
                }
        );


        register(
                UUID.class,
                new F0<UUID>(){
                    @Override
                    public UUID call() {
                        return UUID.randomUUID();
                    }
                },

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
                new F0<ImmuDate>(){
                    @Override
                    public ImmuDate call() {
                        return ImmuDate.now();
                    }
                },
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
        register(
                boolean.class,
                new F0<Boolean>(){
                    @Override
                    public Boolean call() {
                        return false;
                    }
                },
                new F1<Three, Boolean>() {
                    @Override
                    public Three call(Boolean date) {
                        return THREE(date.toString());
                    }
                },
                new F1<Boolean, Three>() {
                    @Override
                    public Boolean call(Three readable) {
                        return Boolean.valueOf(readable.getString());
                    }
                }
        );
        register(
                Boolean.class,
                new F0<Boolean>(){
                    @Override
                    public Boolean call() {
                        return false;
                    }
                },
                new F1<Three, Boolean>() {
                    @Override
                    public Three call(Boolean date) {
                        return THREE(date.toString());
                    }
                },
                new F1<Boolean, Three>() {
                    @Override
                    public Boolean call(Three readable) {
                        return Boolean.valueOf(readable.getString());
                    }
                }
        );


    }

    public <T> void register( Class<T> clazz, F0<T> dflt, F1<Three,T> toReadable, F1<T,Three> fromReadable ) {
        defaults.put(clazz, (F0<Object>) dflt);
        toThrees.put(clazz, (F1<Three, Object>) toReadable);
        fromThrees.put(clazz, (F1<Object, Three>) fromReadable);

    }

    public <T> F1<T, Three> getFromThree(final Class<T> clazz) {
        return (F1<T, Three>) fromThrees.get(clazz);
    }

    public <T> F1<Three, T> getToThree(Class<T> clazz) {
        return (F1<Three, T>) toThrees.get(clazz);
    }

    public <T> F0<T> getDefault( Class<T> clazz ) {
        return (F0<T>) defaults.get(clazz);
    }


}
