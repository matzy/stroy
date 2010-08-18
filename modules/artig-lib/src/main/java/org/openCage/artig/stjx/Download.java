package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Download {
   private String screenshot;
   public String getScreenshot() {
      return screenshot;
   }
   public void setScreenshot( String screenshot ) {
      this.screenshot = screenshot;
   }
   private String icon;
   public String getIcon() {
      return icon;
   }
   public void setIcon( String icon ) {
      this.icon = icon;
   }
   private String download;
   public String getDownload() {
      return download;
   }
   public void setDownload( String download ) {
      this.download = download;
   }
    public String toString() {
       return "Download(screenshot: " + getScreenshot() +" icon: " + getIcon() +" download: " + getDownload() +" )";    }
}
