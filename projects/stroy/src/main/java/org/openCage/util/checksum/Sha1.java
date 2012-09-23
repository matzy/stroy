package org.openCage.util.checksum;

import com.google.inject.Inject;
import org.openCage.kleinod.text.Strings;
import org.openCage.kleinod.errors.Unchecked;
import org.openCage.kleinod.io.IOUtils;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.Memo;
import org.openCage.lindwurm.content.Content;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
public class Sha1 extends Memo<String,Content> implements Checksummer{


    @Inject
    public Sha1() {
        this( 4096 );
    }

    public Sha1( int buffersize ) {
        super( new CheckS(buffersize));
    }

    private static class CheckS implements F1<String,Content> {

        private final int buffersize;

        public CheckS(int buffersize) {
            this.buffersize = buffersize;
        }

        @Override
        public String call(Content content) {
            byte[]      buffer = new byte[buffersize];
            InputStream is     = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                is = content.getStream();

                int len = 0;
                while ( ( len = is.read(buffer)) > 0 ) {
                    len = pad( buffer, len);
                    md.digest(buffer,0,len);
                }

                return Strings.asHex(md.digest());

            } catch (NoSuchAlgorithmException e) {
                throw new Unchecked( e );
            } catch (IOException e) {
                throw new Unchecked( e );
            } catch (DigestException e) {
                throw new Unchecked( e );
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
        /**
         * sha-1 wants updates with al least 20 bytes
         * thus pad it
         * @param buffer
         * @param len
         * @return
         */
        public int pad( byte[] buffer, int len ) {
            for ( ; len < 20; len++ ) {
                buffer[len] = 0;
            }

            return len;
        }
    }
}
