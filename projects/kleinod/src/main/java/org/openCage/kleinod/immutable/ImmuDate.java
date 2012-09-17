package org.openCage.kleinod.immutable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


public class ImmuDate implements DateTime {

    private static SimpleDateFormat full   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SSS");
    private static SimpleDateFormat second = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat min    = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat hour   = new SimpleDateFormat("yyyy-MM-dd HH");
    private static SimpleDateFormat day    = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat month  = new SimpleDateFormat("yyyy-MM");


    private Date date;


    public ImmuDate() {
        date = new Date();
    }

    public ImmuDate(Date date) {
        this.date = (Date)date.clone();
    }

    public Date getDate() {
        return (Date)date.clone();
    }

    public static ImmuDate valueOf( Date date ) {
        return new ImmuDate(date);
    }

    public static ImmuDate valueOf( String str ) {
        try {
            return ImmuDate.valueOf(full.parse(str));
        } catch (ParseException e) {
            try {
                return ImmuDate.valueOf(second.parse(str));
            } catch (ParseException e1) {
                try {
                    return ImmuDate.valueOf(min.parse(str));
                } catch (ParseException e5) {
                    try {
                        return ImmuDate.valueOf(hour.parse(str));
                    } catch (ParseException e2) {
                        try {
                            return ImmuDate.valueOf(day.parse(str));
                        } catch (ParseException e3) {
                            try {
                                return ImmuDate.valueOf(month.parse(str));
                            } catch (ParseException e4) {
                                    throw new IllegalArgumentException( "not a well formatted time string:" + str );
                                }
                            }
                        }
                    }

            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImmuDate immuDate = (ImmuDate) o;

        if (date != null ? !date.equals(immuDate.date) : immuDate.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    @Override
    public String toString() {
        return full.format(date);
    }

    public String toPreciseString() {
        return full.format(date);
    }

    public static ImmuDate now() {
        return new ImmuDate();
    }

    @Override
    public int compareTo(DateTime o) {
        return date.compareTo(((ImmuDate)o).date);
    }
}
