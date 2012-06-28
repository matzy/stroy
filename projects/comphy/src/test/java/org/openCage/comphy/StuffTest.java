package org.openCage.comphy;

import com.google.inject.TypeLiteral;
import org.openCage.lang.inc.Str;
import org.openCage.lang.inc.Strng;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import java.util.List;

import static org.openCage.lang.inc.Strng.S;

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
public class StuffTest {

    public static class Foo implements UnionType {
        @Override
        public List<? extends TypeMirror> getAlternatives() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public TypeKind getKind() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public <R, P> R accept(TypeVisitor<R, P> v, P p) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    public static void main(String[] args) {

        //ImmuProp<Str> foo = new ToAndFro().get( new TypeLiteral<ImmuProp<Strng>>() {}, null );
        ImmuProp<Str> foo2 =  new ToAndFro().get( new TypeLiteral<ImmuProp<Str>>() {}, null );

        foo2.set(S(""));
    }


}
