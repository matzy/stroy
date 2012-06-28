package org.openCage.util.checksum;

import com.twmacinta.util.MD5InputStream;
import org.openCage.util.string.Strings;

import java.io.*;

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

/**
 * calculates the fingerprint of a file based on the first 2*16 bytes
 * (is not as fast as hoped)
 */
public class QuickMD5 implements MD5Provider {


  public String getChecksum( File f ) throws IOException {

    if (!f.exists()) {
        throw new FileNotFoundException(f.toString());
    }

    InputStream close_me = null;
    try {
      long buf_size = f.length();

      if (buf_size < 512) {
          buf_size = 512;
      }

      if (buf_size > 65536) {
          buf_size = 65536;
      }

      byte[]         buf = new byte[(int) buf_size];
      MD5InputStream in  = new MD5InputStream(new FileInputStream(f));
      close_me           = in;

//      while (in.read(buf) != -1) {};
      in.read(buf); // jusr read one buffer load;
      in.close();

      return Strings.asHex( in.hash() );

    } catch (IOException e) {

      if (close_me != null) {
          try { close_me.close();
          } catch (Exception e2) {}
      }

      throw e;
    }
  }}
