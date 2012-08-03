package org.openCage.util.app;

import org.openCage.notabug.Task;
import org.openCage.util.www.wikidot.WikiDotGen;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

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
public class Change {

    public final int num;
    public final String title;
    public final String typ;

    public String comment;
    public String user;
    public String version;
    public List<String> tags = new ArrayList<String>();
    public Integer original;
    public List<Integer> refs = new ArrayList<Integer>();
    public List<String> froms = new ArrayList<String>();
    public List<String> solvers = new ArrayList<String>();

    public Change( int num, String typ, String title ) {
        this.num = num;
        this.typ = typ;
        this.title = title;
    }

    public Task toTask() {
        Task task = Task.next( title );


        return task;
    }

    public Change user( String user ) {
        this.user = user;
        return this;
    }

    public Change implemented( String version ) {
        this.version = version;
        return this;
    }

    public Change tag( String tag ) {
        tags.add( tag );
        return this;
    }

    public Change from( String tag ) {
        froms.add( tag );
        return this;
    }

    public Change duplicate( int dup ) {
        original = dup;
        return this;
    }

    public Change ref( int other ) {
        refs.add( other );
        return this;
    }

    public Change comment( String comment ) {
        this.comment = comment;
        return this;
    }


    public void printWikiAncor() {
        System.out.println(WikiDotGen.ancor( num ) );
        System.out.println( "++++ " + fullTitle()  );

        if ( version != null ) {
            System.out.print( WikiDotGen.link( version, version));
        } else {
            System.out.print("open");
        }

        for ( String from : froms ) {
            System.out.print( " " + WikiDotGen.link( from, from ));
        }

        for ( String from : solvers ) {
            System.out.print( " " + WikiDotGen.link( from, from ));
        }

        for ( String tag : tags ) {
            System.out.print( " " + WikiDotGen.link( tag, "" + tag ));
        }

        for ( Integer ref : refs ) {
            System.out.print( " " + WikiDotGen.link( ref, "" + ref ));
        }

        System.out.println("");

        if ( comment != null ) {
            System.out.println( comment );
        }
    }

    public void printWikiVersionAncor() {
        if ( version == null ) {
            throw new IllegalArgumentException( "null version no ancor" );
        }
        System.out.println(WikiDotGen.ancor( version ) );
        System.out.println("+++ " + version );

    }

    public void printWikiLink() {
        System.out.println( WikiDotGen.link( num, fullTitle() ));
    }

    private String fullTitle() {
        return "(" + num + ") (" + typ + ") " + title;
    }


    public Collection<? extends String> getFroms() {
//        if ( froms.size() == 0 ) {
//            froms.add( "Stephan" );
//        }
        return froms;
    }

    public boolean isFrom(String from) {
        return getFroms().contains( from );
    }


    public boolean hasTag(String tag) {
        return tags.contains( tag );
    }

    public Change solve( String person ) {
        solvers.add( person);

        return this;
    }

    public boolean isHelped(String person) {
        return getHelpers().contains( person );
    }

    public Collection<? extends String> getHelpers() {
//        if ( solvers.size() == 0 && version != null ) {
//            solvers.add( "Stephan" );
//        }
        return solvers;
    }
}
