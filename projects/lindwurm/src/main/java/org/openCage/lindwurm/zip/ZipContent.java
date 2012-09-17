package org.openCage.lindwurm.zip;

import org.openCage.kleinod.io.FileUtils;
import org.openCage.lindwurm.content.Content;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
public class ZipContent implements Content {

    private final String   rootPath;
    private final ZipEntry zipEntry;
    private String name;
//    private Lazy<String> checksum;

    public ZipContent(final String rootPath, final ZipEntry zipEntry, String name) {
        this.rootPath = rootPath;
        this.zipEntry = zipEntry;
        this.name = name;

//        this.checksum = new Lazy<String>( new F0E<String>() {
//            public String callWithException() throws Exception{
//                // TODO close ?
//                return new Sha1().getChecksum( new ZipFile(rootPath).getInputStream(zipEntry));
//            }
//        } );

    }


    @Override
    public String getName() {
        return name;
    }

//    @Override
//    public String getChecksum() {
//        return checksum.get();
//    }
//
//    @Override
//    public FuzzyHash getFuzzyHash() {
//        return Null.of(FuzzyHash.class); // TODO
//    }

    @Override
    public String getType() {
        return FileUtils.getExtension(name);
    }

    @Override
    public String getLocation() {
        return "/"; // TODO
    }

    @Override
    public InputStream getStream() {
        try {
            return new ZipFile(rootPath).getInputStream( zipEntry );
        } catch (IOException e) {
            // TODO
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
}
