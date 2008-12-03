package org.openCage.util.checksum;

import com.twmacinta.util.MD5InputStream;
import org.openCage.util.string.Strings;

import java.io.*;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
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
