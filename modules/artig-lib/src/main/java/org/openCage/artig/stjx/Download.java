package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
public class Download {
   private String screenshot;
   private String icon;
   private String download;
   public String getScreenshot(  ){
      return screenshot;
   }
   public void setScreenshot( String screenshot ){
      this.screenshot = screenshot;
   }
   public String getIcon(  ){
      return icon;
   }
   public void setIcon( String icon ){
      this.icon = icon;
   }
   public String getDownload(  ){
      return download;
   }
   public void setDownload( String download ){
      this.download = download;
   }
   public String toString(  ){
      return ArtifactDescriptionToXML.toStringDownload( "", this );
   }

}
