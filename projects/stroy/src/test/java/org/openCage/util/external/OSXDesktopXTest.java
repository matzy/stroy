package org.openCage.util.external;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openCage.kleinod.errors.FileNotFoundUnchecked;
import org.openCage.kleinod.io.fspath.FSPath;
import org.openCage.kleinod.io.fspath.FSPathBuilder;
import org.openCage.kleinod.os.OS;

import java.io.File;

import static org.junit.Assume.assumeTrue;

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

public class OSXDesktopXTest {

    private FSPath tmpTxtFile;

    @Before
    public void beforeMethod() {
        assumeTrue(!OS.isWindows());

        tmpTxtFile = FSPathBuilder.getTmpTxtFile();
    }

    @After
    public void after() {
        tmpTxtFile.toFile().delete();
        tmpTxtFile = null;
    }


    @Test
    public void testOpenWithStdEditor() {

        new OSXDesktopX( null, null ).openWithStandardEditor( tmpTxtFile.toFile());

    }


    @Test( expected = FileNotFoundUnchecked.class )
    public void testNop() {
        new OSXDesktopX( null, null).openWithStandardEditor( FSPathBuilder.getTmpFile("foo").toFile() );

    }
}
