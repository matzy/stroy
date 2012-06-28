package org.openCage.stroy.algo.tree.zip;

import org.openCage.stroy.algo.fuzzyHash.FuzzyHashGen;

import java.util.zip.ZipEntry;
import java.io.InputStream;

import com.google.inject.Inject;

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

public class ZipFielFactoryImpl implements ZipFielFactory  {

    private final FuzzyHashGen<InputStream> fuzzyHashGen;

    @Inject
    public ZipFielFactoryImpl( final FuzzyHashGen<InputStream> fuzzyHashGen ) {
        this.fuzzyHashGen = fuzzyHashGen;
    }


    public ZipFiel create( final String path, final ZipEntry entry, final String type, FuzzyHashGen<InputStream> fg ) {
        return null;
//        return new ZipFiel( path, entry, type,
//                new Lazy<FuzzyHash>( new F0<FuzzyHash>() {
//                    public FuzzyHash call() {
//
//                        ZipFile zf = null;
//                        try {
//                            zf = new ZipFile( path );
//                            InputStream is = zf.getInputStream( entry );
//                            return fuzzyHashGen.create( is, type );
//                        } catch ( IOException e ) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                            return  null;
//                        }
//                    }
//                } ));
    }
}
