package org.openCage.ruleofthree.jtothree;

import org.junit.Test;
import org.openCage.lang.inc.ImmuDate;

import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;

import static junit.framework.Assert.assertEquals;
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

public class BasicsTest {

    @Test
    public void string() {
        assertEquals( THREE("foo"),  Basics.get().getToThree(String.class).call( "foo" ));

        assertEquals( "duh", Basics.get().getFromThree(String.class).call(THREE("duh")));
    }

    @Test
    public void logLevel() {
        assertEquals( THREE("FINER"),  Basics.get().getToThree(Level.class).call( Level.FINER ));

        assertEquals( Level.ALL, Basics.get().getFromThree(Level.class).call( THREE("ALL")));
    }

    @Test
    public void longTest() {
        assertEquals( THREE("13"),  Basics.get().getToThree(Long.class).call( 13L ));

        assertEquals( new Long(42), Basics.get().getFromThree(Long.class).call( THREE("42")));

    }

    @Test
    public void integer() {
        assertEquals( THREE("13"),  Basics.get().getToThree(Integer.class).call( 13 ));

        assertEquals( new Integer(42), Basics.get().getFromThree(Integer.class).call( THREE("42")));

    }


    @Test
    public void uuid() {
        assertEquals( THREE("16bb74f0-a890-4e6e-a56d-957867ccfb0f"),  Basics.get().getToThree(UUID.class).call( UUID.fromString("16bb74f0-a890-4e6e-a56d-957867ccfb0f") ));

        assertEquals( UUID.fromString("16bb74f0-a890-4e6e-a56d-957867ccfb1f"), Basics.get().getFromThree(UUID.class).call( THREE("16bb74f0-a890-4e6e-a56d-957867ccfb1f")));

    }

    @Test
    public void immuDate() {
        assertEquals( THREE("2012-01-05 14:07:08-000"),  Basics.get().getToThree(ImmuDate.class).call( ImmuDate.valueOf("2012-01-05 14:07:08") ));

        assertEquals( ImmuDate.valueOf("2022-01-05 14:07:09"), Basics.get().getFromThree(ImmuDate.class).call( THREE("2022-01-05 14:07:09")));

    }

    @Test
    public void locale() {
        assertEquals( THREE("fr_CA"),  Basics.get().getToThree(Locale.class).call( Locale.CANADA_FRENCH ));

        assertEquals( Locale.JAPANESE, Basics.get().getFromThree(Locale.class).call( THREE("ja")));

    }
}
