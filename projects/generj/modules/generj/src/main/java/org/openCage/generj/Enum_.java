package org.openCage.generj;

import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.public interface Statement {
 */

public class Enum_ implements ClassI {
    private String name;
    private List<String> vals = new ArrayList<String>();
    private BlockComment comment;
    private String pack;

    public Enum_(String pack, String name, String... vals) {
        this.pack = pack;
        this.name = name;
        this.vals.addAll( Arrays.asList( vals ));
    }

    public Enum_(String pack, String name, List<String> vals) {
        this.pack = pack;
        this.name = name;
        this.vals.addAll( vals );
    }

    @Override
    public ClassI comment(BlockComment comment) {
        this.comment = comment;
        return this;
    }

    public String toString( String prefix ) {
        String ret = "";

        if ( pack != null ) {
            ret += "package " + pack + ";\n";
        }

        if ( comment != null ) {
            ret += comment.toString( prefix );
        }
        ret += prefix + "public enum " + name + " {";

        ret += Strings.join( vals );

        ret += "}\n";


        return ret;
    }

    public String toString() {
        return toString("");
    }
}
