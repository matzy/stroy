package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class App {
   private String mainClass;
   public String getMainClass() {
      return mainClass;
   }
   public void setMainClass( String mainClass ) {
      this.mainClass = mainClass;
   }
   private String icon;
   public String getIcon() {
      return icon;
   }
   public void setIcon( String icon ) {
      this.icon = icon;
   }
   private Download download;
   public Download getDownload() {
      return download;
   }
   public void setDownload( Download download ) {
      this.download = download;
   }
    public String toString() {
       return "App(mainClass: " + getMainClass() +" icon: " + getIcon() +" Download)";    }
}
