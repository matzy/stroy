package org.openCage.lispaffair;

import org.openCage.lishp.BuildinFunction;
import org.openCage.lishp.Function;

import java.util.LinkedList;
import java.util.List;

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
public class BuildinString {

    public static Function split = new BuildinFunction( ":split" ){
        public Object apply( List<Object> lst, Environment env ) {

            Object str = lst.get(1);
            Object regexp = lst.get(2);

            if (!( str instanceof String )) {
                System.err.println("split needs a string not " + str );
                return null;
            }

            if (!( regexp instanceof String )) {
                System.err.println("split needs a string not " + regexp );
                return null;
            }

            List ret = new LinkedList();
            
            for ( String elem : ((String)str).split( (String)regexp )) {
                ret.add( elem );
            }
            
            return ret;
        }

        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "split" );
            return toAppendTo;
        }
    };
}
